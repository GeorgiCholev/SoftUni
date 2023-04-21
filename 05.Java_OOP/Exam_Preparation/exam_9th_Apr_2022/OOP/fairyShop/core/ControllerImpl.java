package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private HelperRepository helperRepository;
    private PresentRepository presentRepository;
    private int craftedPresents;

    public ControllerImpl() {
        helperRepository = new HelperRepository();
        presentRepository = new PresentRepository();
        craftedPresents = 0;
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;

        switch (type) {
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }

        helperRepository.add(helper);
        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Instrument instr = new InstrumentImpl(power);

        Helper byName = helperRepository.findByName(helperName);
        if (byName == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }

        byName.addInstrument(instr);
        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        presentRepository.add(present);
        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {

        Helper[] helpersWhoCraft = helperRepository.getModels().stream()
                .filter(helper -> helper.getEnergy() > 50)
                .toArray(Helper[]::new);
        if (helpersWhoCraft.length == 0) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }

        Present toBeCrafted = presentRepository.findByName(presentName);
        Shop shop = new ShopImpl();
        long totalBrokenInstruments = 0;
        for (Helper helper : helpersWhoCraft) {
            long brokenInstrumentsDuringCrafting = craftPresent(helper, toBeCrafted, shop);
            totalBrokenInstruments += brokenInstrumentsDuringCrafting;

            if (toBeCrafted.isDone()) {
                craftedPresents++;
                return presentCraftOutput(presentName, totalBrokenInstruments, "done");
            }
        }

        return presentCraftOutput(presentName, totalBrokenInstruments, "not done");
    }

    private String presentCraftOutput(String presentName, long totalBrokenInstruments, String outcome) {
        return String.format(PRESENT_DONE, presentName, outcome) +
                String.format(COUNT_BROKEN_INSTRUMENTS, totalBrokenInstruments);
    }

    private long craftPresent(Helper crafter, Present toBeCrafted, Shop shop) {

        shop.craft(toBeCrafted, crafter);
        return getBrokenInstrumentsCount(crafter);
    }

    private long getBrokenInstrumentsCount(Helper helper) {
        return helper.getInstruments().stream().filter(Instrument::isBroken).count();
    }

    @Override
    public String report() {
        return craftedPresents + " presents are done!" + System.lineSeparator() + helperRepository.toString();
    }
}
