package javanut8.ch09;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Text {
    public static void main(String... args) {
        var s = String.format("The %d pet is a %s: %b?%n", 1, "cat", true);
        System.out.println(s);

        s = "The %d pet is a %s: %b?%n".formatted(1, "cat", true);

        var locale = Locale.US;
//        var locale = Locale.GERMAN;

        System.out.println(
        NumberFormat.getNumberInstance(locale).format(1_000_000_000L)
        );

        System.out.println(
        NumberFormat.getCurrencyInstance(locale).format(1_000_000_000L)
        );

        System.out.println(
        NumberFormat.getPercentInstance(locale).format(0.1)
        );

        System.out.println(
        NumberFormat.getCompactNumberInstance(locale , NumberFormat.Style.LONG).format(1_000_000_000L)
        );

        System.out.println(
        NumberFormat.getCompactNumberInstance(locale, NumberFormat.Style.SHORT).format(1_000_000_000L)
        );
    }
}
