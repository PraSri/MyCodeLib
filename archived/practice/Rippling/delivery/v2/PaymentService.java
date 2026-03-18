package Rippling.delivery.v2;

/**
 * The PaymentService interface defines the operations available for maintaining drivers,
 * recording deliveries, clearing payments for deliveries up to a given time,
 * and getting the total unpaid amount.
 */
public interface PaymentService {
    /**
     * Adds a new driver with the specified name and per hour payment rate.
     *
     * @param name        the driver's name
     * @param perHourRate the per hour rate of the driver
     */
    void addDriver(String name, double perHourRate);

    /**
     * Records a new delivery for the driver specified by name.
     *
     * @param driverName   the name (or identifier) of the driver
     * @param epochSeconds the time the delivery was made (epoch seconds)
     */
    void addDelivery(String driverName, long epochSeconds);

    /**
     * Clears (pays out) all deliveries up to the provided clearTime.
     * This function computes the total amount to be paid for all deliveries
     * that occurred on or before the given time and marks those deliveries as paid.
     *
     * @param clearTime the epoch time until which deliveries are cleared
     * @return the total payment amount for deliveries that are cleared
     */
    double clearPayments(long clearTime);

    /**
     * Returns the total unpaid amount for all drivers for deliveries that have not been cleared.
     *
     * @return the total unpaid payment amount
     */
    double getUnpaidTotal();
}
