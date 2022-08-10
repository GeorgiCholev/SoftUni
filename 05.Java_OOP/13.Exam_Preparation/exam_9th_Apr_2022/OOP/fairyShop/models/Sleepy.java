package fairyShop.models;

public class Sleepy extends BaseHelper {
    private static final int INITIAL_ENERGY_UNITS = 50;
    private static final int ENERGY_DECREASE = 5;

    public Sleepy(String name) {
        super(name, INITIAL_ENERGY_UNITS);
        setAdditionalEnergyDecrease(ENERGY_DECREASE);
    }

}
