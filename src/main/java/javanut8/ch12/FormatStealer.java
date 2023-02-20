package javanut8.ch12;

import java.util.Arrays;
import sun.text.resources.FormatData;

public final class FormatStealer extends FormatData {
    public static void main(String[] args) {
        FormatStealer fs = new FormatStealer();
        fs.run();
    }

    private void run() {
        String[] s = (String[]) handleGetObject("japanese.Eras");
        System.out.println(Arrays.toString(s));

        Object[][] contents = getContents();
        Object[] eraData = contents[14];
        Object[] eras = (Object[])eraData[1];
        System.out.println(Arrays.toString(eras));
    }
}
