package Rippling.delivery;

import java.util.HashSet;
import java.util.Set;

public class DeliverySystem {
    private Set<String> drivers; // driver can be an entity
    private long totalMinutes;
    private int totalDeliveries; // delivery can be an entity

    public DeliverySystem() {
        drivers = new HashSet<>();
        totalMinutes = 0;
        totalDeliveries = 0;
    }

    public void addDriver(String driverId) {
        drivers.add(driverId);
    }

    public void addDelivery(long startTime, long endTime) {
        if (drivers.isEmpty()) {
            throw new IllegalStateException("No drivers available to assign the delivery");
        }
        long duration = endTime - startTime;
        totalMinutes += duration;
        totalDeliveries++;
    }

    // we can have global counter, to add the cost while assigning each delivery
    public long getTotalCost() {
        return 10 * totalMinutes + 7L * totalDeliveries;
    }
}