package Rippling.delivery.v4;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class DeliverySystemV4 {
    // track drivers, assignments, schedules...
    private final Map<String, Driver> drivers = new HashMap<>();
    private final List<Assignment> allAssignments = new ArrayList<>();
    private final BillingStrategy billingStrategy;

    private PriorityQueue<Delivery> pendingDeliveries = new PriorityQueue<>();

    private final Map<String, TreeSet<Delivery>> driverSchedules;

    public DeliverySystemV4(BillingStrategy strategy) {
        this.billingStrategy = strategy;
        this.driverSchedules = new HashMap<>();
    }

    public void addDriver(Driver d) {
        drivers.put(d.id, d);
        driverSchedules.put(d.id, new TreeSet<>());
    }

    public void addDelivery(LocalDateTime startTime, LocalDateTime endTime) {
        if (driverSchedules.isEmpty()) {
            throw new IllegalStateException("No drivers available to assign the delivery");
        }
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("endTime must not be less than startTime");
        }

        Delivery delivery = new Delivery(startTime, endTime);
        boolean assigned = false;
        for (Map.Entry<String, TreeSet<Delivery>> entry : driverSchedules.entrySet()) {
            String driverId = entry.getKey();
            TreeSet<Delivery> schedule = entry.getValue();
            if (isAvailable(schedule, delivery)) {
                schedule.add(delivery);
                assigned = true;
                recordAssignment(driverId, delivery);
            }
        }
        if (!assigned) {
            throw new IllegalArgumentException("No driver available for delivery");
        }
        pendingDeliveries.add(delivery);
    }

    private boolean isAvailable(TreeSet<Delivery> schedule, Delivery delivery) {
        Delivery candidate = schedule.floor(new Delivery(delivery.end, delivery.end));
        if (candidate != null && candidate.end.isAfter(delivery.start) && candidate.start.isBefore(delivery.end)) {
            return false;
        }
        return true;
    }

    // to look up the Assignment for a given Delivery
    private final Map<Delivery, Assignment> assignmentMap = new HashMap<>();

    // after matching a Delivery to a Driver:
    public void recordAssignment(String driverId, Delivery del) {
        Driver d = drivers.get(driverId);
        Assignment a = new Assignment(d, del);
        a.setCreatedAt(LocalDateTime.now());
        allAssignments.add(a);
        assignmentMap.put(del, a);
    }

    /**
     * sums cost across all assignments
     */
    public BigDecimal getTotalCost() {
        return allAssignments.stream().map(billingStrategy::computeCost).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private LocalDateTime lastSettledTime = LocalDateTime.MIN;

    // accumulate the cost of all deliveries we've “paid”
    private BigDecimal totalSettledCost = BigDecimal.ZERO;

    private PriorityQueue<Delivery> activeDeliveries = new PriorityQueue<>();


    // settle payment to this current time. i.e. upToTime
    public void payUpToTime(LocalDateTime upToTime) {
        // guard against going backwards
        if (upToTime.isBefore(lastSettledTime)) {
            throw new IllegalArgumentException("upToTime must not be less than lastSettledTime");
        }
        // idempotent if called at the same instant
        if (upToTime.equals(lastSettledTime)) {
            return;
        }

        // 1) move newly?started deliveries into active
        while (!pendingDeliveries.isEmpty() && !pendingDeliveries.peek().start.isAfter(upToTime)) {
            activeDeliveries.add(pendingDeliveries.poll());
        }

        // 2) settle any that have finished by upToTime
        List<Delivery> stillActive = new ArrayList<>();
        while (!activeDeliveries.isEmpty()) {
            Delivery d = activeDeliveries.poll();
            // if delivery ended on or before our cutoff, fully pay it
            if (!d.end.isAfter(upToTime)) {
                Assignment a = assignmentMap.get(d);
                // run your billing strategy for the full assignment
                totalSettledCost = totalSettledCost.add(billingStrategy.computeCost(a));
            } else {
                // still in?flight ? leave it for next round
                stillActive.add(d);
            }
        }
        // put the in?flight ones back
        activeDeliveries.addAll(stillActive);

        // 3) bump our watermark
        lastSettledTime = upToTime;
    }

    public BigDecimal getCostToBePaid() {
        BigDecimal pending = BigDecimal.ZERO;

        // all deliveries that haven't even started
        for (Delivery d : pendingDeliveries) {
            pending = pending.add(billingStrategy.computeCost(assignmentMap.get(d)));
        }

        // plus those in-flight that haven't been fully paid
        for (Delivery d : activeDeliveries) {
            pending = pending.add(billingStrategy.computeCost(assignmentMap.get(d)));
        }

        return pending;
    }

    /**
     * @param currentTime end of the 24h window
     * @return maximum number of drivers simultaneously on a delivery
     * during [currentTime?24h, currentTime]
     */
    public int getMaxActiveDriversInLast24Hours(LocalDateTime currentTime) {
        LocalDateTime windowStart = currentTime.minusHours(24);

        // gather +1/–1 events for every delivery that overlaps the window
        List<Event> events = new ArrayList<>();
        for (TreeSet<Delivery> schedule : driverSchedules.values()) {
            for (Delivery d : schedule) {
                // skip if entirely before or after
                if (d.end.isBefore(windowStart) || d.start.isAfter(currentTime)) {
                    continue;
                }
                // clip to [windowStart, currentTime]
                LocalDateTime s = d.start.isBefore(windowStart) ? windowStart : d.start;
                LocalDateTime e = d.end.isAfter(currentTime) ? currentTime : d.end;
                events.add(new Event(s, +1));
                events.add(new Event(e, -1));
            }
        }

        // sort by time; if same instant, +1 comes before -1
        events.sort(Comparator.comparing((Event ev) -> ev.time).thenComparingInt(ev -> -ev.delta));

        // sweep to find peak
        int curr = 0, peak = 0;
        for (Event ev : events) {
            curr += ev.delta;
            peak = Math.max(peak, curr);
        }
        return peak;
    }

    public int getMaxActiveDriversIn24HrWindow(LocalDateTime currentTime) {
        // get assignements of last 24 hr window
        // get the drivers, put them set for unique drivers
        LocalDateTime windowStart = currentTime.minusHours(24);
        Set<Driver> driversInActiveWindow = new HashSet<>();
        // window -> [windowStart, currentTime]
        for (Assignment a : allAssignments) {
            if (a.getCreatedAt().isBefore(windowStart) && a.getCreatedAt().isAfter(currentTime)) {
                continue;
            } else {
                driversInActiveWindow.add(a.getDriver());
            }
        }
        return driversInActiveWindow.size();
    }

    // helper for line-sweep
    private static class Event {
        final LocalDateTime time;
        final int delta; // +1 at start, -1 at end

        Event(LocalDateTime time, int delta) {
            this.time = time;
            this.delta = delta;
        }
    }

    // … rest of DeliverySystemV4 …
}
