package SetsAndMaps_03.Exercise;

import java.util.*;
import java.util.stream.Collectors;

public class p11_LegendaryFarming {
    public static void main(String[] args) {

//        Shadowmourne – requires 250 Shards;
//        Valanyr – requires 250 Fragments;
//        Dragonwrath – requires 250 Motes;

        Map<String, Integer> numberOfKeyMaterials = new HashMap<>();
        Map<String, String> itemsMadeFromKeyMaterials = new HashMap<>();
        Map<String, Integer> numberOfJunkMaterials = new TreeMap<>();

        numberOfKeyMaterials.put("shards", 0);
        itemsMadeFromKeyMaterials.put("shards", "Shadowmourne");

        numberOfKeyMaterials.put("fragments", 0);
        itemsMadeFromKeyMaterials.put("fragments", "Valanyr");

        numberOfKeyMaterials.put("motes", 0);
        itemsMadeFromKeyMaterials.put("motes", "Dragonwrath");

        Scanner scanner = new Scanner(System.in);
        Map.Entry<String, Integer> obtainedItemEntry;
        while ((obtainedItemEntry = obtainedItemIn(numberOfKeyMaterials)) == null) {
            increaseNumberOfMaterialsIn(numberOfKeyMaterials, numberOfJunkMaterials, scanner.nextLine());
        }

        String legendaryItem = obtainItem(obtainedItemEntry, itemsMadeFromKeyMaterials);
        System.out.println(legendaryItem + " obtained!");

        String keyMaterialsLeft = numberOfKeyMaterials.entrySet()
                .stream().sorted((m1, m2) -> {
                    int result = m2.getValue().compareTo(m1.getValue());
                    if (result == 0) {
                        result = m1.getKey().compareTo(m2.getKey());
                    }
                    return result;
                })
                .map(m -> m.getKey() + ": " + m.getValue())
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(keyMaterialsLeft);

        String junkMaterialsLeft = numberOfJunkMaterials.entrySet()
                .stream().map(j -> j.getKey() + ": " + j.getValue())
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.print(junkMaterialsLeft);
    }

    static void increaseNumberOfMaterialsIn(Map<String, Integer> numberOfKeyMaterials,
                                            Map<String, Integer> numberOfJunkMaterials,
                                            String collectedMaterials) {
        String[] quantityMaterialPairs = collectedMaterials.split("\\s");

        for (int i = 0; i < quantityMaterialPairs.length; i++) {
            int newQuantity = Integer.parseInt(quantityMaterialPairs[i]);
            String material = quantityMaterialPairs[++i].toLowerCase();

            Integer materialQuantity = numberOfKeyMaterials.get(material);
            if (materialQuantity != null) {
                numberOfKeyMaterials.put(material, materialQuantity + newQuantity);
            } else if ((materialQuantity = numberOfJunkMaterials.get(material)) != null) {
                numberOfJunkMaterials.put(material, materialQuantity + newQuantity);
            } else {
                numberOfJunkMaterials.put(material, newQuantity);
            }

            if (obtainedItemIn(numberOfKeyMaterials) != null) {
                return;
            }
        }

    }

    static Map.Entry<String, Integer> obtainedItemIn(Map<String, Integer> numberOfKeyMaterials) {
        return numberOfKeyMaterials.entrySet().stream()
                .filter(i -> i.getValue() >= 250)
                .findFirst()
                .orElse(null);
    }

    static String obtainItem(Map.Entry<String, Integer> obtainedItemEntry, Map<String, String> items) {
        obtainedItemEntry.setValue(obtainedItemEntry.getValue() - 250);
        return items.get(obtainedItemEntry.getKey());
    }
}