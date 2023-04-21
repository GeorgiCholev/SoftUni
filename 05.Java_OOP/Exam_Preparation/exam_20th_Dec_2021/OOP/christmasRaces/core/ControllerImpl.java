package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository,
                          Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        if (driverRepository.getByName(driver) != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }

        Driver newDriver = new DriverImpl(driver);
        driverRepository.add(newDriver);
        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {

        Car alreadyCreated = carRepository.getByName(model);
        if (alreadyCreated != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }

        Car car;
        switch (type) {
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                break;
            default: // case: "Sport"
                car = new SportsCar(model, horsePower);
                break;
        }

        carRepository.add(car);
        return String.format(CAR_CREATED, car.getClass().getSimpleName(), model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {

        Driver driver =
                findInRepositoryOrThrow(driverRepository, driverName, String.format(DRIVER_NOT_FOUND, driverName));

        Car car =
                findInRepositoryOrThrow(carRepository, carModel, String.format(CAR_NOT_FOUND, carModel));

        driver.addCar(car);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {

        Driver driver =
                findInRepositoryOrThrow(driverRepository, driverName, String.format(DRIVER_NOT_FOUND, driverName));

        Race race =
                findInRepositoryOrThrow(raceRepository, raceName, String.format(RACE_NOT_FOUND, raceName));

        race.addDriver(driver);
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {

        Race race =
                findInRepositoryOrThrow(raceRepository, raceName, String.format(RACE_NOT_FOUND, raceName));

        if (race.getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        int raceLaps = race.getLaps();
        Driver[] topThree = race.getDrivers().stream()
                .sorted((d1, d2) -> (Double.compare(d2.getCar().calculateRacePoints(raceLaps),
                        d1.getCar().calculateRacePoints(raceLaps))))
                .limit(3).toArray(Driver[]::new);

        return String.format(DRIVER_FIRST_POSITION, topThree[0].getName(), raceName) + System.lineSeparator() +
                String.format(DRIVER_SECOND_POSITION, topThree[1].getName(), raceName) + System.lineSeparator() +
                String.format(DRIVER_THIRD_POSITION, topThree[2].getName(), raceName);
    }

    @Override
    public String createRace(String name, int laps) {
        if (raceRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }

        Race race = new RaceImpl(name, laps);
        raceRepository.add(race);
        return String.format(RACE_CREATED, name);
    }

    private <T> T findInRepositoryOrThrow(Repository<T> repository, String tagToSearchBy, String excMessage) {

        T searchedEntity = repository.getByName(tagToSearchBy);
        if (searchedEntity == null) {
            throw new IllegalArgumentException(excMessage);
        }

        return searchedEntity;
    }
}
