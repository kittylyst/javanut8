package javanut8.ch04.orders;

import java.time.LocalDate;

sealed interface Order permits BasicOrder, ExpressOrder {
    double price();
    String address();
    LocalDate delivery();
}
