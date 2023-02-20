package javanut8.ch04;

interface Vocal {
  default void call() {
    System.out.println("Hello!");
  }
}

