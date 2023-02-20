package javanut8.ch04;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScratchGenerics {

    private static ScratchGenerics instance = null;

    // Constructor
    public ScratchGenerics() {
        super();
    }

    private void run() {
		// Won't compile
//		List<Object> objects = new ArrayList<String>();
        // Is fine
        List<?> unknownObjects = new ArrayList<String>();

        Object o = new String("X");

        // This is completely legal
        String[] words = {"Hello World!"};
        Object[] objects = words;

        // Oh, dear, runtime error
//		objects[0] = new Integer(42);
        class Local {

            public void foo() {
                System.out.println("Foo!");
            }
        }

        Local l = new Local();
        l.foo();

        File dir = new File("/src");      // The directory to list

        String[] filelist = dir.list((f, s) -> {
            return s.endsWith(".java");
        });

    }

    private void run2() {
        List<Cat> cats = new ArrayList<Cat>();
        List<? extends Pet> pets = cats;
//        pets.add(new Cat()); // won't compile
        cats.add(new Cat());
    }

    private void run3() {
        NumberBox<Integer> ni = new NumberBox<>();
        // Won't compile
//        NumberBox<Object> no = new NumberBox<>();
        NumberBox n = new NumberBox();
        // Dangerous
        n.box(new Object());
        // Runtime error
        System.out.println(n.intValue());
        // ComparingBox<Object> cbo = new ComparingBox<>();
    }
    
    public static <T> T comma(T a, T b) {
        return a;
    }

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        instance = new ScratchGenerics();
        instance.run3();
    }

}
