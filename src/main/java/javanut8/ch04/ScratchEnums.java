package javanut8.ch04;

import javanut8.ch04.shapes.RegularPolygon;

import java.io.IOException;

public class ScratchEnums {

	private static ScratchEnums instance = null;

	// Constructor
	public ScratchEnums() {
		super();
	}

	/*
	 * This is where your actual code will go
	 */
	private void run() {
		RegularPolygon rp1 = RegularPolygon.TRIANGLE;
//		RegularPolygon rp2 = new RegularPolygon(3);
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		instance = new ScratchEnums();
		instance.run();
	}

}
