package Rippling.expense;

import java.util.ArrayList;
import java.util.List;

public class RuleEvaluator {
    public List<Rule> evaluateRule(List<Rule> rules, List<Expense> expenses) {
        List<Rule> failedRules = new ArrayList<>();
        for (Rule rule : rules) {
            if (!rule.isSatisfied(expenses)) {
                failedRules.add(rule);
            }
        }
        return failedRules;
    }
}
