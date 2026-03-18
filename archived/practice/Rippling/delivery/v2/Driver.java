package Rippling.delivery.v2;

import java.util.*;

/**
 * The Driver class represents a delivery driver with a unique name, a per?hour rate,
 * and a list of deliveries that haven’t been cleared yet.
 */
public class Driver {
    private final String name;
    private double perHourRate;
    private final List<Delivery> deliveries;

    /**
     * Constructs a new Driver.
     *
     * @param name        the driver’s name or ID
     * @param perHourRate the per hour payment rate for the driver
     */
    public Driver(String name, double perHourRate) {
        this.name = name;
        this.perHourRate = perHourRate;
        this.deliveries = new ArrayList<>();
    }

    /**
     * Adds a delivery to the driver's list.
     *
     * @param delivery the Delivery to add
     */
    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
    }

    /**
     * Returns the list of pending (unpaid) deliveries.
     *
     * @return list of Delivery objects that are not yet cleared
     */
    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    /**
     * Returns the driver’s per hour rate.
     *
     * @return the per hour payment rate
     */
    // use BigDecimal class for currency/monetary calculations
    public double getPerHourRate() {
        return perHourRate;
    }

    /**
     * Sets or updates the driver's per hour rate.
     *
     * @param perHourRate the new per hour rate
     */
    public void setPerHourRate(double perHourRate) {
        this.perHourRate = perHourRate;
    }

    /**
     * Returns the driver’s name.
     *
     * @return the driver's name
     */
    public String getName() {
        return name;
    }
}
