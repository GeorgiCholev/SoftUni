package fairyShop.repositories;

import fairyShop.models.Present;

import java.util.*;

public class PresentRepository implements Repository<Present> {

    private Map<String, Present> presentsByName;

    public PresentRepository() {
        this.presentsByName = new LinkedHashMap<>();
    }

    @Override
    public Collection<Present> getModels() {
        return Collections.unmodifiableCollection(new ArrayList<>(presentsByName.values()));
    }

    @Override
    public void add(Present model) {
        presentsByName.put(model.getName(), model);
    }

    @Override
    public boolean remove(Present model) {
        return presentsByName.remove(model.getName()) != null;
    }

    @Override
    public Present findByName(String name) {
        return presentsByName.get(name);
    }
}
