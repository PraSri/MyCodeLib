package Rippling.delivery.v2;

public class Main {
    public static void main(String[] args) {
        // Create the PaymentCalculator strategy – we use the default implementation.
        PaymentCalculator calculator = new DefaultPaymentCalculator();
        // Create the PaymentService façade.
        PaymentService paymentService = new PaymentServiceImpl(calculator);

        // Add drivers.
        paymentService.addDriver("Alice", 15.0);
        paymentService.addDriver("Bob", 20.0);

        // Simulate adding deliveries with epoch seconds.
        // (For example purposes, assume these epoch timestamps are in seconds.)
        long now = System.currentTimeMillis() / 1000;
        paymentService.addDelivery("Alice", now - 5000);
        paymentService.addDelivery("Alice", now - 3000);
        paymentService.addDelivery("Bob", now - 4000);

        // Clear payments up to a specific point in time (for example, now - 3500 seconds).
        double clearedAmount = paymentService.clearPayments(now - 3500);
        System.out.println("Total cleared amount: " + clearedAmount);

        // Get the total unpaid amount after clearing.
        double unpaidAmount = paymentService.getUnpaidTotal();
        System.out.println("Remaining unpaid amount: " + unpaidAmount);
    }
}