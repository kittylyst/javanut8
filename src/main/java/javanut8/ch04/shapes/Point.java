package javanut8.ch04.shapes;

public record Point(double x, double y) implements Translatable {
    public Translatable deltaX(double dx) {
        return delta(dx, 0.0);
    }

    public Translatable deltaY(double dy) {
        return delta(0.0, dy);
    }

    public Translatable delta(double dx, double dy) {
        return new Point(x + dx, y + dy);
    }
}
