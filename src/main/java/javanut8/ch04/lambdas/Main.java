package javanut8.ch04.lambdas;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        MyIntProvider prov = () -> 42;
        Supplier<Integer> sup = () -> 42;
        Callable<Integer> callMe = () -> 42;

        MyRunnable myR = () -> System.out.println("Hello");
        Runnable r = (Runnable)myR;
        r.run();
    }
}
