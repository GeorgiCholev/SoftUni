package Generics_08.Lab;

public class Scale<T extends Comparable<T>> {
    private T left;
    private T right;

    public Scale(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getHeavier() {
        int result = left.compareTo(right);
        switch (result) {
            case 1:
                return left;
            case -1:
                return right;
            default:
                return null;
        }
    }
}
