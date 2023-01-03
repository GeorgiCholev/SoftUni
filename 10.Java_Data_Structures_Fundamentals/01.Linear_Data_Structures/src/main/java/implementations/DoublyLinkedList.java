package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class DoublyLinkedList<E> implements LinkedList<E> {

    private static class Node<E> {
        private final E element;

        private Node<E> next;

        private Node<E> prev;

        public Node(E element) {
            this.element = element;
        }
    }

    private int size;

    private Node<E> head;

    private Node<E> tail;

    @Override
    public void addFirst(E element) {

        Node<E> newNode = new Node<>(element);

        if (this.head == null) {
            this.tail = newNode;
        } else {
            Node<E> currentHead = this.head;
            currentHead.prev = newNode;
            newNode.next = currentHead;
        }
        this.head = newNode;

        size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);

        if (head == null) {
            this.head = newNode;
        } else {
            Node<E> currentTail = this.tail;
            currentTail.next = newNode;
            newNode.prev = currentTail;
        }
        this.tail = newNode;

        size++;
    }

    @Override
    public E removeFirst() {
        ensureNotEmpty();

        Node<E> nodeToRemove = this.head;
        E elementToRemove = nodeToRemove.element;

        if (size == 1) {
            emptyListPointers();
        } else {
            Node<E> newHead = nodeToRemove.next;
            newHead.prev = null;
            this.head = newHead;
        }

        size--;
        return elementToRemove;
    }

    @Override
    public E removeLast() {
        ensureNotEmpty();

        Node<E> nodeToRemove = this.tail;
        E elementToRemove = nodeToRemove.element;

        if (size == 1) {
            emptyListPointers();
        } else {
            Node<E> newTail = nodeToRemove.prev;
            newTail.next = null;
            this.tail = newTail;
        }

        size--;
        return elementToRemove;
    }

    @Override
    public E getFirst() {
        return this.head.element;
    }

    @Override
    public E getLast() {
        return this.tail.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }


    private void emptyListPointers() {
        this.head = null;
        this.tail = null;
    }

    private void ensureNotEmpty() {
        if (this.size == 0) {
            throw new IllegalStateException("Illegal remove for empty LinkedList");
        }
    }
}
