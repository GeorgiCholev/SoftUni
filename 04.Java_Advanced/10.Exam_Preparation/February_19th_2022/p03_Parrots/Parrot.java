package ExamPreparation_10.February_19th_2022.p03_Parrots;

public class Parrot {

    String name;
    String species;
    boolean available;

    public Parrot(String name, String species) {
        this.name = name;
        this.species = species;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Parrot " + "(" + this.species + "): " + this.name;
    }
}
