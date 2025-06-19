package Rippling.delivery.v2;

import java.util.*;
import java.util.Map;

/**
 * PaymentServiceImpl is a façade that encapsulates the drivers and their deliveries.
 * It uses a PaymentCalculator strategy (injected via the constructor) to compute the payment
 * for each delivery. This class leverages the Facade pattern to simplify the payment logic interface.
 */
public class PaymentServiceImpl implements PaymentService {
    private final Map<String, Driver> driverMap;
    private final PaymentCalculator paymentCalculator;

    /**
     * Constructs the PaymentServiceImpl using the specified PaymentCalculator.
     *
     * @param paymentCalculator the PaymentCalculator strategy to use in payment computation
     */
    public PaymentServiceImpl(PaymentCalculator paymentCalculator) {
        this.driverMap = new HashMap<>();
        this.paymentCalculator = paymentCalculator;
    }

    @Override
    public void addDriver(String name, double perHourRate) {
        if (driverMap.containsKey(name)) {
            // If already exists, update the rate
            Driver driver = driverMap.get(name);
            driver.setPerHourRate(perHourRate);
            System.out.println("Driver " + name + " already exists. Rate updated to " + perHourRate + ".");
        } else {
            Driver driver = new Driver(name, perHourRate);
            driverMap.put(name, driver);
            System.out.println("Driver " + name + " added with rate " + perHourRate + ".");
        }
    }

    @Override
    public void addDelivery(String driverName, long epochSeconds) {
        Driver driver = driverMap.get(driverName);
        if (driver == null) {
            System.err.println("Driver " + driverName + " not found. Cannot add delivery.");
            return;
        }
        driver.addDelivery(new Delivery(epochSeconds));
        System.out.println("Delivery at " + epochSeconds + " added for driver " + driverName + ".");
    }

    @Override
    public double clearPayments(long clearTime) {
        double totalPayment = 0;
        for (Driver driver : driverMap.values()) {
            // Iterate over pending deliveries safely using the iterator.
            Iterator<Delivery> it = driver.getDeliveries().iterator();
            while (it.hasNext()) {
                Delivery delivery = it.next();
                if (delivery.getEpochTimestamp() <= clearTime) {
                    // Calculate the payment for that delivery.
                    totalPayment += paymentCalculator.calculatePayment(driver, delivery);
                    it.remove(); // Mark the delivery as cleared by removing it.
                }
            }
        }
        System.out.println("Cleared payments up to time " + clearTime + " with total amount: " + totalPayment);
        return totalPayment;
    }

    @Override
    public double getUnpaidTotal() {
        double total = 0;
        for (Driver driver : driverMap.values()) {
            for (Delivery delivery : driver.getDeliveries()) {
                total += paymentCalculator.calculatePayment(driver, delivery);
            }
        }
        System.out.println("Total unpaid amount is: " + total);
        return total;
    }
}

// Main.java
