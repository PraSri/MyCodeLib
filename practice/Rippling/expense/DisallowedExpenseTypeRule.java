package Rippling.expense;

import java.util.List;

public class DisallowedExpenseTypeRule implements Rule {
    private String expenseType;

    public DisallowedExpenseTypeRule(String expenseType) {
        this.expenseType = expenseType;
    }

    @Override
    public boolean isSatisfied(List<Expense> expenses) {
        for (Expense e : expenses) {
            if (expenseType.equals(e.getExpenseType())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getDescription() {
        return expenseType + " expense type should not be charged";
    }
}
