package javanut8.ch04.shapes;

interface Translatable {
    Translatable deltaX(double dx);
    Translatable deltaY(double dy);
    Translatable delta(double dx, double dy);
}
