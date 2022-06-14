package DefiningClasses_06.Exercise.p04_RawData;

public class Car {
    private String model;
    private Engine engine;
    private Cargo cargo;
    private Tire[] fourTires;


    public Car(String model, Engine engine, Cargo cargo, Tire[] fourTires) {
        this.model = model;
        this.engine = engine;
        this.cargo = cargo;
        this.fourTires = fourTires;
    }

    public String getCargoType() {
        return this.cargo.getType();
    }

    public int getEnginePower() {
        return this.engine.getPower();
    }

    public String getModel() {
        return this.model;
    }

    public Tire[] getFourTires() {
        return fourTires;
    }
}
