package javanut8.ch03;

public class Car {
    public static final double LITRE_PER_100KM = 8.9;

    protected double topSpeed;

    protected double fuelTankCapacity;

    private int doors;

    public Car(double topSpeed, double fuelTankCapacity, 
               int doors) {
        this.topSpeed = topSpeed;
        this.fuelTankCapacity = fuelTankCapacity;
        this.doors = doors;
    }

    public double getTopSpeed() {
        return topSpeed;
    }

    public int getDoors() {
        return doors;
    }

    public double getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public double range() {
        return 100 * fuelTankCapacity / LITRE_PER_100KM;
    }
}
