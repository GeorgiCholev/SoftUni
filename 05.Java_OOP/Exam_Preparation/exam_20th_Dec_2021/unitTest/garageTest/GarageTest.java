package garage;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GarageTests {
    private static final int MIDDLE_SPEED = 290;

    private static final String SLOW_CAR_BRAND = "Fiat";
    private static final int SLOW_CAR_SPEED = 80;
    private static final double SLOW_CAR_PRICE = 1500.0;

    private static final String FAST_CAR_BRAND = "Tesla";
    private static final int FAST_CAR_SPEED = 500;
    private static final double FAST_CAR_PRICE = 9999.99;

    private Garage garage;
    private Car slowCar;
    private Car fastCar;

    @Before
    public void setUp() {
        garage = new Garage();
        slowCar = new Car(SLOW_CAR_BRAND, SLOW_CAR_SPEED, SLOW_CAR_PRICE);
        fastCar = new Car(FAST_CAR_BRAND, FAST_CAR_SPEED, FAST_CAR_PRICE);
    }

    private void addTwoCarsToGarage() {
        garage.addCar(slowCar);
        garage.addCar(fastCar);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCar_ThrowsWhenNull_Otherwise_AddsToGarage() {

        Car nullCar = null;
        garage.addCar(nullCar);
        assertEquals(0, garage.getCount());

        addTwoCarsToGarage();
        assertEquals(2, garage.getCount());
    }


    @Test(expected = UnsupportedOperationException.class)
    public void listFromGetCarsMethod_CannotBeModified() {
        addTwoCarsToGarage();
        List<Car> unmodifiableCars = garage.getCars();
        assertEquals(garage.getCount(), unmodifiableCars.size());

        unmodifiableCars.remove(0);
        unmodifiableCars.add(new Car("TestCar", 0, 1000.05));
    }

    @Test
    public void onlyCarWithSpeedLargerThanMiddleSpeed_IsFastCar() {
        addTwoCarsToGarage();
        List<Car> allCarsWithMaxSpeedAbove = garage.findAllCarsWithMaxSpeedAbove(MIDDLE_SPEED);
        assertEquals(1, allCarsWithMaxSpeedAbove.size());

        assertEquals(fastCar, allCarsWithMaxSpeedAbove.get(0));
    }

    @Test
    public void mostExpensiveCarReturnsNullWithEmptyGarage() {
        assertNull(garage.getTheMostExpensiveCar());
    }

    @Test
    public void mostExpensiveCarReturnsExpectedOutcome() {
        addTwoCarsToGarage();
        assertEquals(fastCar, garage.getTheMostExpensiveCar());
    }

    @Test
    public void searchByFiatBrandReturnsSlowCar() {
        addTwoCarsToGarage();
        List<Car> allCarsByBrand = garage.findAllCarsByBrand(SLOW_CAR_BRAND);
        assertEquals(1, allCarsByBrand.size());
        assertEquals(slowCar, allCarsByBrand.get(0));
    }
}