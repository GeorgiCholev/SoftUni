package fairyShop.repositories;

import fairyShop.models.Helper;
import fairyShop.models.Instrument;

import java.util.*;

public class HelperRepository implements Repository<Helper> {

    private Map<String, Helper> helpersByName;

    public HelperRepository() {
        this.helpersByName = new LinkedHashMap<>();
    }

    @Override
    public Collection<Helper> getModels() {
        return Collections.unmodifiableCollection(new ArrayList<>(helpersByName.values()));
    }

    @Override
    public void add(Helper model) {
        helpersByName.put(model.getName(), model);
    }

    @Override
    public boolean remove(Helper model) {
        return helpersByName.remove(model.getName()) != null;
    }

    @Override
    public Helper findByName(String name) {
        return helpersByName.get(name);
    }

    @Override
    public String toString() {
        StringBuilder report = new StringBuilder("Helpers info:" + System.lineSeparator());
        helpersByName.values().forEach(helper ->
                report.append("Name: ").append(helper.getName()).append(System.lineSeparator())
                        .append("Energy: ").append(helper.getEnergy()).append(System.lineSeparator())
                        .append("Instruments: ").append(getCount(helper)).append(" not broken left")
                        .append(System.lineSeparator()));

        return report.toString().trim();
    }

    private long getCount(Helper h) {
        Collection<Instrument> instr = h.getInstruments();
        return instr.stream()
                .filter(i -> !i.isBroken()).count();
    }
}
