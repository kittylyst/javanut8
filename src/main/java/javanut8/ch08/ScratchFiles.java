package javanut8.ch08;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ScratchFiles {

	private static ScratchFiles instance = null;

	// Constructor
	public ScratchFiles() {
		super();
	}

	/*
	 * This is where your actual code will go
	 */
	private void run(Path target) throws IOException {
		FileTime fTime = Files.getLastModifiedTime(target);
		System.out.println(fTime.to(TimeUnit.SECONDS));

		Map<String, ?> attrs = Files.readAttributes(target, "*");
		System.out.println(attrs);
	}
	
	private void run2() throws URISyntaxException, MalformedURLException {
		Path p = Paths.get("/Users/boxcat/cluster.txt");
		System.out.println(p);
//		p = Paths.get(new URI("http://www.oreilly.com/"));
		Path p2 = Paths.get(new URI("file:///Users/boxcat/cluster.txt"));
		System.out.println(p2);
		System.out.println(p2.equals(p));

		File f = p.toFile();
		System.out.println(f.isDirectory());
		Path p3 = f.toPath();
		System.out.println(p3.equals(p));
	}
	
	private void run3() {
		File inputFile = new File("input.txt");
		try (InputStream in = new FileInputStream(inputFile)) {
		  Files.copy(in, Paths.get("output.txt"));
		} catch(IOException ex) {
		  ex.printStackTrace();
		}
	}

	private void run4() throws IOException {
		Files.copy(Paths.get("input.txt"), Paths.get("output.txt"), 
				StandardCopyOption.REPLACE_EXISTING);
	}

	
    private void run5() throws IOException {
    	Path tempJar = Paths.get("sample.jar");
        try (FileSystem workingFS = FileSystems.newFileSystem(tempJar, (ClassLoader) null)) {

            Path pathForFile = workingFS.getPath("/hello.txt");
            List<String> ls = new ArrayList<>();
            ls.add("Hello World!");
            
            Files.write(pathForFile, ls, Charset.defaultCharset(), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
         }
    }

    
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		instance = new ScratchFiles();
		instance.run(Paths.get("/Users/boxcat/cluster.txt"));
		instance.run2();
//		instance.run3();
		instance.run4();
		instance.run5();
	}

}
