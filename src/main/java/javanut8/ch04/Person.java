package javanut8.ch04;

/**
 *
 * @author ben
 */
public class Person implements Vocal, Caller {

    public void call() {
        // Can do our own thing
        // or delegate to either interface
        // Vocal.super.call();
        // or
        // Caller.super.call();
    }
}
