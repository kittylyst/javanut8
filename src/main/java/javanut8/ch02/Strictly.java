package javanut8.ch02;

public class Strictly {
    // Just here to show the build warning:
    //    java: as of release 17, all floating-point expressions are evaluated strictly and 'strictfp' is not required
    strictfp public double getValue() {
        return 0.0;
    }
}
