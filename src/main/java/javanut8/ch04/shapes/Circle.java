package javanut8.ch04.shapes; // Specify a package for the class

public final class Circle extends Shape implements Rotate90 {
    // This is a generally useful constant, so we keep it public
    public static final double PI = 3.14159;

    protected double r;     // Radius is hidden but visible to subclasses

    // A method to enforce the restriction on the radius
    // This is an implementation detail that may be of interest to subclasses
    protected void checkRadius(double radius) {
        if (radius < 0.0)
            throw new IllegalArgumentException("radius may not be negative.");
    }

    // The non-default constructor
    public Circle(double r) {
        checkRadius(r);
        this.r = r;
    }

    // Public data accessor methods
    public double getRadius() { return r; }
    public void setRadius(double r) {
        checkRadius(r);
        this.r = r;
    }

    // Methods to operate on the instance field
    public double area() { return PI * r * r; }
    public double circumference() { return 2 * PI * r; }

    @Override
    public void clockwise() {
        // No-op, circles are rotation-invariant
    }

    @Override
    public void antiClockwise() {
        // No-op, circles are rotation-invariant
    }
}