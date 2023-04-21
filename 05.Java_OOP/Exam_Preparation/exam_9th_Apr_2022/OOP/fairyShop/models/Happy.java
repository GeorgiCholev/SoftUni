package fairyShop.models;

public class Happy extends BaseHelper {
    private static final int INITIAL_ENERGY_UNITS = 100;
    private static final int ENERGY_DECREASE = 0;

    public Happy(String name) {
        super(name, INITIAL_ENERGY_UNITS);
        setAdditionalEnergyDecrease(ENERGY_DECREASE);
    }

}
