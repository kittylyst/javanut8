package javanut8.ch09;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Regex {
    public static void main(String... args) {
        String text = "Apollo 13";

        // A numeric digit. Note we must use \\ because we need a literal \
        // and Java uses a single \ as an escape character, as per the table
        Pattern p = Pattern.compile("\\d");
        Matcher m = p.matcher(text);
        System.out.print(p + " matches " + text + "? " + m.find());
        System.out.println(" ; match: " + m.group());

        // A single letter
        p = Pattern.compile("[a-zA-Z]");
        m = p.matcher(text);
        System.out.print(p + " matches " + text + "? " + m.find());
        System.out.println(" ; match: " + m.group());

        // Any number of letters, which must all be in the range 'a' to 'j'
        // but can be upper- or lowercase
        p = Pattern.compile("([a-jA-J]*)");
        m = p.matcher(text);
        System.out.print(p + " matches " + text + "? " + m.find());
        System.out.println(" ; match: " + m.group());

        // 'a' followed by any four characters, followed by 'b'
        text = "abacab";
        p = Pattern.compile("a....b");
        m = p.matcher(text);
        System.out.print(p + " matches " + text + "? " + m.find());
        System.out.println(" ; match: " + m.group());

        predicate();
        textBlock();
    }

    private static void predicate() {
        // Contains a numeric digit
        Pattern p = Pattern.compile("\\d");

        List<String> ls = List.of("Cat", "Dog", "Ice-9", "99 Luftballoons");
        List<String> containDigits = ls.stream()
                .filter(p.asPredicate())
                .toList();

        System.out.println(containDigits);
    }

    private static void textBlock() {
        // Detect if there are any double-quoted passages in string
        Pattern oldQuoted = Pattern.compile(".*\".*\".*");
        Pattern newQuoted = Pattern.compile("""
                                            .*".*".*""");
        System.out.println(oldQuoted);
        System.out.println(newQuoted);
    }
}
