package javanut8.ch05;

import java.io.IOException;
import static java.lang.System.out;

public class ScratchValues {

	private static ScratchValues instance = null;

	// Constructor
	public ScratchValues() {
		super();
	}

    public void manipulate(Circle circle) {
        circle = new Circle(3);
    }

	/*
	 * This is where your actual code will go
	 */
	private void run() {
		Circle c = new Circle(2);
        manipulate(c);
        System.out.println("Radius: "+ c.getRadius());
        out.println("Radius: "+ c.getRadius());
	}
	
	/**
	 * @param args
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException {
		instance = new ScratchValues();
		instance.run();
	}

}
