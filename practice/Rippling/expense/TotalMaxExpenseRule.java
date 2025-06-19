package Rippling.expense;

import java.util.List;

public class TotalMaxExpenseRule implements Rule {
    private double maxTotal;

    public TotalMaxExpenseRule(double maxTotal) {
        this.maxTotal = maxTotal;
    }

    @Override
    public boolean isSatisfied(List<Expense> expenses) {
        double total = 0.0;
        for (Expense e : expenses) {
            total += e.getAmountInUsd();
        }
        return total <= maxTotal;
    }

    @Override
    public String getDescription() {
        return "Total expense should not be > " + maxTotal;
    }
}
