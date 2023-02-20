package javanut8.ch05;

import java.io.IOException;

public class ScratchImpl {

	private static ScratchImpl instance = null;

	// Constructor
	public ScratchImpl() {
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
		instance = new ScratchImpl();
		instance.run();
	}

}
