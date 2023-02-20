package javanut8.ch06;

import java.io.IOException;

public class ScratchCashPoint {

	private static ScratchCashPoint instance = null;

	// Constructor
	public ScratchCashPoint() {
		super();
	}

	/*
	 * This is where your actual code will go
	 */
	private void run() {
		
	}
	
	/**
	 * @param args
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException {
		instance = new ScratchCashPoint();
		instance.run();
	}

}
