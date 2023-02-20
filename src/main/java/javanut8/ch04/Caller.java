package javanut8.ch04;

interface Caller {
  default void call() {
    Switchboard.placeCall(this);
  }
}

