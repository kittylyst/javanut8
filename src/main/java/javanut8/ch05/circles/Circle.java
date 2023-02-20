package javanut8.ch05.circles;

public record Circle(int x, int y, int r) {
    // Primary constructor
    public Circle {
        if (r < 0) {
            throw new IllegalArgumentException("negative radius");
        }
    }

    // Factory method playing the role of the copy constructor
    public static Circle of(Circle original) {
        return new Circle(original.x, original.y, original.r);
    }
}
