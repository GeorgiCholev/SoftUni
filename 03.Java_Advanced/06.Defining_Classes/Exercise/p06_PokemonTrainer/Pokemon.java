package DefiningClasses_06.Exercise.p06_PokemonTrainer;

public class Pokemon {

    private final String name;
    private final String element;
    private int health;

    public Pokemon(String name, String element, int health) {
        this.name = name;
        this.element = element;
        this.health = health;
    }

    public String getElement() {
        return element;
    }

    public boolean damageBy(int quantity) {
        if (health > quantity) {
            health -= quantity;
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }
}
