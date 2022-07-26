package DefiningClasses_06.Lab.p02_Constructors;

public class Car {

    public String brand;
    public String model;
    public int horsePower;

    public Car(String brand, String model, int horsePower) {
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
    }

    public Car(String brand) {
        this(brand, "unknown", -1);
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public String carInfo() {
        return "The car is: " + getBrand() + " " + getModel() + " - " + getHorsePower() + " HP.";
    }
}
