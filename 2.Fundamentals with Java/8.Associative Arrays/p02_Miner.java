package Maps_8.Exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p02_Miner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String input = scanner.nextLine();
        Map<String, Integer> quantityOfMaterial = new LinkedHashMap<>();
        while (!input.equals("stop")) {
            String material = input;
            input = scanner.nextLine();
            int quantity = Integer.parseInt(input);
            quantityOfMaterial.putIfAbsent(material, 0);
            quantityOfMaterial.put(material, quantityOfMaterial.get(material) + quantity);

            input = scanner.nextLine();
        }
        quantityOfMaterial.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
