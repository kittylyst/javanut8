package javanut8.ch08;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import static java.util.Map.entry;

public class ScratchBuffers {

	private static ScratchBuffers instance = null;

	// Constructor
	public ScratchBuffers() {
		super();
	}

	/*
	 * This is where your actual code will go
	 */
	private void run() {
		ByteBuffer b = ByteBuffer.allocateDirect(65536);
		ByteBuffer b2 = ByteBuffer.allocate(4096); 


		byte[] data = {1, 2, 3}; 
		ByteBuffer b3 = ByteBuffer.wrap(data); 

		b.order(ByteOrder.BIG_ENDIAN);

		b.put(data); 
		b.put((byte)42); 
		b.put(0, (byte)9);
		b.putChar('x');
		b.putInt(0xcafebabe);

		int capacity = b.capacity(); 
		int position = b.position(); 		
		int limit = b.limit();
		int remaining = b.remaining();
		boolean more = b.hasRemaining();

	}

	private void foo() {
        Set<String> set = Set.of("Hello", "World", "from", "Java");
        Map<String, Double> capitals = Map.of("Barcelona", 22.5, "New York", 28.3);
        Map<String, Double> caps = Map.ofEntries(entry("Barcelona", 22.5), entry("New York", 28.3));

    }

	private void run2() throws IOException {
		File f = new File("input.txt");
		try (RandomAccessFile raf = new RandomAccessFile(f,"rw");
				FileChannel fc = raf.getChannel();) {
			MappedByteBuffer mbf = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size());
			byte[] b = new byte[(int)fc.size()];
			mbf.get(b, 0, b.length);
			for (int i=0; i<fc.size(); i++) {
				b[i] = 0; // Won't be written back to the file, we're a copy
			}
			mbf.position(0);
			mbf.put(b); // Zeros the file
		}
	}
	
	private void run3() throws IOException, InterruptedException, ExecutionException {
		try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get("input.txt"))) {
			  ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 100);
			  Future<Integer> result = channel.read(buffer, 0);

			  while(!result.isDone()) {
			    // Do some other useful work....
			  }

			  System.out.println("Bytes read: " + result.get());
		}
	}

	private void run4() throws IOException, InterruptedException, ExecutionException {
		byte[] data = {2, 3, 5, 7, 11, 13, 17, 19, 23}; 
		ByteBuffer buffy = ByteBuffer.wrap(data);
		CompletionHandler<Integer,Object> h = new CompletionHandler<>() {
			public void completed(Integer written, Object o) {
				System.out.println("Bytes written: " + written);
			}

			public void failed(Throwable x, Object o) {
				System.out.println("Asynch write failed: "+ x.getMessage());
			}
		};
		
		try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get("primes.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
			channel.write(buffy, 0, null, h);
			Thread.sleep(1000); // Needed so we don't exit too quickly
		}
	}
	
	public void run5() throws IOException, InterruptedException {
		boolean shutdown = false;
		try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
			  Path dir = FileSystems.getDefault().getPath("/Users/boxcat");
			  dir.register(watcher,
					  StandardWatchEventKinds.ENTRY_CREATE,
					  StandardWatchEventKinds.ENTRY_MODIFY,
					  StandardWatchEventKinds.ENTRY_DELETE);

			  while(!shutdown) {
			    WatchKey key = watcher.take();
			    for (WatchEvent<?> event: key.pollEvents()) {
			    	Object o = event.context();
			    	if (o instanceof Path) {
			    		System.out.println("File altered: "+ o);
			    	}
			    }
			    key.reset();
			  }
		}
	}

	private void run6() throws IOException, InterruptedException {
		try(DirectoryStream<Path> stream = 
				Files.newDirectoryStream(Paths.get("/Users/boxcat/projects"), "*.java")) {
			for (Path p : stream) {                                                                  
				System.out.println(p +": "+ Files.size(p));
			}                                                               
		}
	}

	/*
	private static class FileFinder extends SimpleFileVisitor<Path> {
		
		public FileVisitResult visitFile() {
			
		}
		
	}*/
	
	private void run7() throws IOException, InterruptedException {
		final Path homeDir = Paths.get("/Users/boxcat/projects/openjdk/tortuga");
		Files.find(homeDir, 255, (p, attrs) -> p.toString().endsWith(".java"))
		     .forEach(q -> {System.out.println(q.normalize());});

//		Files.find(homeDir, 255, (p, attrs) -> isJavaFile.matcher(p.toString()).find())
//	     .forEach(q -> {System.out.println(q.toFile().getCanonicalPath());});
		
		System.out.println("In run7()");
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		instance = new ScratchBuffers();
		instance.run();
		instance.run2();
		instance.run3();
		instance.run4();
		instance.run6();
		instance.run7();
	}

}
