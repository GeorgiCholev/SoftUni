package _1_Abstraction_and_Enum.Lab.PointInRectangle;

public class Rectangle {

    // bottom left corner coordinates
    private Point A;
    // Top right corner coordinates
    private Point C;

    public Rectangle(Point a, Point c) {
        A = a;
        C = c;
    }

    public boolean contains(Point p) {
        return A.lessOrEqual(p) && C.moreOrEqual(p);
    }
}
