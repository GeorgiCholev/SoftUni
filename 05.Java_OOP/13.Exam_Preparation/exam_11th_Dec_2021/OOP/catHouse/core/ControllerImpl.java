package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.Repository;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private Repository toyRepository;
    private Collection<House> houses;

    public ControllerImpl() {
        toyRepository = new ToyRepository();
        houses = new ArrayList<>();
    }

    private House getHouseByName(String houseName) {
        return houses.stream()
                .filter(h -> h.getName().equals(houseName))
                .findFirst().orElse(new LongHouse("Unreachable Statement"));
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        switch (type) {
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            case "LongHouse":
                house = new LongHouse(name);
                break;
            default:
                throw new NullPointerException(INVALID_HOUSE_TYPE);
        }

        houses.add(house);
        return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        switch (type) {
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }

        toyRepository.buyToy(toy);
        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy ofType = toyRepository.findFirst(toyType);
        if (ofType == null) {
            throw new IllegalArgumentException(String.format(NO_TOY_FOUND, toyType));
        }
        toyRepository.removeToy(ofType);

        House searchedHouse = getHouseByName(houseName);

        searchedHouse.buyToy(ofType);
        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;
        switch (catType) {
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }

        House house = getHouseByName(houseName);
        if (catAndHouseAreSuited(cat, house)) {
            house.addCat(cat);
            return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
        }

        return UNSUITABLE_HOUSE;
    }

    private boolean catAndHouseAreSuited(Cat cat, House house) {
        return (cat.getClass().getSimpleName().equals("ShorthairCat")
                && house.getClass().getSimpleName().equals("ShortHouse")) ||
                (cat.getClass().getSimpleName().equals("LonghairCat")
                        && house.getClass().getSimpleName().equals("LongHouse"));
    }

    @Override
    public String feedingCat(String houseName) {
       House house = getHouseByName(houseName);
       house.feeding();
       return String.format(FEEDING_CAT, house.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = getHouseByName(houseName);
        Collection<Toy> toys = house.getToys();
        Collection<Cat> cats = house.getCats();
        double toysValue = toys.stream().mapToDouble(Toy::getPrice).sum();
        double catsValue = cats.stream().mapToDouble(Cat::getPrice).sum();
        double houseValue = toysValue + catsValue;

        return String.format(VALUE_HOUSE, houseName, houseValue);
    }

    @Override
    public String getStatistics() {
        return houses.stream()
                .map(House::getStatistics)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
