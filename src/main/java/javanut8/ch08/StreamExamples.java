package javanut8.ch08;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples {

    public static class SquareGenerator implements IntSupplier {
        private int current = 1;

        @Override
        public synchronized int getAsInt() {
            int thisResult = current * current;
            System.out.print(String.format("%d... ", thisResult));
            current++;
            return thisResult;
        }
    }

    public static void main(String... args) {
        squares();
//        lazy();
        options();
    }

    public static void squares() {
        IntStream squares = IntStream.generate(new SquareGenerator());
        PrimitiveIterator.OfInt stepThrough = squares.iterator();

        for (int i = 0; i < 10; i++) {
            System.out.println(stepThrough.nextInt());
        }
        System.out.println("First iterator done...");
        // We can go on as long as we like...
        for (int i = 0; i < 10; i++) {
            System.out.println(stepThrough.nextInt());
        }
    }

    public static void lazy() {
        List<String> quotes = List.of("For Brutus is an honourable man",
                "Give me your hands if we be friends and Robin shall restore amends",
                "Misery acquaints a man with strange bedfellows");

        // Create a temporary collection for our words
        List<String> words = quotes.stream()
                .flatMap(line -> Stream.of(line.split(" +")))
                .toList();
        long wordCount = words.size();

        // Cast to double prevents Java from using integer division
        double aveLength = ((double) words.stream()
                .map(String::length)
                .reduce(0, Integer::sum)) / wordCount;
        System.out.println("Average word length: " + aveLength);
    }

    private static void options() {

        // Further filtering

        // Distinct elements only
        listAndPrint(
                Stream.of(1, 2, 1, 2, 3, 4)
                        .distinct()
        );

        // Ignores items in stream until predicate matches, then returns remainder
        // Note that later elements aren't required to match the predicate.
        listAndPrint(
                Stream.of(1, 2, 3, 4, 5, 3)
                        .dropWhile((i) -> i < 4)
        );

        // Takes items as long as the predicate matches
        listAndPrint(
                Stream.of(1, 2, 3, 4, 5)
                        .takeWhile((i) -> i < 4)
        );

        // Skips the first N items in the stream
        listAndPrint(
                Stream.of(1, 2, 3, 4, 5)
                        .skip(2)
        );

        // Limits items taken from stream to an exact value
        // Useful with infinite streams to set boundaries
        listAndPrint(
                Stream.of(1, 2, 3, 4, 5)
                        .limit(3)
        );


        // Searching


        // Are all the items odd?
        System.out.println(
                Stream.of(1, 1, 3, 5)
                        .allMatch((i) -> i % 2 == 1)
        );

        // Are none of the items even?
        System.out.println(
                Stream.of(1, 1, 3, 5)
                        .noneMatch((i) -> i % 2 == 0)
        );

        // Is at least one item even?
        System.out.println(
                Stream.of(1, 1, 3, 5, 6)
                        .anyMatch((i) -> i % 2 == 0)
        );

        var lines = Stream.of("For Brutus is an honourable man",
                        "Give me your hands if we be friends and Robin shall restore amends",
                        "Misery acquaints a man with strange bedfellows");

        listAndPrint(
            lines.map((s) -> s.split(" +"))
        );

        // Reset as prior stream is closed...
        lines = Stream.of("For Brutus is an honourable man",
                "Give me your hands if we be friends and Robin shall restore amends",
                "Misery acquaints a man with strange bedfellows");

        listAndPrint(
                lines.flatMap((s) -> Arrays.stream(s.split(" +")))
        );

        // Collect yourself....
        List<Integer> list = Stream.of(1, 2, 3, 4, 5)
                                      .toList();

        var array = Stream.of(1, 2, 3, 4, 5)
                .toArray();

        List<Integer> list2 = Stream.of(1,2,3,4,5).collect(Collectors.toList());
        System.out.println(list2);

        Set<Integer> set = Stream.of(1,2,3,4,5).collect(Collectors.toSet());
        System.out.println(set);

        TreeSet<Integer> collection = Stream.of(1,2,3,4,5).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(collection);

        Map<Integer, String> map = Stream.of(1,2,3,4,5).collect(Collectors.toMap((i) -> i, Object::toString));
        System.out.println(map);

        Map<Character, List<Integer>> grouped =
                Stream.of(10, 11, 12, 20, 30)
                        .collect(Collectors.groupingBy((i) -> {
                            return i.toString().charAt(0);
                        }));
        System.out.println(grouped);

        var count = Stream.of(1,2,3).count();
        var max = Stream.of(1,2,3).max(Integer::compareTo);
        var min = Stream.of(1,2,3).min(Integer::compareTo);

        var average = Stream.of(1,2,3).collect(Collectors.averagingInt(Integer::intValue));
        var sum = Stream.of(1,2,3).collect(Collectors.summingInt(Integer::intValue));
        var summary = Stream.of(1,2,3).collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(summary);

        var words = List.of("This", "is", "some", "text");
        words.stream().collect(Collectors.joining(", "));

        // Reset as prior stream is closed...
        lines = Stream.of("For Brutus is an honourable man",
                "Give me your hands if we be friends and Robin shall restore amends",
                "Misery acquaints a man with strange bedfellows");

        System.out.println(
                lines
                    .flatMap((s) -> Arrays.stream(s.split(" +")))
                    .collect(Collectors.joining(", "))
        );
    }

    private static void listAndPrint(Stream stream) {
        System.out.println(stream.toList());
    }
}
