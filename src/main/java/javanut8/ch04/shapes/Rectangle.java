package javanut8.ch04.shapes;

public final class Rectangle extends Shape implements Rotate90 {
	private double w, h;                               // Instance data
	public Rectangle(double w, double h) {               // Constructor
		this.w = w;
		this.h = h;
	}
	public double getWidth() { return w; }               // Accessor method
	public double getHeight() { return h; }              // Another accessor
	  
	@Override
	public double area() { return w*h; }                 // Implementations of abstract methods.

	@Override
	public double circumference() { return 2*(w + h); }

	@Override
	public void clockwise() {
		// Swap width and height
		double tmp = w;
		w = h;
		h = tmp;
	}

	@Override
	public void antiClockwise() {
		// Swap width and height
		double tmp = w;
		w = h;
		h = tmp;
	}
}
