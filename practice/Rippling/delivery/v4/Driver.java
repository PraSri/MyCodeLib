package Rippling.delivery.v4;

import java.math.BigDecimal;

public class Driver {
    final String id;
    final BigDecimal ratePerMinute;   // e.g. ?1.50/min
    final BigDecimal ratePerKm;       // e.g. ?0.50/km
    final BigDecimal flatFee;         // e.g. ?20 per delivery

    Driver(String id,
           BigDecimal ratePerMinute,
           BigDecimal ratePerKm,
           BigDecimal flatFee) {
        this.id = id;
        this.ratePerMinute = ratePerMinute;
        this.ratePerKm = ratePerKm;
        this.flatFee = flatFee;
    }
}
