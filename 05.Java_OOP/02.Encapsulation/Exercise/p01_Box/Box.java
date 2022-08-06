package _2_Encapsulation.Exercise.p01_Box;

public class Box {

    private double length;
    private double width;
    private double height;


    public Box(double length, double width, double height) {
        setLength(length);
        setWidth(width);
        setHeight(height);
    }

    public double calculateSurfaceArea() {
        return (2 * length * width) + calculateLateralSurfaceArea();
    }

    public double calculateLateralSurfaceArea() {
        return (2 * length * height) + (2 * width * height);
    }

    public double calculateVolume() {
        return length * width * height;
    }



    private void setLength(double length) {
        validateParameter(length, "Length");
        this.length = length;
    }

    private void setWidth(double width) {
        validateParameter(width, "Width");
        this.width = width;
    }

    private void setHeight(double height) {
        validateParameter(height, "Height");
        this.height = height;
    }

    private void validateParameter(double parameterToValidate, String field) {
        if (parameterToValidate <= 0) {
            throw new IllegalArgumentException(field + " cannot be zero or negative.");
        }
    }
}
