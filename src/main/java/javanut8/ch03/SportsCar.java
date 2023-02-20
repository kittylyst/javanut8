package javanut8.ch03;

public class SportsCar extends Car {

    private double efficiency;

    public SportsCar(double topSpeed) {
        super(topSpeed, 50.0, 2);
        if (topSpeed > 200.0) {
            efficiency = 200.0 / topSpeed;
        } else {
            efficiency = 1.0;
        }
    }

    public double getEfficiency() {
        return efficiency;
    }

    @Override
    public double range() {
        return 100 * fuelTankCapacity * efficiency / LITRE_PER_100KM;
    }

}
