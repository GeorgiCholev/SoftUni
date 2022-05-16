package Lists_5.Exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class p08_AnonymousThreat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<String> data = new ArrayList<>(Arrays.asList(input.split("\\s+")));
        String line = scanner.nextLine();
        while (!line.equals("3:1")) {
            String[] components = line.split("\\s+");
            String command = components[0];
            switch (command) {
                case "merge":
                    mergeDataInRange(data, Integer.parseInt(components[1]), Integer.parseInt(components[2]));
                    break;
                case "divide":
                    divideDataAtIndex(data, Integer.parseInt(components[1]), Integer.parseInt(components[2]));
                    break;
            }
            line = scanner.nextLine();
        }
        System.out.println(String.join(" ", data));
    }

    private static void divideDataAtIndex(List<String> data, int index, int partitions) {
        if (partitions != 0 && partitions != 1) {
            String dataToDivide = data.get(index);
            data.remove(index);
            if (dataToDivide.length() < partitions) {
                partitions = dataToDivide.length();
            }
            int position = 0;
            for (int i = 0; i < partitions; i++) {
                String blockOfData;
                if (i != partitions - 1) {
                    blockOfData = dataToDivide.substring(position, dataToDivide.length() / partitions + position);
                    data.add(index + i, blockOfData);
                    position += blockOfData.length();
                } else {
                    blockOfData = dataToDivide.substring(position);
                    data.add(index + i, blockOfData);
                }
            }
        }
    }

    private static void mergeDataInRange(List<String> data, int startIndex, int endIndex) {

        if (startIndex < 0) {
            if ((endIndex < 0) || (endIndex >= data.size())) {
                endIndex = (Math.min(endIndex - startIndex, data.size() - 1));
            }
            startIndex = 0;
        } else if (startIndex <= data.size() - 1) {
            endIndex = Math.min(endIndex, data.size() - 1);
        } else if (startIndex > data.size() - 1) {
            startIndex = Math.max(0, (data.size() - 1) - (endIndex - startIndex));
            endIndex = data.size() - 1;
        }

        List<String> theMerge = new ArrayList<>(endIndex - startIndex + 1);
        int tracker = startIndex;
        while (tracker <= endIndex) {
            theMerge.add(data.get(startIndex));
            data.remove(startIndex);
            tracker++;
        }
        String merged = String.join("", theMerge);
        data.add(startIndex, merged);
    }
}
