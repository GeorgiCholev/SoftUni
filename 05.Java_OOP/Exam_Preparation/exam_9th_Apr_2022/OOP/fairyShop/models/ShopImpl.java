package fairyShop.models;

import java.util.Collection;

public class ShopImpl implements Shop {

    @Override
    public void craft(Present present, Helper helper) {

        if (!helper.canWork()) {
            return;
        }

        Collection<Instrument> instruments = helper.getInstruments();
        if (instruments.isEmpty()) {
            return;
        }

        for (Instrument instrument : instruments) {
            if (instrument.isBroken()) {
                continue;
            }
            while (!instrument.isBroken() && !present.isDone() && helper.canWork()) {

                present.getCrafted();
                helper.work();
                instrument.use();

                if (present.isDone() || !helper.canWork()) {
                    return;
                }
            }
        }
    }
}
