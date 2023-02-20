package javanut8.ch05.sealed;

public abstract sealed class Shape {
    public abstract double area();
    public abstract double circumference();

    public static final class Circle extends Shape {
        public static final double PI = 3.14159;
        private final double r;

        public Circle(double r) {
            if (r < 0.0) {
                throw new IllegalArgumentException("radius may not be negative.");
            }
            this.r = r;
        }

        public double area() { return PI * r * r; }
        public double circumference() { return 2 * PI * r; }
    }

    public static final class Rectangle extends Shape {
        private final double w, h;

        public Rectangle(double w, double h) {
            this.w = w;  this.h = h;
        }

        public double area() { return w*h; }
        public double circumference() { return 2*(w + h); }
    }

}
