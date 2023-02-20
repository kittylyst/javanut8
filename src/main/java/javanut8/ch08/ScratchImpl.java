package javanut8.ch08;

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
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		instance = new ScratchImpl();
		instance.run();
	}

}
