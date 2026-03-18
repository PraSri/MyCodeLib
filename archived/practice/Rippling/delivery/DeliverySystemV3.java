package Rippling.delivery;

import java.util.*;

public class DeliverySystemV3 {
    static class Delivery implements Comparable<Delivery> {
        long start;
        long end;

        Delivery(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Delivery other) {
            int cmp = Long.compare(this.start, other.start);
            if (cmp != 0) return cmp;
            return Long.compare(this.end, other.end);
        }
    }

    private Map<String, TreeSet<Delivery>> driverSchedules;
    private PriorityQueue<Delivery> unstartedDeliveries;
    private PriorityQueue<Delivery> activeDeliveries;
    private long totalSettledMinutes;
    private int totalSettledDeliveryCount;
    private long totalMinutesAll;
    private int totalDeliveriesAll;
    private long lastSettledTime;

    public DeliverySystemV3() {
        driverSchedules = new HashMap<>();
        unstartedDeliveries = new PriorityQueue<>(Comparator.comparingLong(d -> d.start));
        activeDeliveries = new PriorityQueue<>(Comparator.comparingLong(d -> d.end));
        totalSettledMinutes = 0;
        totalSettledDeliveryCount = 0;
        totalMinutesAll = 0;
        totalDeliveriesAll = 0;
        lastSettledTime = Long.MIN_VALUE;
    }

    public void addDriver(String driverId) {
        driverSchedules.put(driverId, new TreeSet<>());
    }

    public void addDelivery(long startTime, long endTime) {
        if (driverSchedules.isEmpty()) {
            throw new IllegalStateException("No drivers available to assign the delivery");
        }
        if (endTime < startTime) {
            throw new IllegalArgumentException("endTime must not be less than startTime");
        }

        Delivery delivery = new Delivery(startTime, endTime);
        boolean assigned = false;
        for (Map.Entry<String, TreeSet<Delivery>> entry : driverSchedules.entrySet()) {
            TreeSet<Delivery> schedule = entry.getValue();
            if (isAvailable(schedule, delivery)) {
                schedule.add(delivery);
                assigned = true;
                // each delivery cost -> driver.perHourRate * (hours taken to complete the delivery)
                break;
            }
        }

        if (!assigned) {
            throw new IllegalStateException("No driver available for the delivery at the given time");
        }

        unstartedDeliveries.add(delivery);
        totalMinutesAll += (endTime - startTime);
        totalDeliveriesAll++;
    }

    private boolean isAvailable(TreeSet<Delivery> schedule, Delivery delivery) {
        Delivery candidate = schedule.floor(new Delivery(delivery.end, delivery.end));
        if (candidate != null && candidate.end > delivery.start && candidate.start < delivery.end) {
            return false;
        }
        return true;
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


    /**
     * Returns the maximum number of drivers who were simultaneously
     * on a delivery at any instant in the 24-hour window
     * [currentTime - 24*60, currentTime].
     * <p>
     * Assumes all times are in the same unit (e.g. minutes since epoch).
     */
    public int getMaxActiveDriversInLast24Hours(long currentTime) {
        long windowStart = currentTime - 24L * 60; // 24 hours in minutes
        List<Event> events = new ArrayList<>();

        // build start/end events for any delivery overlapping the window
        for (Map.Entry<String, TreeSet<Delivery>> e : driverSchedules.entrySet()) {
            for (Delivery d : e.getValue()) {
                if (d.end <= windowStart || d.start >= currentTime) {
                    // completely outside the window
                    continue;
                }
                long s = Math.max(d.start, windowStart);
                long t = Math.min(d.end, currentTime);
                events.add(new Event(s, +1));
                events.add(new Event(t, -1));
            }
        }

        // sweep
        Collections.sort(events);
        int maxConcurrent = 0, curr = 0;
        for (Event ev : events) {
            curr += ev.delta;
            maxConcurrent = Math.max(maxConcurrent, curr);
        }
        return maxConcurrent;
    }

    // small helper to mark +1 at start, -1 at end
    static class Event implements Comparable<Event> {
        public long time;
        public int delta;

        Event(long time, int delta) {
            this.time = time;
            this.delta = delta;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getDelta() {
            return delta;
        }

        public void setDelta(int delta) {
            this.delta = delta;
        }

        @Override
        public int compareTo(Event e) {
            int cmp = Long.compare(this.time, e.time);
            return cmp != 0 ? cmp : Long.compare(this.delta, e.delta);
        }
    }
}
