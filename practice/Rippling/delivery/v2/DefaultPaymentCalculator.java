package Rippling.delivery.v2;

/**
 * DefaultPaymentCalculator implements the PaymentCalculator interface.
 * In this simple example, we assume each delivery awards one full hour of payment at the driver's rate.
 */
public class DefaultPaymentCalculator implements PaymentCalculator {
    @Override
    public double calculatePayment(Driver driver, Delivery delivery) {
        // Each delivery is considered one hour of work.
        return driver.getPerHourRate();
    }
}
