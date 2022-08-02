package DefiningClasses_06.Exercise.p06_PokemonTrainer;

import java.util.ArrayList;
import java.util.List;

public class Trainer {

    private final String name;
    private final List<Pokemon> pokemons;
    private int numberOfBadges;

    public Trainer(String name) {
        this.name = name;
        this.pokemons = new ArrayList<>();
        this.numberOfBadges = 0;
    }

    public int getNumberOfBadges() {
        return numberOfBadges;
    }

    public void damagePokemonsBy(int quantity) {
        int i = 0;
        while (i < pokemons.size()) {
            boolean canTakeDamage = pokemons.get(i).damageBy(quantity);
            if (!canTakeDamage) {
                pokemons.remove(i);
            } else {
                i++;
            }
        }
    }

    public void catchPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
    }

    public boolean findInPokemons(String element) {
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getElement().equals(element)){
                return true;
            }
        }
        return false;
    }

    public void receiveBadge() {
        numberOfBadges++;
    }

    @Override
    public String toString() {
        return name + " " + numberOfBadges + " " + pokemons.size();
    }
}
