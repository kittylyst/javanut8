package javanut8.ch04;

/**
 *
 * @author ben
 * @param <T>
 */
public class NumberBox<T extends Number> extends Box<T> {

    public int intValue() {
        return value.intValue();
    }
}
