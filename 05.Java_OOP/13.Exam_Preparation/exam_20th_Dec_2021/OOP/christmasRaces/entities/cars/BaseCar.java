package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;
import static christmasRaces.common.ExceptionMessages.INVALID_MODEL;

public class BaseCar implements Car {
    private static final int MIN_MODEL_NAME_LENGTH = 4;

    private String model;
    private int horsePower;
    private double cubicCentimeters;

    protected BaseCar(String model, int horsePower, double cubicCentimeters) {
        setModel(model);
        setHorsePower(horsePower);
        this.cubicCentimeters = cubicCentimeters;
    }

    private void setModel(String model) {
        if (model == null || model.trim().isEmpty() || model.length() < MIN_MODEL_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format(INVALID_MODEL, model, MIN_MODEL_NAME_LENGTH));
        }

        this.model = model;
    }

    @Override
    public String getModel() {
        return model;
    }

    private void setHorsePower(int horsePower) {
        if (("MuscleCar".equals(getClass().getSimpleName()) && (horsePower < 400 || horsePower > 600)) ||
                ("SportsCar".equals(getClass().getSimpleName()) && (horsePower < 250 || horsePower > 450))) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
        this.horsePower = horsePower;
    }


    @Override
    public int getHorsePower() {
        return horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return cubicCentimeters / ((horsePower * laps) * 1.0);
    }

}
