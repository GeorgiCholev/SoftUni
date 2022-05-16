package Lists_5.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p09_PokemonDontGo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputSplit = input.split("\\s+");
        List<Integer> sequence = parseData(inputSplit);
        int sumOfDeletedElements = 0;
        while (sequence.size() > 0) {
            int index = Integer.parseInt(scanner.nextLine());
            sumOfDeletedElements += elementAtIndex(index, sequence);
        }
        System.out.println(sumOfDeletedElements);
    }

    private static int elementAtIndex(int index, List<Integer> sequence) {
        int removedElement;
        if (index >= 0 && index <= sequence.size() - 1) {
            removedElement = sequence.get(index);
            sequence.remove(index);
        } else if (index < 0) {
            index = 0;
            removedElement = sequence.get(index);
            sequence.set(index, sequence.get(sequence.size() - 1));
        } else {
            index = sequence.size() - 1;
            removedElement = sequence.get(index);
            sequence.set(index, sequence.get(0));
        }
        for (int i = 0; i < sequence.size(); i++) {
            if (sequence.get(i) <= removedElement) {
                sequence.set(i, sequence.get(i) + removedElement);
            } else {
                sequence.set(i, sequence.get(i) - removedElement);
            }
        }
        return removedElement;
    }

        private static List<Integer> parseData (String[]data){
            List<Integer> product = new ArrayList<>(data.length);
            for (String pointOfData : data) {
                product.add(Integer.parseInt(pointOfData));
            }
            return product;
        }
    }
