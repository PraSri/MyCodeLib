package Rippling.delivery;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Test harness for DeliverySystemConcurrent covering key edge cases
 * and a simple concurrency scenario.
 */
public class DeliverySystemConcurrentTest {
    private static int testsRun = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) throws InterruptedException {
        testNoDrivers();
        testInvalidTime();
        testSingleDeliveryLifecycle();
        testMultipleDriversAssignment();
        testOverlappingDeliveriesNoDriver();
        testPayUpToTimeMonotonicity();
        testIdempotentPayUpToTime();
        testConcurrentAddDeliveries();
        summary();
    }

    private static void assertEquals(long expected, long actual, String name) {
        testsRun++;
        if (expected != actual) {
            System.err.printf("FAIL [%s]: expected %d but got %d%n",
                    name, expected, actual);
            testsFailed++;
        }
    }

    private static void expectException(Runnable r,
                                        Class<? extends Exception> exClass,
                                        String name) {
        testsRun++;
        try {
            r.run();
            System.err.printf("FAIL [%s]: expected %s but no exception%n",
                    name, exClass.getSimpleName());
            testsFailed++;
        }
        catch (Exception e) {
            if (!exClass.isInstance(e)) {
                System.err.printf("FAIL [%s]: expected %s but got %s%n",
                        name,
                        exClass.getSimpleName(),
                        e.getClass().getSimpleName());
                testsFailed++;
            }
        }
    }

    // 1. No drivers ? addDelivery should throw IllegalStateException
    private static void testNoDrivers() {
        DeliverySystemConcurrent sys = new DeliverySystemConcurrent();
        expectException(
                () -> sys.addDelivery(0, 10),
                IllegalStateException.class,
                "No drivers available"
        );
    }

    // 2. endTime < startTime ? IllegalArgumentException
    private static void testInvalidTime() {
        DeliverySystemConcurrent sys = new DeliverySystemConcurrent();
        sys.addDriver("d1");
        expectException(
                () -> sys.addDelivery(20, 10),
                IllegalArgumentException.class,
                "Invalid time range"
        );
    }

    // 3. Single delivery: cost, partial/full payments
    private static void testSingleDeliveryLifecycle() {
        DeliverySystemConcurrent sys = new DeliverySystemConcurrent();
        sys.addDriver("d1");
        sys.addDelivery(0, 100);
        // total cost = 10*100 + 7*1 = 1007
        assertEquals(1007, sys.getTotalCost(), "Total cost initial");
        assertEquals(1007, sys.getCostToBePaid(), "Cost to be paid initial");

        sys.payUpToTime(50);
        long paidPart = 10L * 50 + 7L * 0; // 500
        assertEquals(1007 - paidPart, sys.getCostToBePaid(), "Cost after partial pay");

        sys.payUpToTime(100);
        assertEquals(0, sys.getCostToBePaid(), "Cost after full pay");
    }

    // 4. Multiple drivers: overlapping deliveries assigned correctly
    private static void testMultipleDriversAssignment() {
        DeliverySystemConcurrent sys = new DeliverySystemConcurrent();
        sys.addDriver("d1");
        sys.addDriver("d2");
        sys.addDelivery(0, 30);
        sys.addDelivery(0, 30);
        // total minutes=60, deliveries=2
        assertEquals(10*60 + 7*2, sys.getTotalCost(), "Total cost two drivers");
        sys.payUpToTime(30);
        assertEquals(0, sys.getCostToBePaid(), "Cost after settling both");
    }

    // 5. Too many overlapping deliveries ? IllegalStateException
    private static void testOverlappingDeliveriesNoDriver() {
        DeliverySystemConcurrent sys = new DeliverySystemConcurrent();
        sys.addDriver("d1");
        sys.addDriver("d2");
        sys.addDelivery(0, 10);
        sys.addDelivery(0, 10);
        expectException(
                () -> sys.addDelivery(0, 10),
                IllegalStateException.class,
                "No driver for third overlap"
        );
    }

    // 6. payUpToTime backwards ? IllegalArgumentException
    private static void testPayUpToTimeMonotonicity() {
        DeliverySystemConcurrent sys = new DeliverySystemConcurrent();
        sys.addDriver("d1");
        sys.addDelivery(0, 10);
        sys.payUpToTime(5);
        expectException(
                () -> sys.payUpToTime(2),
                IllegalArgumentException.class,
                "Pay time backward"
        );
    }

    // 7. payUpToTime called twice with same time ? no effect
    private static void testIdempotentPayUpToTime() {
        DeliverySystemConcurrent sys = new DeliverySystemConcurrent();
        sys.addDriver("d1");
        sys.addDelivery(0, 10);
        sys.payUpToTime(5);
        long cost1 = sys.getCostToBePaid();
        sys.payUpToTime(5);
        long cost2 = sys.getCostToBePaid();
        assertEquals(cost1, cost2, "Idempotent payUpToTime");
    }

    // 8. Concurrency test: multiple threads adding deliveries concurrently
    private static void testConcurrentAddDeliveries() throws InterruptedException {
        DeliverySystemConcurrent sys = new DeliverySystemConcurrent();
        sys.addDriver("d1");
        sys.addDriver("d2");
        sys.addDriver("d3");

        final int threads = 5;
        final int deliveriesPerThread = 20;
        final int duration = 10;

        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch  = new CountDownLatch(threads);
        AtomicBoolean exceptionOccurred = new AtomicBoolean(false);

        for (int t = 0; t < threads; t++) {
            final int tid = t;
            Executors.newSingleThreadExecutor().submit(() -> {
                try {
                    startLatch.await();
                    for (int i = 0; i < deliveriesPerThread; i++) {
                        long start = tid * 1000L + i * duration;
                        long end   = start + duration;
                        sys.addDelivery(start, end);
                    }
                } catch (Exception e) {
                    exceptionOccurred.set(true);
                } finally {
                    doneLatch.countDown();
                }
            });
        }

        // Start all threads
        startLatch.countDown();
        // Wait for completion
        doneLatch.await();

        testsRun++;
        if (exceptionOccurred.get()) {
            System.err.println("FAIL [Concurrent adds]: exception in threads");
            testsFailed++;
        }

        // Compute expected totals
        long totalCount  = (long) threads * deliveriesPerThread;
        long totalMin    = totalCount * duration;
        long expectedCost= 10 * totalMin + 7 * totalCount;

        assertEquals(expectedCost, sys.getTotalCost(), "Concurrent total cost");
        assertEquals(expectedCost, sys.getCostToBePaid(), "Concurrent cost before pay");

        // Settle everything
        sys.payUpToTime(Long.MAX_VALUE / 2);
        assertEquals(0, sys.getCostToBePaid(), "Concurrent cost after full pay");
    }

    private static void summary() {
        System.out.printf("%nTests run: %d, Failures: %d%n",
                testsRun, testsFailed);
        if (testsFailed == 0) {
            System.out.println("ALL TESTS PASSED");
        } else {
            System.err.println("SOME TESTS FAILED");
        }
    }
}