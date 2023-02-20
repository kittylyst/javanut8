package javanut8.ch04;

import java.io.File;

/**
 *
 * @author ben
 */
public class ScratchLambdas {

    public static void main(String[] args) {
        ScratchLambdas sl = new ScratchLambdas();
        sl.run();
    }

    private void run() {
        File dir = new File("/src");      // The directory to list

        String[] filelist = dir.list((d, fName) -> fName.endsWith(".java"));
    }

}
