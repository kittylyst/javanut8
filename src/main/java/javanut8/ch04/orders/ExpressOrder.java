package javanut8.ch04.orders;

import java.time.LocalDate;

public record ExpressOrder(double price,
                           String address,
                           LocalDate delivery,
                           double deliveryCharge) implements Order {}
