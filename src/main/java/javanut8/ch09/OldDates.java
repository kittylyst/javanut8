package javanut8.ch09;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class OldDates {
    public static void main(String... args) {
        // Defaults to timestamp when called
        var oldDate = new java.util.Date();

        // Note both forms require specifying timezone -
        // part of the failing in the old API
        var newDate = LocalDate.ofInstant(
                oldDate.toInstant(),
                ZoneId.systemDefault());

        var newTime = LocalDateTime.ofInstant(
                oldDate.toInstant(),
                ZoneId.systemDefault());
    }
}
