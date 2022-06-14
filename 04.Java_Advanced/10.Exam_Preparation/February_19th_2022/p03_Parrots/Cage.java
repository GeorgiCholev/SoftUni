package ExamPreparation_10.February_19th_2022.p03_Parrots;

import java.util.ArrayList;
import java.util.List;

public class Cage {

    String name;
    int capacity;
    List<Parrot> parrots;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.parrots = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add(Parrot parrot) {
        if (this.parrots.size() < this.capacity) {
            this.parrots.add(parrot);
        }
    }

    public boolean remove(String parrotName) {
        for (int i = 0; i < parrots.size(); i++) {
            if (parrots.get(i).getName().equals(parrotName)) {
                parrots.remove(i);
                return true;
            }
        }
        return false;
    }

    public Parrot sellParrot(String parrotName) {
        for (Parrot parrot : parrots) {
            if (parrot.getName().equals(parrotName)) {
                parrot.setAvailable(false);
                return parrot;
            }
        }
        return null;
    }

    public List<Parrot> sellParrotBySpecies(String species) {
        List<Parrot> belongingToSpecies = new ArrayList<>();
        for (Parrot parrot : parrots) {
            if (parrot.getSpecies().equals(species)) {
                belongingToSpecies.add(parrot);
                parrot.setAvailable(false);
            }
        }
        return belongingToSpecies;
    }

    public int count() {
        return parrots.size();
    }

    public String report() {
        StringBuilder buffer = new StringBuilder("Parrots available at " + this.name + ":" + System.lineSeparator());
        parrots.stream().filter(Parrot::isAvailable)
                .forEach(parrot -> buffer.append(parrot).append(System.lineSeparator()));
        return buffer.toString();
    }
}
