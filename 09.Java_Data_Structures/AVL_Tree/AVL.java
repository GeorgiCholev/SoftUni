import java.util.function.Consumer;

public class AVL<T extends Comparable<T>> {

    private Node<T> root;

    public Node<T> getRoot() {
        return this.root;
    }

    public boolean contains(T item) {
        Node<T> node = this.search(this.root, item);
        return node != null;
    }

    public void insert(T item) {
        this.root = this.insert(this.root, item);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node<T> node, Consumer<T> action) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, action);
        action.accept(node.value);
        this.eachInOrder(node.right, action);
    }

    private Node<T> insert(Node<T> node, T item) {
        if (node == null) {
            return new Node<>(item);
        }

        int result = item.compareTo(node.value);
        if (result < 0) {
            node.left = this.insert(node.left, item);
        } else if (result > 0) {
            node.right = this.insert(node.right, item);
        }

        recalculateHeight(node);

        return balance(node);
    }

    private Node<T> balance(Node<T> node) {
        int balancingFactor = getBalancingFactor(node);

        if (balancingFactor == -2) { // left heavy

            if (getBalancingFactor(node.left) == 1) { // left child right heavy
                node.left = rotateLeft(node.left);
            }

            return rotateRight(node);

        } else if (balancingFactor == 2) { // right heavy

            if (getBalancingFactor(node.right) == -1) { // right child left heavy
                node.right = rotateRight(node.right);
            }

            return rotateLeft(node);

        }

        return node;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;

        recalculateHeight(node);
        recalculateHeight(leftChild);

        return leftChild;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;

        recalculateHeight(node);
        recalculateHeight(rightChild);

        return rightChild;
    }

    private int getHeight(Node<T> node) {
        return node == null ? 0 : node.height;
    }

    private int getBalancingFactor(Node<T> node) {
        return getHeight(node.right) - getHeight(node.left);
    }

    private void recalculateHeight(Node<T> node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    private Node<T> search(Node<T> node, T item) {
        if (node == null) {
            return null;
        }

        int result = item.compareTo(node.value);
        if (result < 0) {
            return search(node.left, item);
        } else if (result > 0) {
            return search(node.right, item);
        }

        return node;
    }
}
