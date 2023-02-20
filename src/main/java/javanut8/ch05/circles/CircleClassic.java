package javanut8.ch05.circles;

// This class represents a circle with immutable position and radius.
public class CircleClassic implements Comparable<CircleClassic> {
    // These fields hold the coordinates of the center and the radius.
    // They are private for data encapsulation and final for immutability
    private final int x, y, r;

    // The basic constructor: initialize the fields to specified values
    public CircleClassic(int x, int y, int r) {
        if (r < 0) throw new IllegalArgumentException("negative radius");
        this.x = x; this.y = y; this.r = r;
    }

    // This is a "copy constructor"--a useful alternative to clone()
    public CircleClassic(CircleClassic original) {
        x = original.x;   // Just copy the fields from the original
        y = original.y;
        r = original.r;
    }

    // Public accessor methods for the private fields.
    // These are part of data encapsulation.
    public int getX() { return x; }
    public int getY() { return y; }
    public int getR() { return r; }

    // Return a string representation
    @Override public String toString() {
        return String.format("center=(%d,%d); radius=%d", x, y, r);
    }

    // Test for equality with another object
    @Override public boolean equals(Object o) {
        // Identical references?
        if (o == this) return true;
        // Correct type and non-null?
        if (!(o instanceof CircleClassic)) return false;
        CircleClassic that = (CircleClassic) o;                 // Cast to our type
        if (this.x == that.x && this.y == that.y && this.r == that.r)
            return true;                          // If all fields match
        else
            return false;                         // If fields differ
    }

    // A hash code allows an object to be used in a hash table.
    // Equal objects must have equal hash codes.  Unequal objects are
    // allowed to have equal hash codes as well, but we try to avoid that.
    // We must override this method because we also override equals().
    @Override public int hashCode() {
        int result = 17;          // This hash code algorithm from the book
        result = 37*result + x;   // Effective Java, by Joshua Bloch
        result = 37*result + y;
        result = 37*result + r;
        return result;
    }

    // This method is defined by the Comparable interface. Compare
    // this Circle to that Circle.  Return a value < 0 if this < that
    // Return 0 if this == that. Return a value > 0 if this > that.
    // Circles are ordered top to bottom, left to right, and then by radius
    public int compareTo(CircleClassic that) {
        // Smaller circles have bigger y
        long result = (long)that.y - this.y;
        // If same compare l-to-r
        if (result==0) result = (long)this.x - that.x;
        // If same compare radius
        if (result==0) result = (long)this.r - that.r;

        // We have to use a long value for subtraction because the
        // differences between a large positive and large negative
        // value could overflow an int. But we can't return the long,
        // so return its sign as an int.
        return Long.signum(result);
    }
}
