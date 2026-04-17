package uber;

import java.util.*;

public class UberEatsPricingModel {

    // ---------- Enums ----------
    enum Size {
        SMALL(1.0),
        MEDIUM(1.3),
        LARGE(1.6);

        final double priceMultiplier;

        Size(double priceMultiplier) {
            this.priceMultiplier = priceMultiplier;
        }
    }

    // ---------- Pricing Rules ----------
    interface PricingRule {
        void apply(Order order, PricingContext context);
    }

    // ---------- Models ----------
    static class Topping {
        private final String name;
        private final double price;

        public Topping(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }
    }

    static class FoodItem {
        private final String name;
        private final double basePrice;

        public FoodItem(String name, double basePrice) {
            this.name = name;
            this.basePrice = basePrice;
        }

        public double getBasePrice() {
            return basePrice;
        }

        public String getName() {
            return name;
        }
    }

    static class LineItem {
        private final FoodItem foodItem;
        private final Size size;
        private final List<Topping> toppings;
        private int quantity;

        public LineItem(FoodItem foodItem, Size size, List<Topping> toppings, int quantity) {
            this.foodItem = foodItem;
            this.size = size;
            this.toppings = new ArrayList<>(toppings);
            this.quantity = quantity;
        }

        public double getItemTotalPrice() {
            double itemPrice = foodItem.getBasePrice() * size.priceMultiplier;
            for (Topping t : toppings) {
                itemPrice += t.getPrice();
            }
            return itemPrice * quantity;
        }

        public FoodItem getFoodItem() {
            return foodItem;
        }

        public Size getSize() {
            return size;
        }

        public List<Topping> getToppings() {
            return Collections.unmodifiableList(toppings);
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    static class Order {
        private final List<LineItem> items = new ArrayList<>();
        private double surgeMultiplier = 1.0;

        public void addItem(LineItem item) {
            items.add(item);
        }

        public List<LineItem> getItems() {
            return Collections.unmodifiableList(items);
        }

        public double getSurgeMultiplier() {
            return surgeMultiplier;
        }

        public void setSurgeMultiplier(double surgeMultiplier) {
            this.surgeMultiplier = surgeMultiplier;
        }

        // Compute raw subtotal (no surge applied yet)
        public double computeRawSubtotal() {
            double subtotal = 0;
            for (LineItem item : items) {
                double price = item.getFoodItem().getBasePrice() * item.getSize().priceMultiplier;
                for (Topping t : item.getToppings()) {
                    price += t.getPrice();
                }
                subtotal += price * item.getQuantity();
            }
            return subtotal;
        }
    }

    static class PricingContext {
        private final Order order;
        private double total;

        public PricingContext(Order order) {
            this.order = order;
            this.total = order.computeRawSubtotal();
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public Order getOrder() {
            return order;
        }
    }

    // BOGO: For every 2 of the same FoodItem (ignoring size/toppings for simplicity),
// one becomes free. Modifies line item quantities.
    static class BOGORule implements PricingRule {
        @Override
        public void apply(Order order, PricingContext context) {
            // Group identical items by FoodItem name (simple version)
            Map<String, List<LineItem>> itemMap = new HashMap<>();
            for (LineItem item : order.getItems()) {
                itemMap.computeIfAbsent(item.getFoodItem().getName(), k -> new ArrayList<>()).add(item);
            }

            for (List<LineItem> group : itemMap.values()) {
                int totalQty = group.stream().mapToInt(LineItem::getQuantity).sum();
                int freeItems = totalQty / 2; // BOGO: buy one get one free
                if (freeItems > 0) {
                    // Reduce quantity from the first item in group
                    int toReduce = freeItems;
                    for (LineItem item : group) {
                        int qty = item.getQuantity();
                        int reduce = Math.min(toReduce, qty);
                        item.setQuantity(qty - reduce);
                        toReduce -= reduce;
                        if (toReduce == 0) break;
                    }
                }
            }
            // Recalculate total after quantity adjustments
            context.setTotal(order.computeRawSubtotal());
        }
    }

    // Surge pricing: multiply all item base prices (already accounted in raw subtotal)
// We apply surge multiplier to the current total.
    static class SurgePricingRule implements PricingRule {
        @Override
        public void apply(Order order, PricingContext context) {
            double multiplier = order.getSurgeMultiplier();
            if (multiplier != 1.0) {
                context.setTotal(context.getTotal() * multiplier);
            }
        }
    }

    // Coupon: can be percentage or fixed discount, with optional minimum order value
    static class Coupon {
        private final String code;
        private final double discountValue; // if >0 and <1 -> percentage, else fixed amount
        private final boolean isPercentage;
        private final double minOrderValue;

        public Coupon(String code, double discountValue, boolean isPercentage, double minOrderValue) {
            this.code = code;
            this.discountValue = discountValue;
            this.isPercentage = isPercentage;
            this.minOrderValue = minOrderValue;
        }

        public double applyDiscount(double currentTotal) {
            if (currentTotal < minOrderValue) return 0;
            if (isPercentage) {
                return currentTotal * (discountValue / 100.0);
            } else {
                return Math.min(discountValue, currentTotal);
            }
        }
    }

    static class CouponRule implements PricingRule {
        private final Coupon coupon;

        public CouponRule(Coupon coupon) {
            this.coupon = coupon;
        }

        @Override
        public void apply(Order order, PricingContext context) {
            double discount = coupon.applyDiscount(context.getTotal());
            context.setTotal(context.getTotal() - discount);
        }
    }

    // Uber One: additional percentage discount on the total after all previous rules
    static class UberOneRule implements PricingRule {
        private final double discountPercent; // e.g., 5 for 5% off

        public UberOneRule(double discountPercent) {
            this.discountPercent = discountPercent;
        }

        @Override
        public void apply(Order order, PricingContext context) {
            if (discountPercent > 0) {
                context.setTotal(context.getTotal() * (1 - discountPercent / 100.0));
            }
        }
    }

    // ---------- Pricing Calculator ----------
    static class PricingCalculator {
        private final List<PricingRule> rules = new ArrayList<>();

        public void addRule(PricingRule rule) {
            rules.add(rule);
        }

        public double calculate(Order order) {
            PricingContext context = new PricingContext(order);
            for (PricingRule rule : rules) {
                rule.apply(order, context);
            }
            return context.getTotal();
        }
    }

    // ---------- Demo ----------
    public class UberEatsPricingDemo {
        public static void main(String[] args) {
            // Setup food items
            FoodItem pizza = new FoodItem("Pizza", 10.0);
            FoodItem burger = new FoodItem("Burger", 5.0);

            Topping cheese = new Topping("Cheese", 1.0);
            Topping bacon = new Topping("Bacon", 1.5);

            // Build order
            Order order = new Order();
            order.addItem(new LineItem(pizza, Size.LARGE, List.of(cheese, bacon), 2));
            order.addItem(new LineItem(burger, Size.MEDIUM, List.of(cheese), 3));

            // Surge pricing active (1.2x)
            order.setSurgeMultiplier(1.2);

            // Create calculator and add rules in correct order
            PricingCalculator calculator = new PricingCalculator();
            calculator.addRule(new BOGORule());          // First, adjust quantities for BOGO
            calculator.addRule(new SurgePricingRule());  // Then apply surge multiplier
            calculator.addRule(new CouponRule(new Coupon("SAVE10", 10, false, 20))); // $10 off min $20
            calculator.addRule(new UberOneRule(5));      // 5% off for Uber One members

            double finalPrice = calculator.calculate(order);
            System.out.printf("Final order price: $%.2f\n", finalPrice);
            // Expected: Raw subtotal = (Pizza: 10*1.6 + 1+1.5 = 18.5 each) *2 = 37,
            // Burger: (5*1.3 +1 = 7.5 each)*3 = 22.5, total raw = 59.5
            // BOGO: pizza quantity 2 -> 1 free? Actually 2/2=1 free, so one pizza free -> pizza quantity becomes 1, raw = 18.5 + 22.5 = 41
            // Surge 1.2 -> 49.2
            // Coupon $10 off -> 39.2 (min 20 satisfied)
            // Uber One 5% -> 39.2 * 0.95 = 37.24
        }
    }
}
