package restaurant.core;

import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.*;

import static restaurant.common.ExceptionMessages.*;
import static restaurant.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private double totalMoney;

    private HealthFoodRepository<HealthyFood> healthyFoodsRepo;
    private BeverageRepository<Beverages> beveragesRepo;
    private TableRepository<Table> tableRepo;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository,
                          BeverageRepository<Beverages> beverageRepository,
                          TableRepository<Table> tableRepository) {
        healthyFoodsRepo = healthFoodRepository;
        beveragesRepo = beverageRepository;
        tableRepo = tableRepository;
        totalMoney = 0;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood hFood;
        switch (type) {
            case "Salad":
                hFood = new Salad(name, price);
                break;
            default:   // case "VeganBiscuits":
                hFood = new VeganBiscuits(name, price);
                break;
        }

        if (healthyFoodsRepo.foodByName(name) != null) {
            throw new IllegalArgumentException(String.format(FOOD_EXIST, name));
        }

        healthyFoodsRepo.add(hFood);
        return String.format(FOOD_ADDED, name);
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
        Beverages bev;
        switch (type) {
            case "Smoothie":
                bev = new Smoothie(name, counter, brand);
                break;
            default:  // case "Fresh":
                bev = new Fresh(name, counter, brand);
                break;
        }

        if (beveragesRepo.beverageByName(name, brand) != null) {
            throw new IllegalArgumentException(String.format(BEVERAGE_EXIST, name));
        }

        beveragesRepo.add(bev);
        return String.format(BEVERAGE_ADDED, type, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table;
        switch (type) {
            case "InGarden":
                table = new InGarden(tableNumber, capacity);
                break;
            default:  // case "Indoors":
                table = new Indoors(tableNumber, capacity);
                break;
        }

        if (tableRepo.byNumber(tableNumber) != null) {
            throw new IllegalArgumentException(String.format(TABLE_IS_ALREADY_ADDED, tableNumber));
        }

        tableRepo.add(table);
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {
        Table readyForReservation = tableRepo.getAllEntities().stream()
                .filter(t -> !t.isReservedTable() && t.getSize() >= numberOfPeople)
                .findFirst()
                .orElse(null);

        if (readyForReservation == null) {
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }
        readyForReservation.reserve(numberOfPeople);
        return String.format(TABLE_RESERVED, readyForReservation.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table tableWithOrder = tableRepo.byNumber(tableNumber);
        if (tableWithOrder == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        HealthyFood orderedFood = healthyFoodsRepo.foodByName(healthyFoodName);
        if (orderedFood == null) {
            return String.format(NONE_EXISTENT_FOOD, healthyFoodName);
        }

        tableWithOrder.orderHealthy(orderedFood);
        return String.format(FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table tableWithOrder = tableRepo.byNumber(tableNumber);
        if (tableWithOrder == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        Beverages orderedBeverage = beveragesRepo.beverageByName(name, brand);
        if (orderedBeverage == null) {
            return String.format(NON_EXISTENT_DRINK, name, brand);
        }

        tableWithOrder.orderBeverages(orderedBeverage);
        return String.format(BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        Table tableWithBill = tableRepo.byNumber(tableNumber);
        double bill = tableWithBill.bill();
        tableWithBill.clear();
        totalMoney += bill;
        return String.format(BILL, tableNumber, bill);
    }


    @Override
    public String totalMoney() {
        return String.format(TOTAL_MONEY, this.totalMoney);
    }
}
