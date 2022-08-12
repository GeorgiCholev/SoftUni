package restaurant.repositories;

import restaurant.entities.healthyFoods.Food;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.HealthFoodRepository;
import restaurant.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HealthFoodRepositoryImpl implements HealthFoodRepository<HealthyFood> {

    private Collection<HealthyFood> healthFoods;

    public HealthFoodRepositoryImpl() {
        this.healthFoods = new ArrayList<>();
    }

    @Override
    public HealthyFood foodByName(String name) {
        return healthFoods.stream()
                .filter(f -> f.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<HealthyFood> getAllEntities() {
        return Collections.unmodifiableCollection(healthFoods);
    }

    @Override
    public void add(HealthyFood entity) {
        healthFoods.add(entity);
    }
}
