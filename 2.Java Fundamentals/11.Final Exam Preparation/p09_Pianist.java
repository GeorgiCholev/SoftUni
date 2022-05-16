package Fundamentals_ExamPrep_11.August15th2020;

import java.util.*;

public class p03_Pianist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Piece> pieces = addInitialPieces(scanner);
        String command = scanner.nextLine();
        while (!command.equals("Stop")) {
            String[] components = command.split("\\|");
            doSelectedInstruction(components, pieces);
            command = scanner.nextLine();
        }
        pieces.forEach((key, value) -> System.out.println(value.getInfo()));
    }

    private static void doSelectedInstruction(String[] components, Map<String, Piece> pieces) {
        String instruction = components[0];
        String name = components[1];
        switch (instruction) {
            case "Add":
                if (!pieces.containsKey(name)) {
                    Piece newAddition = new Piece(name, components[2], components[3]);
                    pieces.put(name, newAddition);
                    System.out.printf("%s by %s in %s added to the collection!\n", name, components[2], components[3]);
                } else {
                    System.out.printf("%s is already in the collection!\n", name);
                }
                break;
            case "Remove":
                if (pieces.containsKey(name)) {
                    pieces.remove(name);
                    System.out.printf("Successfully removed %s!\n", name);
                } else {
                    System.out.printf("Invalid operation! %s does not exist in the collection.\n", name);
                }
                break;
            case "ChangeKey":
                if (pieces.containsKey(name)) {
                    String key = components[2];
                    pieces.get(name).setKey(key);
                    System.out.printf("Changed the key of %s to %s!\n", name, key);
                } else {
                    System.out.printf("Invalid operation! %s does not exist in the collection.\n", name);
                }
                break;
        }
    }

    private static Map<String, Piece> addInitialPieces(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Piece> pieces = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] components = line.split("\\|");
            String name = components[0];
            String composer = components[1];
            String key = components[2];
            pieces.put(name, new Piece(name, composer, key));
        }
        return pieces;
    }

    static class Piece {
        String name;
        String composer;
        String key;

        Piece(String name, String composer, String key) {
            this.name = name;
            this.composer = composer;
            this.key = key;
        }


        String getInfo() {
            return String.format("%s -> Composer: %s, Key: %s", this.name, this.composer, this.key);
        }

        void setKey(String key) {
            this.key = key;
        }
    }
}
