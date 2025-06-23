package Rippling.delivery.v4;

import java.math.BigDecimal;

public class StandardBilling implements BillingStrategy {
    @Override
    public BigDecimal computeCost(Assignment a) {
        // time component
        long mins = a.delivery.getDuration().toMinutes();
        BigDecimal timeCharge = a.driver.ratePerMinute
                .multiply(BigDecimal.valueOf(mins));

        // distance component
        BigDecimal distCharge = a.driver.ratePerKm
                .multiply(BigDecimal.valueOf(a.delivery.distanceKm));

        // flat fee
        BigDecimal flatFee = a.driver.flatFee;

        return timeCharge.add(distCharge).add(flatFee);
    }
}

