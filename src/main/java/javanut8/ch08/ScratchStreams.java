package javanut8.ch08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScratchStreams {

	private static ScratchStreams instance = null;

	// Constructor
	public ScratchStreams() {
		super();
	}

	/*
	 * This is where your actual code will go
	 */
	private void run() {
		try (InputStream is = new FileInputStream("/Users/boxcat/cluster.txt");) {
			byte[] buf = new byte[4096];
			int len, count = 0;
			while ((len = is.read(buf)) > 0) {
				for (int i=0; i<len; i++)
					if (buf[i] == 97) count++; 
			}
			System.out.println("'a's seen: "+ count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void run2(String filename) {
		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			  String line;

			  while((line = in.readLine()) != null) {
			    System.out.println(line); 
			  }
			} catch (IOException e) {
			  // Handle FileNotFoundException, etc. here
			}
	}

    private static Pattern SHELL_META_START = Pattern.compile("^#(\\w+)\\s*(\\w+)?");
	
	private void run3() {
		try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
			  String line;

			  READ: while((line = console.readLine()) != null) {
          		// Check for shell metas
              	Matcher m = SHELL_META_START.matcher(line);
              	if (m.find()) {
              		String metaName = m.group(1);
              		String arg = m.group(2);
              		doMeta(metaName, arg);
              		continue READ;
              	}
                
			    System.out.println(line); 
			  }
			} catch (IOException e) {
			  // Handle FileNotFoundException, etc. here
			}
	}
	
	private void doMeta(String metaName, String arg) {
		// TODO Auto-generated method stub
		
	}

	private void run4() {
		File f = new File(System.getProperty("user.home") + File.separator + ".bashrc");
		try (PrintWriter out
			   = new PrintWriter(new BufferedWriter(new FileWriter(f)))) {
			out.println("## Automatically generated config file. DO NOT EDIT!");
		} catch (IOException iox) { 
			// Handle exceptions
		}
	}
	
	private void run5() {
		try (BufferedReader in = new BufferedReader(new FileReader("/Users/ben/.bash_profile"));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/Users/ben/bash_profile.bak")))) {
		  String line;

		  while((line = in.readLine()) != null) {
		    out.println(line); 
		  }
		} catch (IOException e) {
		  // Handle FileNotFoundException, etc. here
		}
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		instance = new ScratchStreams();
		instance.run();
	}

}
