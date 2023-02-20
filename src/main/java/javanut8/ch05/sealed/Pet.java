package javanut8.ch05.sealed;

/**
 *
 * @author ben
 */
public abstract sealed class Pet permits Cat, Dog {
    private final String name;

    protected Pet(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }


}
