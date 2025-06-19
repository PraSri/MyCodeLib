package Rippling.expense;

import java.util.List;
import java.util.function.Predicate;

class MaxPerExpenseRule implements Rule {
    private Predicate<Expense> condition;
    private double maxAmountPerExpense;
    private String description;

    public MaxPerExpenseRule(Predicate<Expense> condition, double maxAmountPerExpense, String description) {
        this.condition = condition;
        this.maxAmountPerExpense = maxAmountPerExpense;
        this.description = description;
    }

    @Override
    public boolean isSatisfied(List<Expense> expenses) {
        return expenses.stream()
                .filter(condition)
                .allMatch(e -> e.getAmountInUsd() <= maxAmountPerExpense);
    }

    @Override
    public String getDescription() {
        return description;
    }
}
