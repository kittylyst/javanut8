package javanut8.ch04;

/**
 *
 * @author ben
 */
public class Weird {
    public static void main(String[] args) {
        IntHolder[] holders = new IntHolder[10];
        for (int i = 0; i < 10; i++) {
            final int fi = i;

            holders[i] = () -> {
                return fi;
            };
        }
        // The lambda is now out of scope, but we have 10 valid instances
        // of the class the lambda has been converted to in our array. 
        // The local variable fi is not in our scope here, but is still
        // in scope for the getValue() method of each of those 10 objects.
        // So call getValue() for each object and print it out. 
        // This prints the digits 0 to 9.
        for (int i = 0; i < 10; i++) {
            System.out.println(holders[i].getValue());
        }
    }
}
