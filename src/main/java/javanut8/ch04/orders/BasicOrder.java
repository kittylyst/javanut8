package javanut8.ch04.orders;

import java.time.LocalDate;

public record BasicOrder(double price,
                         String address,
                         LocalDate delivery) implements Order {}
