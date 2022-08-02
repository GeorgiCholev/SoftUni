package DefiningClasses_06.Exercise.p06_PokemonTrainer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, Trainer> trainersByName = new LinkedHashMap<>();

        Scanner scanner = new Scanner(System.in);
        String input;
        while (!"Tournament".equals(input = scanner.nextLine())) {

            String[] data = input.split("\\s");

            addNewTrainerIn(trainersByName, data[0]);

            catchPokemon(data, trainersByName);
        }

        String elementFromConsole;
        while (!"End".equals(elementFromConsole = scanner.nextLine())) {

            for (Trainer trainer : trainersByName.values()) {
                boolean isPresent = trainer.findInPokemons(elementFromConsole);
                if (isPresent) {
                    trainer.receiveBadge();
                } else {
                    trainer.damagePokemonsBy(10);
                }
            }
        }

        trainersByName.values().stream()
                .sorted((t1, t2) -> Integer.compare(t2.getNumberOfBadges(), t1.getNumberOfBadges()))
                .forEach(System.out::println);
    }

    static void catchPokemon(String[] data, Map<String, Trainer> trainersByName) {
        Trainer catcher = trainersByName.get(data[0]);
        String pokemonName = data[1];
        String pokemonElement = data[2];
        int pokemonHealth = Integer.parseInt(data[3]);
        Pokemon pokemonToCatch = new Pokemon(pokemonName, pokemonElement, pokemonHealth);
        catcher.catchPokemon(pokemonToCatch);
    }

    static void addNewTrainerIn(Map<String, Trainer> trainersByName, String name) {
        Trainer trainer = new Trainer(name);
        trainersByName.putIfAbsent(name, trainer);
    }
}
