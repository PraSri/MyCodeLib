package Rippling.delivery;

/**
 * A simple test harness for DeliverySystemV3 covering edge cases.
 * Run with: java DeliverySystemV3Test
 */
public class DeliverySystemV3Test {
    private static int testsRun = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        testNoDrivers();
        testInvalidTime();
        testSingleDeliveryLifecycle();
        testMultipleDriversAssignment();
        testOverlappingDeliveriesNoDriver();
        testPayUpToTimeMonotonicity();
        testIdempotentPayUpToTime();
        summary();
    }

    private static void assertEquals(long expected, long actual, String name) {
        testsRun++;
        if (expected != actual) {
            System.err.printf(
                    "FAIL [%s]: expected %d but got %d%n",
                    name, expected, actual
            );
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
        } catch (Exception e) {
            if (!exClass.isInstance(e)) {
                System.err.printf(
                        "FAIL [%s]: expected %s but got %s%n",
                        name,
                        exClass.getSimpleName(),
                        e.getClass().getSimpleName()
                );
                testsFailed++;
            }
        }
    }

    // 1. No drivers ? addDelivery should throw IllegalStateException
    private static void testNoDrivers() {
        DeliverySystemV3 sys = new DeliverySystemV3();
        expectException(
                () -> sys.addDelivery(0, 10),
                IllegalStateException.class,
                "No drivers available"
        );
    }

    // 2. endTime < startTime ? IllegalArgumentException
    private static void testInvalidTime() {
        DeliverySystemV3 sys = new DeliverySystemV3();
        sys.addDriver("d1");
        expectException(
                () -> sys.addDelivery(20, 10),
                IllegalArgumentException.class,
                "Invalid time range"
        );
    }

    // 3. Single delivery: cost, partial/full payments
    private static void testSingleDeliveryLifecycle() {
        DeliverySystemV3 sys = new DeliverySystemV3();
        sys.addDriver("d1");
        // add one 0?100 delivery
        sys.addDelivery(0, 100);
        // total cost = 10*100 + 7*1 = 1000 + 7 = 1007
        assertEquals(1007, sys.getTotalCost(), "Total cost initial");
        // none paid yet ? costToBePaid = total
        assertEquals(1007, sys.getCostToBePaid(), "Cost to be paid initial");

        // pay up to time 50 ? minutes paid = 50, deliveries completed = 0
        sys.payUpToTime(50);
        long paid = 10L * 50 + 7L * 0;        // 500
        assertEquals(1007 - paid, sys.getCostToBePaid(),
                "Cost after partial pay");

        // pay up to time 100 ? completes delivery: adds 10*50 + 7*1 = 507 more
        sys.payUpToTime(100);
        assertEquals(0, sys.getCostToBePaid(), "Cost after full pay");
    }

    // 4. Multiple drivers: overlapping deliveries assigned correctly
    private static void testMultipleDriversAssignment() {
        DeliverySystemV3 sys = new DeliverySystemV3();
        sys.addDriver("d1");
        sys.addDriver("d2");
        // two simultaneous deliveries
        sys.addDelivery(0, 30);   // goes to d1
        sys.addDelivery(0, 30);   // goes to d2
        // both total 60 minutes, 2 deliveries
        assertEquals(10 * 60 + 7 * 2, sys.getTotalCost(),
                "Total cost two drivers");

        // settle all
        sys.payUpToTime(30);
        assertEquals(0, sys.getCostToBePaid(),
                "Cost after settling both");
    }

    // 5. Too many overlapping deliveries ? IllegalStateException
    private static void testOverlappingDeliveriesNoDriver() {
        DeliverySystemV3 sys = new DeliverySystemV3();
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
        DeliverySystemV3 sys = new DeliverySystemV3();
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
        DeliverySystemV3 sys = new DeliverySystemV3();
        sys.addDriver("d1");
        sys.addDelivery(0, 10);
        sys.payUpToTime(5);
        long cost1 = sys.getCostToBePaid();
        sys.payUpToTime(5);
        long cost2 = sys.getCostToBePaid();
        assertEquals(cost1, cost2, "Idempotent payUpToTime");
    }

    private static void summary() {
        System.out.printf(
                "%nTests run: %d, Failures: %d%n",
                testsRun, testsFailed
        );
        if (testsFailed == 0) {
            System.out.println("ALL TESTS PASSED");
        } else {
            System.err.println("SOME TESTS FAILED");
        }
    }
}