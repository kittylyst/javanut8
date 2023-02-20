package javanut8.ch04;

/**
 *
 * @author ben
 * @param <T>
 */
public class ComparingBox<T extends Comparable<T>> extends Box<T>
                            implements Comparable<ComparingBox<T>> {
    @Override
    public int compareTo(ComparingBox<T> o) {
        if (value == null)
            return o.value == null ? 0 : -1;
        return value.compareTo(o.value);
    }    
}
