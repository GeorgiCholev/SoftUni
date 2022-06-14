package ExamPreparation_10.April_13th_2022;

import java.util.*;

public class p01_Blacksmith {
    public static void main(String[] args) {

        Map<String, Integer> amountOfSword = new LinkedHashMap<>(4);
        amountOfSword.put("Gladius", 0);
        amountOfSword.put("Katana", 0);
        amountOfSword.put("Sabre", 0);
        amountOfSword.put("Shamshir", 0);

        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> steelQueue = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).forEach(steelQueue::offer);

        ArrayDeque<Integer> carbonStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).forEach(carbonStack::push);

        int totalNumberOfSwords = 0;
        int steel;
        int carbon;
        while (!steelQueue.isEmpty() && !carbonStack.isEmpty()) {
            steel = steelQueue.poll();
            carbon = carbonStack.pop();
            boolean isForged = forgeSword(steel, carbon, amountOfSword);
            if (!isForged) {
                carbon += 5;
                carbonStack.push(carbon);
                continue;
            }
            totalNumberOfSwords++;
        }

        firstLineOutput(totalNumberOfSwords);
        secondLineOutput(steelQueue);
        thirdLineOutput(carbonStack);
        fourthLineOutput(amountOfSword);
    }

    private static void fourthLineOutput(Map<String, Integer> amountOfSword) {
        amountOfSword.entrySet().stream().filter(e -> e.getValue() != 0)
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }

    private static void thirdLineOutput(ArrayDeque<Integer> carbonStack) {
        if (carbonStack.isEmpty()) {
            System.out.println("Carbon left: none");
        } else {
            System.out.print("Carbon left: ");
            while (!carbonStack.isEmpty()) {
                System.out.print(carbonStack.pop());
                if (!carbonStack.isEmpty()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    private static void secondLineOutput(ArrayDeque<Integer> steelQueue) {
        if (steelQueue.isEmpty()) {
            System.out.println("Steel left: none");
        } else {
            System.out.print("Steel left: ");
            while (!steelQueue.isEmpty()) {
                System.out.print(steelQueue.poll());
                if (!steelQueue.isEmpty()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    private static void firstLineOutput(int totalNumberOfSwords) {
        if (totalNumberOfSwords == 0) {
            System.out.println("You did not have enough resources to forge a sword.");
        } else {
            System.out.println("You have forged " + totalNumberOfSwords + " swords.");
        }
    }

    private static boolean forgeSword(int steel, int carbon, Map<String, Integer> amountOfSword) {
        int materialMix = steel + carbon;
        switch (materialMix) {
            case 70:
                amountOfSword.put("Gladius", amountOfSword.get("Gladius") + 1);
                return true;
            case 80:
                amountOfSword.put("Shamshir", amountOfSword.get("Shamshir") + 1);
                return true;
            case 90:
                amountOfSword.put("Katana", amountOfSword.get("Katana") + 1);
                return true;
            case 110:
                amountOfSword.put("Sabre", amountOfSword.get("Sabre") + 1);
                return true;
            default:
                return false;
        }

    }
}
