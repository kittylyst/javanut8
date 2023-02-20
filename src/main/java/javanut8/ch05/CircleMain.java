package javanut8.ch05;

public class CircleMain {
    public static void main(String[] args) {
        var c = new Circle(1);
        var c2 = c.clone();
        System.out.println(c2.getRadius());

        var m = new CircleMain();
        try {
            m.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

}
