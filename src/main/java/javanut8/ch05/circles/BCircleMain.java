package javanut8.ch05.circles;

public class BCircleMain {

    public static void main(String[] args) {
        var cb = new BCircle.CircleBuilder();
        cb.x(1).y(2).r(3);
        var circle = cb.build();
    }

}
