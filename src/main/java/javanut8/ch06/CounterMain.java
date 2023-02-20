package javanut8.ch06;

/**
 *
 * @author ben
 */
public class CounterMain {

    public static void main(String[] args) throws InterruptedException {
    Counter c = new Counter();
    int REPEAT = 10_000_000;
    Runnable r = () -> {
        for (int i = 0; i < REPEAT; i++) {
            c.increment();
        }
    };
    Thread t1 = new Thread(r);
    Thread t2 = new Thread(r);
    
    t1.start();
    t2.start();
    t1.join();
    t2.join();

    int anomaly = (2 * REPEAT) - c.getCounter();
    double perc = ((double) anomaly * 100) / (2 * REPEAT);
    System.out.println("Lost updates: "+ anomaly +" ; % = " + perc);
    }
}
