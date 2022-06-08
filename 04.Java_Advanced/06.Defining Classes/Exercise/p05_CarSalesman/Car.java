package DefiningClasses_06.Exercise.p05_CarSalesman;

public class Car {

    private String model;
    private Engine engine;
    private String weight;
    private String color;

    public Car(String model, Engine engine, String weight, String color) {
        this.model = model;
        this.engine = engine;
        this.weight = weight;
        this.color = color;
    }

    public Car(String model, Engine engine) {
        this(model, engine, "n/a", "n/a");
    }

    public Car(String model, Engine engine, String thirdInput) {
        this(model, engine);
        if (thirdInput.matches("\\d+")) {
            this.weight = thirdInput;
        } else {
            this.color = thirdInput;
        }
    }

    @Override
    public String toString() {
        return this.model + ":" + System.lineSeparator()
                + this.engine.getModel() + ":" + System.lineSeparator()
                + "Power: " + this.engine.getPower() + System.lineSeparator()
                + "Displacement: " + this.engine.getDisplacement() + System.lineSeparator()
                + "Efficiency: " + this.engine.getEfficiency() + System.lineSeparator()
                + "Weight: " + this.weight + System.lineSeparator()
                + "Color: " + this.color;
    }
}
