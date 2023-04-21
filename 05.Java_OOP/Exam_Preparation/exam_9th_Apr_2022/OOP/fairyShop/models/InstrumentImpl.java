package fairyShop.models;

import static fairyShop.common.ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO;

public class InstrumentImpl implements Instrument {

    private static final int POWER_DEPRECIATION = 10;
    private int power;

    public InstrumentImpl(int power) {
        setInitialPower(power);
    }

    private void setInitialPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(INSTRUMENT_POWER_LESS_THAN_ZERO);
        }

        this.power = power;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void use() {
        int afterUse = power - POWER_DEPRECIATION;

        this.power = Math.max(afterUse, 0);
    }

    @Override
    public boolean isBroken() {
        return power == 0;
    }
}
