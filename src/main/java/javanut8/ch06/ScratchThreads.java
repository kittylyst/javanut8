package javanut8.ch06;

public class ScratchThreads {

	private static ScratchThreads instance = null;

	// Constructor
	public ScratchThreads() {
		super();
	}

	/*
	 * This is where your actual code will go
	 */
	private void run() {
        Thread t = new Thread(() -> {System.out.println("Hello Thread");});
        t.start();
	}


    public void run2() {
        // This thread just throws an exception
        Thread handledThread = new Thread(() -> { throw new UnsupportedOperationException(); });

    // Giving threads a name helps with debugging
        handledThread.setName("My Broken Thread");

    // Here's a handler for the error.
        handledThread.setUncaughtExceptionHandler((t, e) -> {
            System.err.printf("Exception in thread %d '%s':" +
                    "%s at line %d of %s%n",
                    t.getId(),    // Thread id
                    t.getName(),  // Thread name
                    e.toString(), // Exception name and message
                    e.getStackTrace()[0].getLineNumber(),
                    e.getStackTrace()[0].getFileName());
        });
        handledThread.start();

    }

    public Thread run3() {
        // This thread just throws an exception
        Thread handledThread = new Thread(() -> { throw new UnsupportedOperationException(); });
        handledThread.start();
        return handledThread;
    }
    
    public void run4() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

        /**
         * @param args
         * @throws java.io.IOException
         */
    public static void main(String[] args) throws Exception {
		instance = new ScratchThreads();
		instance.run();
        Thread ret = instance.run3();
        instance.run2();
        instance.run4();
        ret.join();
        Thread.sleep(1000);
        System.out.println("program finishes normally");
	}

}
