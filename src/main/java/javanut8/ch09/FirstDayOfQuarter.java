package javanut8.ch09;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.IsoFields;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class FirstDayOfQuarter implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        final int currentQuarter = YearMonth.from(temporal)
                .get(IsoFields.QUARTER_OF_YEAR);

        final Month firstMonthOfQuarter = switch (currentQuarter) {
            case 1 -> Month.JANUARY;
            case 2 -> Month.APRIL;
            case 3 -> Month.JULY;
            case 4 -> Month.OCTOBER;
            default -> throw new IllegalArgumentException("Impossible");
        };

        return LocalDate.from(temporal)
                .withMonth(firstMonthOfQuarter.getValue())
                .with(TemporalAdjusters.firstDayOfMonth());
    }
}
