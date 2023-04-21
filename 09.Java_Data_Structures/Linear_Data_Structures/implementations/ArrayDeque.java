package implementations;

import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {

    private static final int STARTING_CAPACITY = 3;

    private Object[] elements;

    private int head;
    private int tail;
    private int size;

    public ArrayDeque() {
        this.elements = new Object[STARTING_CAPACITY];
        this.head = this.elements.length / 2;
        this.tail = this.head;
    }


    @Override
    public void add(E element) {
        if (this.tail == this.elements.length - 1) {
            this.grow();
        }

        if (this.isEmpty()) {
            this.elements[tail] = element;
        } else {
            this.elements[++tail] = element;
        }
        this.size++;
    }

    @Override
    public void offer(E element) {
        this.add(element);
    }

    @Override
    public void addLast(E element) {
        this.add(element);
    }


    @Override
    public void addFirst(E element) {

        if (this.head == 0) {
            this.grow();
        }

        if (this.isEmpty()) {
            this.elements[head] = element;
        } else {
            this.elements[--head] = element;
        }

        this.size++;
    }

    @Override
    public void push(E element) {
        this.addFirst(element);
    }


    @Override
    public E get(int index) {

        int slotIndex = this.head + index;
        this.validateArrayIndex(index, slotIndex);

        return this.getElementAt(slotIndex);
    }

    @Override
    public E get(Object object) {

        if (object == null) {
            return null;
        }

        for (int i = this.head; i <= this.tail; i++) {
            if (elements[i].equals(object)) {
                return this.getElementAt(i);
            }
        }

        return null;
    }

    @Override
    public void set(int index, E element) {

        int slotIndex = index + this.head;
        this.validateArrayIndex(index, slotIndex);

        this.elements[slotIndex] = element;
    }

    @Override
    public void insert(int index, E element) {

        int slotIndex = index + this.head;

        validateArrayIndex(index, slotIndex);
        int arrayMiddle = this.size / 2;

        if (arrayMiddle > index) {
            shiftLeftInsert(index, slotIndex, element);
        } else {
            shiftRightInsert(index, slotIndex, element);
        }
    }


    @Override
    public E peek() {
        if (this.isEmpty()) {
            return null;
        }

        return this.getElementAt(this.head);
    }

    @Override
    public E removeFirst() {

        if (this.isEmpty()) {
            return null;
        }

        E removed = this.getElementAt(this.head);
        this.elements[head] = null;
        size--;

        if (!this.isEmpty()) {
            head++;
        }

        return removed;
    }

    @Override
    public E pop() {
        return this.removeFirst();
    }

    @Override
    public E poll() {
        return this.removeFirst();
    }


    @Override
    public E removeLast() {

        if (this.isEmpty()) {
            return null;
        }

        E removed = this.getElementAt(tail);
        this.elements[tail] = null;
        size--;

        if (!this.isEmpty()) {
            tail--;
        }

        return removed;
    }

    @Override
    public E remove(int index) {

        int slotIndex = this.head + index;
        validateArrayIndex(index, slotIndex);

        E removed = this.getElementAt(index);

        int arrayMiddle = this.size / 2;
        if (arrayMiddle > index) {
            shiftLeftRemove(slotIndex);
            this.removeFirst();
        } else {
            shiftRightRemove(slotIndex);
            this.removeLast();
        }

        return removed;
    }

    @Override
    public E remove(Object object) {
        if (object == null || this.isEmpty()) {
            return null;
        }

        int slotIndex = -1;
        E removed = null;

        for (int i = this.head; i <= this.tail; i++) {
            if (this.elements[i].equals(object)) {
                removed = this.getElementAt(i);
                slotIndex = i;
                break;
            }
        }

        if (removed == null) {
            return null;
        }

        int arrayIndex = slotIndex - this.head;
        int arrayMiddle = this.size / 2;
        if (arrayMiddle > arrayIndex) {
            shiftLeftRemove(slotIndex);
            this.removeFirst();
        } else {
            shiftRightRemove(slotIndex);
            this.removeLast();
        }

        return removed;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public void trimToSize() {
        if (this.isEmpty()) {
            return;
        }

        Object[] newSlot = new Object[this.size];

        System.arraycopy(this.elements, this.head, newSlot, 0, newSlot.length);

        this.elements = newSlot;
        this.head = 0;
        this.tail = this.size - 1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int pointer = head;

            @Override
            public boolean hasNext() {
                return pointer <= tail;
            }

            @Override
            public E next() {
                return getElementAt(pointer++);
            }
        };
    }


    private void grow() {
        int slotCapacity = (this.elements.length * 2) + 1;
        Object[] newSlot = new Object[slotCapacity];

        int midSlotIndex = slotCapacity / 2;
        int arrayStartIndex = midSlotIndex - this.size / 2;

        int oldSlotIndex = this.head;

        this.head = arrayStartIndex;
        this.tail = arrayStartIndex + this.size - 1;

        for (int i = this.head; i <= this.tail; i++) {
            newSlot[i] = this.elements[oldSlotIndex++];
        }

        this.elements = newSlot;
    }

    @SuppressWarnings("unchecked")
    private E getElementAt(int index) {
        return (E) this.elements[index];
    }

    private void validateArrayIndex(int arrayIndex, int slotIndex) {
        if (slotIndex < this.head || slotIndex > this.tail || this.isEmpty()) {
            throw new IndexOutOfBoundsException("Not valid index " + arrayIndex);
        }
    }

    private void shiftRightRemove(int slotIndex) {
        for (int i = slotIndex; i < this.tail; i++) {
            this.elements[i] = this.elements[i + 1];
        }
    }

    private void shiftLeftRemove(int slotIndex) {
        for (int i = slotIndex; i > this.head; i--) {
            this.elements[i] = this.elements[i - 1];
        }
    }

    private void shiftRightInsert(int index, int slotIndex, E toInsert) {
        E addLast = this.getElementAt(this.tail);

        for (int i = this.tail; i > slotIndex; i--) {
            elements[i] = elements[i  - 1];
        }

        this.addLast(addLast);
        this.set(index, toInsert);
    }

    private void shiftLeftInsert(int index, int slotIndex, E toInsert) {
        E addFirst = this.getElementAt(this.head);

        for (int i = this.head; i < slotIndex; i++) {
            elements[i] = elements[i + 1];
        }

        this.addFirst(addFirst);
        this.set(index, toInsert);
    }
}
