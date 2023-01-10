package solutions;

import java.util.ArrayList;
import java.util.function.Consumer;

import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;

    private BinarySearchTree(Node<E> root) {
        this.root = root;
    }

    public BinarySearchTree() {
    }

    public static class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;

        public Node(E value) {
            this.value = value;
        }

        private Node<E> getLeft() {
            return this.leftChild;
        }

        private Node<E> getRight() {
            return this.rightChild;
        }

        public E getValue() {
            return this.value;
        }

        private void setLeftChild(Node<E> leftChild) {
            this.leftChild = leftChild;
        }

        private void setRightChild(Node<E> rightChild) {
            this.rightChild = rightChild;
        }

        private void setValue(E value) {
            this.value = value;
        }
    }

    public void eachInOrder(Consumer<E> consumer) {
        inOrder(this.root, consumer);
    }

    private void inOrder(Node<E> node, Consumer<E> consumer) {
        if (node == null) {
            return;
        }

        inOrder(node.getLeft(), consumer);
        consumer.accept(node.value);
        inOrder(node.getRight(), consumer);
    }

    public Node<E> getRoot() {
        return this.root;
    }

    public void insert(E element) {

        Node<E> toInsert = new Node<>(element);

        if (this.root == null) {
            this.root = toInsert;
            return;
        }

        insertPast(toInsert, this.root);
    }

    private void insertPast(Node<E> toInsert, Node<E> node) {

        if (compareResult(toInsert.getValue(), node.getValue()) < 0) {
            Node<E> left = node.getLeft();

            if (left == null) {
                node.setLeftChild(toInsert);
                return;
            }

            insertPast(toInsert, node.leftChild);
        } else {
            Node<E> right = node.getRight();

            if (right == null) {
                node.setRightChild(toInsert);
                return;
            }

            insertPast(toInsert, node.rightChild);
        }
    }

    private int compareResult(E elementA, E elementB) {
        return elementA.compareTo(elementB);
    }

    public boolean contains(E element) {
        return searchElement(element) != null;
    }

    public BinarySearchTree<E> search(E element) {

        Node<E> node = searchElement(element);

        return node == null ? null : new BinarySearchTree<E>(node);
    }

    private Node<E> searchElement(E element) {
        Node<E> node = this.root;

        while (node != null) {

            E currentValue = node.getValue();

            int compareResult = compareResult(element, currentValue);
            if (compareResult == 0) {
                break;
            } else if (compareResult < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }

        return node;
    }

    public List<E> range(E first, E second) {
        List<E> inRange = new ArrayList<>();

        addInRange(this.root, first, second, inRange);

        return inRange.size() > 0 ? inRange : null;
    }

    private void addInRange(Node<E> node, E min, E max, List<E> inRange) {
        if (node == null) {
            return;
        }

        E value = node.getValue();
        int floor = compareResult(min, value);
        int ceil = compareResult(max, value);

        if (floor < 0) {
            addInRange(node.getLeft(), min, max, inRange);
        }

        if (floor <= 0 && ceil >= 0) {
            inRange.add(value);
        }

        if (ceil > 0) {
            addInRange(node.getRight(), min, max, inRange);
        }

    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node<E> left = this.root.getLeft();
        if (left == null) {
            this.root = this.root.getRight();
        } else {
            deleteMin(this.root, left);
        }
    }

    private void deleteMin(Node<E> prevNode, Node<E> currentNode) {
        Node<E> left = currentNode.getLeft();
        if (left == null) {
            Node<E> right = currentNode.getRight();
            prevNode.setLeftChild(right);
        } else {
            deleteMin(currentNode, left);
        }
    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node<E> right = this.root.getRight();
        if (right == null) {
            this.root = this.root.getLeft();
        } else {
            deleteMax(this.root, right);
        }
    }

    private void deleteMax(Node<E> prevNode, Node<E> currentNode) {
        Node<E> right = currentNode.getRight();
        if (right == null) {
            prevNode.setRightChild(currentNode.getLeft());
        } else {
            deleteMax(currentNode, right);
        }
    }
}
