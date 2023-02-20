package javanut8.ch03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCar {
    @Test
    public void simple_car_test() {
        Car c = new Car(180.0, 50, 4);
        assertEquals(561.79775, c.range(), 0.001, "Expected simple range of 561.79775");
        SportsCar sc = new SportsCar(200);
        assertEquals(c.range(), sc.range(), 0.001, "Expected simple range of 561.79775");
        sc = new SportsCar(300);
        assertEquals(374.53183, sc.range(), 0.001, "Expected simple range of 374.53183");
        sc = new SportsCar(400);
        assertEquals(c.range() / 2.0, sc.range(), 0.001, "Expected simple range of ~280");
    }
}
