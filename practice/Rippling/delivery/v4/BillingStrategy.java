package Rippling.delivery.v4;

import java.math.BigDecimal;

@FunctionalInterface
interface BillingStrategy {
  /**
   * @return cost for this single assignment
   */
  BigDecimal computeCost(Assignment a);
}