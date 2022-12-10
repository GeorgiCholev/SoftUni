package Generics_08.Lab;

import java.util.List;

public class ListUtils {
    public static <T extends Comparable<T>> T getMin(List<T> list) {
        checkListSize(list);
        T minElement = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(minElement) < 0) {
                minElement = list.get(i);
            }
        }
        return minElement;
    }

    public static <T extends Comparable<T>> T getMax(List<T> list) {
        checkListSize(list);
        T maxElement = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(maxElement) > 0) {
                maxElement = list.get(i);
            }
        }
        return maxElement;
    }

    private static <T> void checkListSize(List<T> list) {
        if (list.size() == 0) {
            throw new IllegalArgumentException("Empty List");
        }

    }
}
