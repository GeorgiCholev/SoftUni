package Generics_08.Exercise.BoxProblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Box <T extends Comparable<T>> {
    List<T> elements;

    public Box() {
        this.elements = new ArrayList<>();
    }

    public void add(T element) {
        elements.add(element);
    }

    public void swap(int indexOne, int indexTwo) {
        Collections.swap(elements, indexOne, indexTwo);
    }

    public int countGreater(T outsider) {
        long count = elements.stream().filter(el -> el.compareTo(outsider) > 0).count();
        return (int) count;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (T element : elements) {
            buffer.append(String.format("%s: %s%n", element.getClass().getName(), element.toString()));
        }
        return buffer.toString();
    }
}
