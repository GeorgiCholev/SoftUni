package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

import static restaurant.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static restaurant.common.ExceptionMessages.INVALID_TABLE_SIZE;

public class BaseTable implements Table {

    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    protected BaseTable(int number, int size, double pricePerPerson) {
        healthyFood = new ArrayList<>();
        beverages = new ArrayList<>();
        this.number = number;
        setSize(size);
        this.pricePerPerson = pricePerPerson;
        isReservedTable = false;
    }

    @Override
    public int getTableNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    private void setSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    @Override
    public int numberOfPeople() {
        return numberOfPeople;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return isReservedTable;
    }

    @Override
    public double allPeople() {
        return allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        setNumberOfPeople(numberOfPeople);
        this.allPeople = numberOfPeople * pricePerPerson;

        if (!isReservedTable) {
            isReservedTable = true;
        }
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double foodBill = healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();
        double beveragesBill = beverages.stream().mapToDouble(Beverages::getPrice).sum();
        return foodBill + beveragesBill + this.allPeople;
    }

    @Override
    public void clear() {
        healthyFood = new ArrayList<>();
        beverages = new ArrayList<>();
        allPeople = 0.0;
        numberOfPeople = 0;
        isReservedTable = false;
    }

    @Override
    public String tableInformation() {

        return "Table - " + number + System.lineSeparator() +
                "Size - " + size + System.lineSeparator() +
                "Type - " + getClass().getSimpleName() + System.lineSeparator() +
                "All price - " + pricePerPerson;
    }
}
