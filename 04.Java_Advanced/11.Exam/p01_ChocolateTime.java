import java.util.*;

public class p01_ChocolateTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> numberOfChocolate = new LinkedHashMap<>(3);
        numberOfChocolate.put("Baking Chocolate", 0); //100
        numberOfChocolate.put("Dark Chocolate", 0); // 50
        numberOfChocolate.put("Milk Chocolate", 0); // 30

        ArrayDeque<Double> milkQuantityQueue = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" ")).map(Double::parseDouble).forEach(milkQuantityQueue::offer);

        ArrayDeque<Double> cacaoPowderStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" ")).map(Double::parseDouble).forEach(cacaoPowderStack::push);

        while (!milkQuantityQueue.isEmpty() && !cacaoPowderStack.isEmpty()) {
            double milkQuantity = milkQuantityQueue.poll();
            double cacaoPowder = cacaoPowderStack.pop();
            String percentageCacao = String.format("%.2f", (cacaoPowder / (milkQuantity + cacaoPowder)));

            int countOfChocolate;
            switch (percentageCacao) {
                case "0.30":
                    countOfChocolate = numberOfChocolate.get("Milk Chocolate") + 1;
                    numberOfChocolate.put("Milk Chocolate", countOfChocolate);
                    break;
                case "0.50":
                    countOfChocolate = numberOfChocolate.get("Dark Chocolate") + 1;
                    numberOfChocolate.put("Dark Chocolate", countOfChocolate);
                    break;
                case "1.00":
                    countOfChocolate = numberOfChocolate.get("Baking Chocolate") + 1;
                    numberOfChocolate.put("Baking Chocolate", countOfChocolate);
                    break;
                default:
                    milkQuantityQueue.offer(milkQuantity + 10);
                    break;
            }
        }

        long count = numberOfChocolate.values().stream().mapToDouble(v -> v).filter(v -> v != 0).count();
        if (count == 3) {
            System.out.println("It’s a Chocolate Time. All chocolate types are prepared.");
        } else {
            System.out.println("Sorry, but you didn't succeed to prepare all types of chocolates.");
        }

        numberOfChocolate.entrySet().stream().filter(e -> e.getValue() != 0)
                .forEach(e -> System.out.println("# " + e.getKey() + " --> " + e.getValue()));
    }
}
