package Rippling.delivery.v4;

import java.math.BigDecimal;

// you could also add surge pricing, night?differentials, etc.
public class SurgeBilling implements BillingStrategy {
    private final BigDecimal surgeMultiplier;

    SurgeBilling(BigDecimal surgeMultiplier) {
        this.surgeMultiplier = surgeMultiplier;
    }

    @Override
    public BigDecimal computeCost(Assignment a) {
        StandardBilling base = new StandardBilling();
        return base.computeCost(a).multiply(surgeMultiplier);
    }
}
