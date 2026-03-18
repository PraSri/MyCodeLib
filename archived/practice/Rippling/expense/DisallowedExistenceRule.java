package Rippling.expense;

import java.util.List;
import java.util.function.Predicate;

class DisallowedExistenceRule implements Rule {
    private Predicate<Expense> condition;
    private String description;

    public DisallowedExistenceRule(Predicate<Expense> condition, String description) {
        this.condition = condition;
        this.description = description;
    }

    @Override
    public boolean isSatisfied(List<Expense> expenses) {
        return expenses.stream()
                .noneMatch(condition);
    }

    @Override
    public String getDescription() {
        return description;
    }
}
