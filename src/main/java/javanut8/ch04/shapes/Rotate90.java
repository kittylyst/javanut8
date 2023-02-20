package javanut8.ch04.shapes;

public sealed interface Rotate90 permits Circle, Rectangle {
    void clockwise();
    void antiClockwise();
}
