package Rippling.expense;

import java.util.ArrayList;
import java.util.List;

public class RuleEvaluatorTest {

    public static void main(String[] args) {
        // Create sample expenses
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("1", "Lunch", "Food", 40.0, "restaurant", "ABC Restaurant"));
        expenses.add(new Expense("2", "Concert", "Entertainment", 150.0, "venue", "Music Hall"));
        expenses.add(new Expense("3", "Dinner", "Food", 60.0, "restaurant", "XYZ Bistro"));
        expenses.add(new Expense("4", "Flight", "Travel", 500.0, "airline", "Sky Airlines"));
        expenses.add(new Expense("5", "Snacks", "Food", 10.0, "cafe", "Coffee Corner"));

        // Create test rules
        List<Rule> rules = new ArrayList<>();
        rules.add(new AggregateAmountRule(e -> true, 700.0, "Total expenses > 700"));
        rules.add(new AggregateAmountRule(e -> "Food".equals(e.getExpenseType()), 100.0, "Total food expenses > 100"));
        rules.add(new MaxPerExpenseRule(e -> "restaurant".equals(e.getSellerType()), 45.0, "Restaurant expense > 45"));
        rules.add(new DisallowedExistenceRule(e -> "Entertainment".equals(e.getExpenseType()), "Entertainment expenses disallowed"));
        rules.add(new AggregateAmountRule(e -> "Travel".equals(e.getExpenseType()), 450.0, "Travel expenses > 450"));

        // Evaluate rules
        RuleEvaluator evaluator = new RuleEvaluator();
        List<Rule> failedRules = evaluator.evaluateRule(rules, expenses);

        // Print results
        System.out.println("Failed rules:");
        for (Rule rule : failedRules) {
            System.out.println(" - " + rule.getDescription());
        }

        // Additional test cases
        runTestCase("All rules pass", allRulesPassTestCase());
        runTestCase("Multiple rules fail", multipleRulesFailTestCase());
        runTestCase("Empty expenses", emptyExpensesTestCase());
        runTestCase("Single expensive item", singleExpensiveItemTestCase());
    }

    private static void runTestCase(String testName, List<Rule> failedRules) {
        System.out.println("\n" + testName + ":");
        if (failedRules.isEmpty()) {
            System.out.println(" - No rules failed (as expected)");
        } else {
            for (Rule rule : failedRules) {
                System.out.println(" - " + rule.getDescription());
            }
        }
    }

    private static List<Rule> allRulesPassTestCase() {
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("1", "Breakfast", "Food", 20.0, "cafe", "Morning Cafe"));
        expenses.add(new Expense("2", "Bus", "Transport", 15.0, "public", "City Transit"));

        List<Rule> rules = new ArrayList<>();
        rules.add(new AggregateAmountRule(e -> true, 100.0, "Total expenses > 100"));
        rules.add(new MaxPerExpenseRule(e -> true, 50.0, "Any expense > 50"));
        rules.add(new DisallowedExistenceRule(e -> "Entertainment".equals(e.getExpenseType()), "Entertainment disallowed"));

        RuleEvaluator evaluator = new RuleEvaluator();
        return evaluator.evaluateRule(rules, expenses);
    }

    private static List<Rule> multipleRulesFailTestCase() {
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("1", "Luxury Dinner", "Food", 200.0, "restaurant", "Gourmet Spot"));
        expenses.add(new Expense("2", "Casino", "Entertainment", 300.0, "casino", "Golden Jackpot"));

        List<Rule> rules = new ArrayList<>();
        rules.add(new AggregateAmountRule(e -> true, 400.0, "Total expenses > 400"));
        rules.add(new AggregateAmountRule(e -> "Food".equals(e.getExpenseType()), 100.0, "Food expenses > 100"));
        rules.add(new MaxPerExpenseRule(e -> "restaurant".equals(e.getSellerType()), 100.0, "Restaurant expense > 100"));
        rules.add(new DisallowedExistenceRule(e -> "Entertainment".equals(e.getExpenseType()), "Entertainment disallowed"));

        RuleEvaluator evaluator = new RuleEvaluator();
        return evaluator.evaluateRule(rules, expenses);
    }

    private static List<Rule> emptyExpensesTestCase() {
        List<Expense> expenses = new ArrayList<>();

        List<Rule> rules = new ArrayList<>();
        rules.add(new AggregateAmountRule(e -> true, 100.0, "Total expenses > 100"));
        rules.add(new DisallowedExistenceRule(e -> "Entertainment".equals(e.getExpenseType()), "Entertainment disallowed"));

        RuleEvaluator evaluator = new RuleEvaluator();
        return evaluator.evaluateRule(rules, expenses);
    }

    private static List<Rule> singleExpensiveItemTestCase() {
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("1", "Designer Bag", "Shopping", 1000.0, "boutique", "Fashion Store"));

        List<Rule> rules = new ArrayList<>();
        rules.add(new AggregateAmountRule(e -> true, 500.0, "Total expenses > 500"));
        rules.add(new MaxPerExpenseRule(e -> true, 800.0, "Any expense > 800"));
        rules.add(new DisallowedExistenceRule(e -> "Shopping".equals(e.getExpenseType()), "Shopping disallowed"));

        RuleEvaluator evaluator = new RuleEvaluator();
        return evaluator.evaluateRule(rules, expenses);
    }
}
