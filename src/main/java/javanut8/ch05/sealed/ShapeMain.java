package javanut8.ch05.sealed;

public class ShapeMain {

    public static void main(String[] args) {
        Shape shape = new Shape.Rectangle(2.0, 1.0);

        if (shape instanceof Shape.Circle c) {
            System.out.println("Circle: "+ c.circumference());
        } else if (shape instanceof Shape.Rectangle r) {
            System.out.println("Rectangle: "+ r.circumference());
        }
    }

}
