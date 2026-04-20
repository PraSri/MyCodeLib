package microsoft;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Splitwise {

    // ======================== Expense Factory (Factory Pattern) ========================
    enum ExpenseType {EQUAL, EXACT, PERCENT}

    static class EqualExpense extends Expense {
        public EqualExpense(String id, double totalAmount, User paidBy, List<Split> splits, String description) {
            super(id, totalAmount, paidBy, splits, description);
        }

        @Override
        public void validate() throws IllegalArgumentException {
            double splitAmount = totalAmount / splits.size();
            for (Split split : splits) {
                if (!(split instanceof EqualSplit))
                    throw new IllegalArgumentException("All splits must be EqualSplit for EqualExpense");
                split.setAmount(splitAmount);
            }
        }
    }

    static class ExactExpense extends Expense {
        public ExactExpense(String id, double totalAmount, User paidBy, List<Split> splits, String description) {
            super(id, totalAmount, paidBy, splits, description);
        }

        @Override
        public void validate() throws IllegalArgumentException {
            double sum = 0;
            for (Split split : splits) {
                if (!(split instanceof ExactSplit))
                    throw new IllegalArgumentException("All splits must be ExactSplit for ExactExpense");
                sum += split.getAmount();
            }
            if (Math.abs(sum - totalAmount) > 0.01)
                throw new IllegalArgumentException("Sum of exact splits does not match total amount");
        }
    }

    static class PercentExpense extends Expense {
        public PercentExpense(String id, double totalAmount, User paidBy, List<Split> splits, String description) {
            super(id, totalAmount, paidBy, splits, description);
        }

        @Override
        public void validate() throws IllegalArgumentException {
            double totalPercent = 0;
            for (Split split : splits) {
                if (!(split instanceof PercentSplit))
                    throw new IllegalArgumentException("All splits must be PercentSplit for PercentExpense");
                totalPercent += ((PercentSplit) split).getPercent();
            }
            if (Math.abs(totalPercent - 100.0) > 0.01)
                throw new IllegalArgumentException("Sum of percentages must be 100%");
            // Calculate amounts from percentages
            for (Split split : splits) {
                ((PercentSplit) split).setAmountFromPercent(totalAmount);
            }
        }
    }

    // ======================== User Model ========================
    class User {
        private final String id;
        private final String name;
        private final String email;
        // Balance map: otherUserId -> net amount this user is owed (positive = owed to me, negative = I owe)
        private final Map<String, Double> balances;

        public User(String id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.balances = new HashMap<>();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Map<String, Double> getBalances() {
            return balances;
        }

        public void updateBalance(String otherUserId, double amount) {
            balances.merge(otherUserId, amount, Double::sum);
            // Remove zero entries for clean representation
            if (Math.abs(balances.get(otherUserId)) < 0.01) {
                balances.remove(otherUserId);
            }
        }

        @Override
        public String toString() {
            return String.format("User{id='%s', name='%s'}", id, name);
        }
    }

    // ======================== Split Models ========================
    abstract class Split {
        protected User user;
        protected double amount;  // actual share amount (calculated after validation)

        public Split(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }

    class EqualSplit extends Split {
        public EqualSplit(User user) {
            super(user);
        }
    }

    class ExactSplit extends Split {
        public ExactSplit(User user, double amount) {
            super(user);
            this.amount = amount;
        }
    }

    class PercentSplit extends Split {
        private double percent;

        public PercentSplit(User user, double percent) {
            super(user);
            this.percent = percent;
        }

        public double getPercent() {
            return percent;
        }

        public void setAmountFromPercent(double totalAmount) {
            this.amount = (totalAmount * percent) / 100.0;
        }
    }

    // ======================== Expense Models ========================
    abstract class Expense {
        protected final String id;
        protected final double totalAmount;
        protected final User paidBy;
        protected final List<Split> splits;
        protected final String description;
        protected final Date date;

        public Expense(String id, double totalAmount, User paidBy, List<Split> splits, String description) {
            this.id = id;
            this.totalAmount = totalAmount;
            this.paidBy = paidBy;
            this.splits = splits;
            this.description = description;
            this.date = new Date();
        }

        public abstract void validate() throws IllegalArgumentException;

        public User getPaidBy() {
            return paidBy;
        }

        public List<Split> getSplits() {
            return splits;
        }

        public double getTotalAmount() {
            return totalAmount;
        }
    }

    class ExpenseFactory {
        public static Expense createExpense(ExpenseType type, String id, double totalAmount, User paidBy,
                                            List<Split> splits, String description) {
            Expense expense;
            switch (type) {
                case EQUAL:
                    expense = new EqualExpense(id, totalAmount, paidBy, splits, description);
                    break;
                case EXACT:
                    expense = new ExactExpense(id, totalAmount, paidBy, splits, description);
                    break;
                case PERCENT:
                    expense = new PercentExpense(id, totalAmount, paidBy, splits, description);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown expense type");
            }
            expense.validate();  // validation inside each concrete class
            return expense;
        }
    }

    // ======================== Balance Manager ========================
    class BalanceManager {
        // Updates balances when an expense is added
        public void updateBalances(Expense expense) {
            User paidBy = expense.getPaidBy();
            for (Split split : expense.getSplits()) {
                User borrower = split.getUser();
                double amount = split.getAmount();
                if (borrower.equals(paidBy)) continue; // self-split ignored

                // paidBy is owed 'amount' from borrower
                paidBy.updateBalance(borrower.getId(), +amount);
                borrower.updateBalance(paidBy.getId(), -amount);
            }
        }

        // Settle debt: user1 pays user2 the given amount
        public void settleBalance(User user1, User user2, double amount) {
            user1.updateBalance(user2.getId(), +amount);
            user2.updateBalance(user1.getId(), -amount);
        }

        // Show balance for a single user
        public void showBalance(User user) {
            Map<String, Double> balances = user.getBalances();
            if (balances.isEmpty()) {
                System.out.println(user.getName() + " has no outstanding balances.");
                return;
            }
            for (Map.Entry<String, Double> entry : balances.entrySet()) {
                String otherUserId = entry.getKey();
                double diff = entry.getValue();
                // For display: positive = others owe user, negative = user owes others
                if (diff > 0) {
                    System.out.printf("%s owes %s %.2f%n", otherUserId, user.getName(), diff);
                } else if (diff < 0) {
                    System.out.printf("%s owes %s %.2f%n", user.getName(), otherUserId, -diff);
                }
            }
        }

        // Show all balances across all users (simplified)
        public void showAllBalances(Map<String, User> users) {
            for (User user : users.values()) {
                showBalance(user);
            }
        }
    }

    // ======================== In-Memory Repositories (Singleton) ========================
    class UserRepository {
        private static UserRepository instance;
        private final Map<String, User> users = new HashMap<>();

        private UserRepository() {
        }

        public static synchronized UserRepository getInstance() {
            if (instance == null) instance = new UserRepository();
            return instance;
        }

        public void addUser(User user) {
            users.put(user.getId(), user);
        }

        public User getUser(String id) {
            return users.get(id);
        }

        public Map<String, User> getAllUsers() {
            return Collections.unmodifiableMap(users);
        }
    }

    class ExpenseRepository {
        private static ExpenseRepository instance;
        private final Map<String, Expense> expenses = new HashMap<>();
        private final AtomicLong idGenerator = new AtomicLong(1);

        private ExpenseRepository() {
        }

        public static synchronized ExpenseRepository getInstance() {
            if (instance == null) instance = new ExpenseRepository();
            return instance;
        }

        public String generateId() {
            return "EXP" + idGenerator.getAndIncrement();
        }

        public void saveExpense(Expense expense) {
            expenses.put(expense.id, expense);
        }

        public List<Expense> getAllExpenses() {
            return new ArrayList<>(expenses.values());
        }
    }

    // ======================== Main Service / Controller ========================
    class SplitwiseService {
        private final UserRepository userRepo = UserRepository.getInstance();
        private final ExpenseRepository expenseRepo = ExpenseRepository.getInstance();
        private final BalanceManager balanceManager = new BalanceManager();

        public void addUser(String id, String name, String email) {
            userRepo.addUser(new User(id, name, email));
        }

        public void addExpense(ExpenseType type, double totalAmount, String paidByUserId,
                               List<Split> splits, String description) {
            User paidBy = userRepo.getUser(paidByUserId);
            if (paidBy == null) throw new IllegalArgumentException("Paid by user not found");

            String expenseId = expenseRepo.generateId();
            Expense expense = ExpenseFactory.createExpense(type, expenseId, totalAmount, paidBy, splits, description);
            expenseRepo.saveExpense(expense);
            balanceManager.updateBalances(expense);
        }

        public void settleBalance(String userId1, String userId2, double amount) {
            User u1 = userRepo.getUser(userId1);
            User u2 = userRepo.getUser(userId2);
            if (u1 == null || u2 == null) throw new IllegalArgumentException("User not found");
            balanceManager.settleBalance(u1, u2, amount);
        }

        public void showBalance(String userId) {
            User user = userRepo.getUser(userId);
            if (user == null) throw new IllegalArgumentException("User not found");
            balanceManager.showBalance(user);
        }

        public void showAllBalances() {
            balanceManager.showAllBalances(userRepo.getAllUsers());
        }
    }

    // ======================== Demo ========================
    public class SplitwiseDemo {
        public static void main(String[] args) {
            SplitwiseService sw = new SplitwiseService();

            // Create users
            sw.addUser("u1", "Alice", "alice@example.com");
            sw.addUser("u2", "Bob", "bob@example.com");
            sw.addUser("u3", "Charlie", "charlie@example.com");

            // 1. Equal split expense: Alice pays 300 for all three
            List<Split> equalSplits = Arrays.asList(
                    new EqualSplit(UserRepository.getInstance().getUser("u1")),
                    new EqualSplit(UserRepository.getInstance().getUser("u2")),
                    new EqualSplit(UserRepository.getInstance().getUser("u3"))
            );
            sw.addExpense(ExpenseType.EQUAL, 300.0, "u1", equalSplits, "Dinner");

            // 2. Exact split expense: Bob pays 200, Alice 50, Charlie 150
            List<Split> exactSplits = Arrays.asList(
                    new ExactSplit(UserRepository.getInstance().getUser("u1"), 50.0),
                    new ExactSplit(UserRepository.getInstance().getUser("u2"), 200.0),
                    new ExactSplit(UserRepository.getInstance().getUser("u3"), 150.0)
            );
            sw.addExpense(ExpenseType.EXACT, 400.0, "u2", exactSplits, "Groceries");

            // 3. Percent split expense: Charlie pays 120, split 50% Alice, 30% Bob, 20% Charlie
            List<Split> percentSplits = Arrays.asList(
                    new PercentSplit(UserRepository.getInstance().getUser("u1"), 50.0),
                    new PercentSplit(UserRepository.getInstance().getUser("u2"), 30.0),
                    new PercentSplit(UserRepository.getInstance().getUser("u3"), 20.0)
            );
            sw.addExpense(ExpenseType.PERCENT, 120.0, "u3", percentSplits, "Movie tickets");

            System.out.println("===== Balances after all expenses =====");
            sw.showAllBalances();

            System.out.println("\n===== Alice's balance =====");
            sw.showBalance("u1");

            System.out.println("\n===== Settle: Bob pays Alice 50 =====");
            sw.settleBalance("u2", "u1", 50.0);
            sw.showAllBalances();
        }
    }
}
