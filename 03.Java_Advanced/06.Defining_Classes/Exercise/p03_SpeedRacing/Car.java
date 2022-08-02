package DefiningClasses_06.Exercise.p03_SpeedRacing;

public class Car {

    private final String model;
    private double fuelAmount;
    private final double fuelCostPerKm;
    private int distanceTraveled;

    public Car(String model, double fuelAmount, double fuelCostPerKm) {
        this.model = model;
        this.fuelAmount = fuelAmount;
        this.fuelCostPerKm = fuelCostPerKm;
        this.distanceTraveled = 0;
    }

    public String getModel() {
        return model;
    }

    public boolean drive(int kilometres) {

        double expenditure = kilometres * fuelCostPerKm;
        if (fuelAmount >= expenditure) {
            fuelAmount -= expenditure;
            distanceTraveled += kilometres;
            return true;
        }
        return false;

    }

    public String getStatistics() {
        return model + " " + String.format("%.2f ", fuelAmount) + distanceTraveled;
    }
}
