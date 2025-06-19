package Rippling.delivery;

import java.util.*;

public class DeliverySystemV2 {
    private Set<String> drivers;
    private PriorityQueue<Delivery> unstartedDeliveries;
    private PriorityQueue<Delivery> activeDeliveries;
    private long totalSettledMinutes;
    private int totalSettledDeliveryCount;
    private long totalMinutesAll;
    private int totalDeliveriesAll;
    private long lastSettledTime;

    public DeliverySystemV2() {
        drivers = new HashSet<>();
        unstartedDeliveries = new PriorityQueue<>(Comparator.comparingLong(d -> d.start));
        activeDeliveries = new PriorityQueue<>(Comparator.comparingLong(d -> d.end));
        totalSettledMinutes = 0;
        totalSettledDeliveryCount = 0;
        totalMinutesAll = 0;
        totalDeliveriesAll = 0;
        lastSettledTime = Long.MIN_VALUE;
    }

    public void addDriver(String driverId) {
        drivers.add(driverId);
    }

    public void addDelivery(long startTime, long endTime) {
        if (drivers.isEmpty()) {
            throw new IllegalStateException("No drivers available to assign the delivery");
        }
        if (endTime < startTime) {
            throw new IllegalArgumentException("endTime must not be less than startTime");
        }
        totalMinutesAll += (endTime - startTime);
        totalDeliveriesAll++;
        unstartedDeliveries.add(new Delivery(startTime, endTime));
    }

    public void payUpToTime(long upToTime) {
        if (upToTime < lastSettledTime) {
            throw new IllegalArgumentException("upToTime must not be less than lastSettledTime");
        }
        if (upToTime == lastSettledTime) {
            return;
        }

        while (!unstartedDeliveries.isEmpty() && unstartedDeliveries.peek().start <= upToTime) {
            activeDeliveries.add(unstartedDeliveries.poll());
        }

        long minutesThisCall = 0;
        int completedDeliveriesThisCall = 0;
        List<Delivery> stillActive = new ArrayList<>();

        while (!activeDeliveries.isEmpty()) {
            Delivery d = activeDeliveries.poll();
            long segmentStart = Math.max(d.start, lastSettledTime);
            if (d.end <= upToTime) {
                if (d.end > segmentStart) {
                    minutesThisCall += (d.end - segmentStart);
                }
                completedDeliveriesThisCall++;
            } else {
                if (upToTime > segmentStart) {
                    minutesThisCall += (upToTime - segmentStart);
                }
                stillActive.add(d);
            }
        }

        for (Delivery d : stillActive) {
            activeDeliveries.add(d);
        }

        totalSettledMinutes += minutesThisCall;
        totalSettledDeliveryCount += completedDeliveriesThisCall;
        lastSettledTime = upToTime;
    }

    public long getTotalCost() {
        return 10 * totalMinutesAll + 7 * totalDeliveriesAll;
    }

    public long getCostToBePaid() {
        long remainingMinutes = totalMinutesAll - totalSettledMinutes;
        int remainingDeliveries = totalDeliveriesAll - totalSettledDeliveryCount;
        return 10 * remainingMinutes + 7 * remainingDeliveries;
    }

    private static class Delivery {
        long start;
        long end;

        Delivery(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }

}
