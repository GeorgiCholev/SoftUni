package _1_Abstraction_and_Enum.Lab.PointInRectangle;

public class Point {

    //Coordinates (x, y)
    private int X;
    private int Y;

    public Point(int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public boolean lessOrEqual(Point p) {
        return this.X <= p.getX() && this.Y <= p.getY();
    }

    public boolean moreOrEqual(Point p) {
        return this.X >= p.getX() && this.Y >= p.getY();
    }
}
