package catHouse.entities.cat;

public class ShorthairCat extends BaseCat {

    private static final int INITIAL_KILOGRAMS = 7;
    private static final int WEIGHT_PUT_AFTER_EATING = 1;

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
        setKilograms(INITIAL_KILOGRAMS);
    }

    @Override
    public void eating() {
        setKilograms(getKilograms() + WEIGHT_PUT_AFTER_EATING);
    }
}
