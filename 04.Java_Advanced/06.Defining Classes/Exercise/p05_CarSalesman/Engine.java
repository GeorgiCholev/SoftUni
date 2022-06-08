package DefiningClasses_06.Exercise.p05_CarSalesman;

public class Engine {

    private String model;
    private int power;
    private String displacement;
    private String efficiency;


    public Engine(String model, int power, String displacement, String efficiency) {
        this.model = model;
        this.power = power;
        this.displacement = displacement;
        this.efficiency = efficiency;
    }

    public Engine(String model, int power, String thirdInput) {
        this(model, power);
        if (thirdInput.matches("\\d+")) {
            this.displacement = thirdInput;
        } else {
            this.efficiency = thirdInput;
        }
    }


    public Engine(String model, int power) {
        this(model, power, "n/a", "n/a");
    }

    public int getPower() {
        return this.power;
    }

    public String getModel() {
        return this.model;
    }

    public String getDisplacement() {
        return this.displacement;
    }

    public String getEfficiency() {
        return this.efficiency;
    }
}
