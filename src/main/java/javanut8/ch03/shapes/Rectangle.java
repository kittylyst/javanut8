package javanut8.ch03.shapes;

public class Rectangle extends Shape {
    // Instance data
    protected double w, h;

    // Constructor                               
    public Rectangle(double w, double h) {               
        this.w = w;  this.h = h;
    }

    // Accessor methods
    public double getWidth() { return w; }               
    public double getHeight() { return h; }

    // Implementation of abstract methods             
    public double area() { return w*h; }                 
    public double circumference() { return 2*(w + h); }   
}
