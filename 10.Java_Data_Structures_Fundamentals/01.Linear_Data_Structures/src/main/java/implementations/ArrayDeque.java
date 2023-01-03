package implementations;

import interfaces.Deque;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {

    private static final int STARTING_CAPACITY = 7;

    private Object[] elements;

    private int head;
    private int tail;
    private int size;

    public ArrayDeque() {
        elements = new Object[STARTING_CAPACITY];
        this.head = elements.length / 2;
        this.tail = this.head;
    }

    private void grow() {
        int newCapacity = elements.length * 2;
//        this.capacity *= 2;
//
//        Object[] newSlot = new Object[capacity];
//
//        System.arraycopy(elements, 0, newSlot, 0, elements.length);
//
//        this.elements = newSlot;
    }

    @Override
    public void add(E element) {

        if (tail == elements.length) {
            grow();
        }

        elements[tail] = element;

        if (head == tail) {
            head--;
        }
        tail++;
        size++;
    }

    @Override
    public void offer(E element) {
    }

    @Override
    public void addFirst(E element) {

    }

    @Override
    public void addLast(E element) {

    }

    @Override
    public void push(E element) {

    }

    @Override
    public void insert(int index, E element) {

    }

    @Override
    public void set(int index, E element) {

    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E get(Object object) {
        return null;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public E remove(Object object) {
        return null;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int capacity() {
        return 0;
    }

    @Override
    public void trimToSize() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
