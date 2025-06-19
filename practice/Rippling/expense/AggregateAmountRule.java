package Rippling.expense;

import java.util.List;
import java.util.function.Predicate;

public class AggregateAmountRule implements Rule {
    private Predicate<Expense> condition;
    private double maxTotal;
    private String description;

    public AggregateAmountRule(Predicate<Expense> condition, double maxTotal, String description) {
        this.condition = condition;
        this.maxTotal = maxTotal;
        this.description = description;
    }

    @Override
    public boolean isSatisfied(List<Expense> expenses) {
        double total = expenses.stream()
                .filter(condition)
                .mapToDouble(Expense::getAmountInUsd)
                .sum();
        return total <= maxTotal;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
