package Rippling.expense;

import java.util.List;

public interface Rule {
    boolean isSatisfied(List<Expense> expenses);

    String getDescription();
}
