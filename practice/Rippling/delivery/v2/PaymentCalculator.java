package Rippling.delivery.v2;

/**
 * The PaymentCalculator interface defines how to compute the payment for a single delivery.
 */
public interface PaymentCalculator {
    /**
     * Calculates the payment for a given delivery for the specified driver.
     *
     * @param driver   the Driver for which to compute payment
     * @param delivery the Delivery completed by the driver
     * @return the payment amount for that delivery
     */
    double calculatePayment(Driver driver, Delivery delivery);
}
