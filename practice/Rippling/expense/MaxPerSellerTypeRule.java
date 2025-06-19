package Rippling.expense;

import java.util.List;

public class MaxPerSellerTypeRule implements Rule {
    private String sellerType;
    private double maxAmount;

    public MaxPerSellerTypeRule(String sellerType, double maxAmount) {
        this.sellerType = sellerType;
        this.maxAmount = maxAmount;
    }

    @Override
    public boolean isSatisfied(List<Expense> expenses) {
        for (Expense e : expenses) {
            if (sellerType.equals(e.getSellerType())) {
                if (e.getAmountInUsd() > maxAmount) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "Seller type " + sellerType + " should not have expense more that " + maxAmount;
    }
}
