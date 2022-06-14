package ExamPreparation_10.April_13th_2022.p03_EggBasket;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Egg> data;
    private String material;
    private int capacity;

    public Basket(String material, int capacity) {
        this.material = material;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void addEgg(Egg egg) {
        if (data.size() < this.capacity) {
            data.add(egg);
        }
    }

    public boolean removeEgg(String color) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getColor().equals(color)) {
                data.remove(i);
                return true;
            }
        }
        return false;
    }

    public Egg getStrongestEgg() {
        int highestStrength = Integer.MIN_VALUE;
        Egg strongestEgg = null;
        for (Egg egg : data) {
            if (egg.getStrength() > highestStrength) {
                strongestEgg = egg;
                highestStrength = strongestEgg.getStrength();
            }
        }
        return strongestEgg;
    }

    public Egg getEgg(String color) {
        for (Egg egg : data) {
            if (egg.getColor().equals(color)) {
                return egg;
            }
        }
        return null;
    }

    public int getCount() {
        return data.size();
    }

    public String report() {
        StringBuilder buffer = new StringBuilder(this.material + " basket contains:" + System.lineSeparator());
        this.data.forEach(egg -> buffer.append(egg.toString()).append(System.lineSeparator()));
        return buffer.toString().trim();
    }
}
