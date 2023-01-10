package solutions;

import interfaces.Decrease;
import interfaces.Heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {

    private List<E> elements = new ArrayList<>();

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);

        surface(this.elements.size() - 1);
    }

    private void surface(int index) {
        if (index == 0) {
            return;
        }

        int parentIndex = (index - 1) / 2;
        E parent = this.elements.get(parentIndex);
        E current = this.elements.get(index);

        if (current.compareTo(parent) < 0) {
            swapElements(index, parentIndex);
            surface(parentIndex);
        }
    }

    private void swapElements(int indexA, int indexB) {
        E oldElementA = this.elements.get(indexA);
        this.elements.set(indexA, this.elements.get(indexB));
        this.elements.set(indexB, oldElementA);
    }

    @Override
    public E peek() {
        validateSize();
        return this.elements.get(0);
    }

    @Override
    public E poll() {
        validateSize();

        E removed = this.elements.get(0);

        this.elements.set(0, this.elements.get(this.size() - 1));
        this.elements.remove(this.size() - 1);
        sink(0);

        return removed;
    }

    private void sink(int index) {

        int childAIndex = index * 2 + 1;
        int childBIndex = index * 2 + 2;

        if (!isValidIndex(childAIndex)) {
            return;
        }

        int swapIndex;

        if (!isValidIndex(childBIndex) ||
                elements.get(childAIndex).compareTo(elements.get(childBIndex)) < 0) {
            swapIndex = childAIndex;
        } else {
            swapIndex = childBIndex;
        }

        E currentElement = this.elements.get(index);

        if (currentElement.compareTo(this.elements.get(swapIndex)) > 0) {
            swapElements(index, swapIndex);
            sink(swapIndex);
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < elements.size();
    }

    @Override
    public void decrease(E element) {
        int index = -1;
        for (int i = 0; i < this.elements.size(); i++) {
            if (elements.get(i).equals(element)) {
                index = i;
                elements.get(i).decrease();
            }
        }

        this.surface(index);
    }

    private void validateSize() {
        if (size() == 0) {
            throw new IllegalStateException("Empty Heap");
        }
    }

}
