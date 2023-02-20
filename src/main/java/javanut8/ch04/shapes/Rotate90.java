package javanut8.ch04.shapes;

//tag::ROT90[]
public sealed interface Rotate90 permits Circle, Rectangle {
    void clockwise();
    void antiClockwise();
}
//end::ROT90[]