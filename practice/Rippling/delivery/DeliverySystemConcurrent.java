package Rippling.delivery;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;

public class DeliverySystemConcurrent {
    static class Delivery implements Comparable<Delivery> {
        final long start, end;

        Delivery(long s, long e) {
            start = s;
            end = e;
        }

        @Override
        public int compareTo(Delivery o) {
            int c = Long.compare(start, o.start);
            return c != 0 ? c : Long.compare(end, o.end);
        }
    }

    // thread-safe driver ? schedule map
    private final ConcurrentMap<String, NavigableSet<Delivery>> driverSchedules =
            new ConcurrentHashMap<>();

    // thread-safe priority queues
    private final PriorityBlockingQueue<Delivery> unstartedDeliveries =
            new PriorityBlockingQueue<>(11, Comparator.comparingLong(d -> d.start));
    private final PriorityBlockingQueue<Delivery> activeDeliveries =
            new PriorityBlockingQueue<>(11, Comparator.comparingLong(d -> d.end));

    // atomic counters
    private final AtomicLong totalSettledMinutes = new AtomicLong();
    private final AtomicInteger totalSettledDeliveries = new AtomicInteger();
    private final AtomicLong totalMinutesAll = new AtomicLong();
    private final AtomicInteger totalDeliveriesAll = new AtomicInteger();
    private final AtomicLong lastSettledTime = new AtomicLong(Long.MIN_VALUE);

    // a global lock for schedule?assignment
    private final ReentrantLock scheduleLock = new ReentrantLock();

    // a separate lock for payUpToTime
    private final ReentrantLock settleLock = new ReentrantLock();

    public void addDriver(String driverId) {
        // putIfAbsent so multiple threads can’t clobber each other
        driverSchedules.putIfAbsent(
                driverId,
                // use ConcurrentSkipListSet for per-driver thread safety
                new ConcurrentSkipListSet<>()
        );
    }

    public void addDelivery(long startTime, long endTime) {
        if (driverSchedules.isEmpty())
            throw new IllegalStateException("No drivers available");

        if (endTime < startTime)
            throw new IllegalArgumentException("endTime < startTime");

        Delivery d = new Delivery(startTime, endTime);
        boolean assigned = false;

        // lock the entire assignment step so two threads don’t both pick the same slot
        scheduleLock.lock();
        try {
            for (NavigableSet<Delivery> schedule : driverSchedules.values()) {
                // floor gives us the delivery with greatest start ? newDelivery.end
                Delivery floor = schedule.floor(new Delivery(d.end, d.end));
                boolean overlap = floor != null && floor.end > d.start;
                if (!overlap) {
                    schedule.add(d);
                    assigned = true;
                    break;
                }
            }
        } finally {
            scheduleLock.unlock();
        }

        if (!assigned)
            throw new IllegalStateException("No driver available for that time");

        // these are thread-safe queues
        unstartedDeliveries.add(d);

        // atomic counters
        totalMinutesAll.addAndGet(endTime - startTime);
        totalDeliveriesAll.incrementAndGet();
    }

    public void payUpToTime(long upToTime) {
        // serialize all payment computations
        settleLock.lock();
        try {
            long last = lastSettledTime.get();
            if (upToTime < last)
                throw new IllegalArgumentException("upToTime < lastSettledTime");
            if (upToTime == last)
                return;

            // move newly started deliveries into active set
            while (true) {
                Delivery peek = unstartedDeliveries.peek();
                if (peek == null || peek.start > upToTime) break;
                activeDeliveries.add(unstartedDeliveries.poll());
            }

            long minutesThisCall = 0;
            int doneThisCall = 0;
            ConcurrentLinkedQueue<Delivery> stillActive = new ConcurrentLinkedQueue<>();

            // settle active deliveries
            Delivery d;
            while ((d = activeDeliveries.poll()) != null) {
                long segStart = Math.max(d.start, last);
                if (d.end <= upToTime) {
                    if (d.end > segStart)
                        minutesThisCall += d.end - segStart;
                    doneThisCall++;
                } else {
                    if (upToTime > segStart)
                        minutesThisCall += upToTime - segStart;
                    stillActive.add(d);
                }
            }
            // re-enqueue those still going
            activeDeliveries.addAll(stillActive);

            // update globals
            totalSettledMinutes.addAndGet(minutesThisCall);
            totalSettledDeliveries.addAndGet(doneThisCall);
            lastSettledTime.set(upToTime);
        } finally {
            settleLock.unlock();
        }
    }

    /**
     * total cost for all ever-added deliveries
     */
    public long getTotalCost() {
        // 10 * total minutes  +  7 * total deliveries
        return 10 * totalMinutesAll.get()
                + 7 * totalDeliveriesAll.get();
    }

    /**
     * remaining cost yet to be paid
     */
    public long getCostToBePaid() {
        long remainingMin = totalMinutesAll.get() - totalSettledMinutes.get();
        int remainingDel = totalDeliveriesAll.get() - totalSettledDeliveries.get();
        return 10 * remainingMin + 7 * remainingDel;
    }
}