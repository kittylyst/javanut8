package javanut8.ch04.lambdas;

@FunctionalInterface
public interface MyIntProvider {
    int run() throws InterruptedException;
}
