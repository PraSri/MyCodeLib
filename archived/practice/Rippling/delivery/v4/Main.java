package Rippling.delivery.v4;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        // 1. build drivers
        var d1 = new Driver("alice", BigDecimal.valueOf(1.5), BigDecimal.valueOf(0.5), BigDecimal.valueOf(20));
        var d2 = new Driver("bob", BigDecimal.valueOf(1.2), BigDecimal.valueOf(0.6), BigDecimal.valueOf(25));

        // 2. pick a billing policy
        BillingStrategy policy = new StandardBilling();
        // or: BillingStrategy policy = new SurgeBilling(BigDecimal.valueOf(1.3));

        DeliverySystemV4 sys = new DeliverySystemV4(policy);
        sys.addDriver(d1);
        sys.addDriver(d2);

        // 3. add deliveries & assignments
        Delivery del = new Delivery(LocalDateTime.now().minusHours(2), LocalDateTime.now(), 15.0);
        sys.recordAssignment("alice", del);

        // 4. compute cost

        System.out.println("Total Cost = Rs. " + sys.getTotalCost());

        tests();
    }

    /**
     * Demo / simple test harness for DeliverySystemV4.
     */
    public static void tests() {
        // 1) Build some drivers with per?minute, per?km rates, and a flat fee
        Driver alice = new Driver("alice", BigDecimal.valueOf(1.50),    // Rs. 1.50 per minute
                BigDecimal.valueOf(0.50),    // Rs. 0.50 per km
                BigDecimal.valueOf(20.00)    // Rs. 20 flat fee
        );
        Driver bob = new Driver("bob", BigDecimal.valueOf(1.20), BigDecimal.valueOf(0.60), BigDecimal.valueOf(25.00));

        // 2) Choose a billing policy (standard time+distance+flat)
        BillingStrategy billing = new StandardBilling();

        // 3) Create the system and register drivers
        DeliverySystemV4 system = new DeliverySystemV4(billing);
        system.addDriver(alice);
        system.addDriver(bob);

        // 4) Create some deliveries at various times
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twoHoursAgo = now.minusHours(2);
        LocalDateTime oneHourAgo = now.minusHours(1);
        LocalDateTime inThirty = now.plusMinutes(30);
        LocalDateTime inTwoHours = now.plusHours(2);

        // 4a) Completed delivery: two hours ago ? one hour ago (1h = 60m, Rs. 1.5×60 + Rs. 20 = Rs. 110)
        system.addDelivery(twoHoursAgo, oneHourAgo);

        // 4b) In?flight delivery: started one hour ago ? ends in 30m (1.5h = 90m)
        system.addDelivery(oneHourAgo, inThirty);

        // 4c) Pending delivery: starts in 30m ? ends in 2h
        system.addDelivery(inThirty, inTwoHours);

        // 5) Before any settlement:
        System.out.println("=== Before payUpToTime ===");
        System.out.println("Total Cost          = Rs. " + system.getTotalCost());
        System.out.println("Cost To Be Paid     = Rs. " + system.getCostToBePaid());

        // 6) Pay up to 'now' (this settles only the completed delivery)
        system.payUpToTime(now);
        System.out.println("\n=== After payUpToTime(now) ===");
        System.out.println("Total Cost          = Rs. " + system.getTotalCost());
        System.out.println("Cost To Be Paid     = Rs. " + system.getCostToBePaid());

        // 7) Pay up to 'inTwoHours' (this settles everything)
        system.payUpToTime(inTwoHours);
        System.out.println("\n=== After payUpToTime(inTwoHours) ===");
        System.out.println("Total Cost          = Rs. " + system.getTotalCost());
        System.out.println("Cost To Be Paid     = Rs. " + system.getCostToBePaid());

        testMaxDriversIn24HourWindow();
    }

    private static void testMaxDriversIn24HourWindow() {
        DeliverySystemV4 sys = new DeliverySystemV4(new StandardBilling());
// … add drivers & deliveries …
        // 1) Build some drivers with per?minute, per?km rates, and a flat fee
        Driver alice = new Driver("alice", BigDecimal.valueOf(1.50),    // Rs. 1.50 per minute
                BigDecimal.valueOf(0.50),    // Rs. 0.50 per km
                BigDecimal.valueOf(20.00)    // Rs. 20 flat fee
        );
        Driver bob = new Driver("bob", BigDecimal.valueOf(1.20), BigDecimal.valueOf(0.60), BigDecimal.valueOf(25.00));

        sys.addDriver(alice);
        sys.addDriver(bob);
        // 4) Create some deliveries at various times
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twoHoursAgo = now.minusHours(2);
        LocalDateTime oneHourAgo = now.minusHours(1);
        LocalDateTime inThirty = now.plusMinutes(30);
        LocalDateTime inTwoHours = now.plusHours(2);

        // 4a) Completed delivery: two hours ago ? one hour ago (1h = 60m, Rs. 1.5×60 + Rs. 20 = Rs. 110)
        sys.addDelivery(twoHoursAgo, oneHourAgo);

        // 4b) In?flight delivery: started one hour ago ? ends in 30m (1.5h = 90m)
        sys.addDelivery(oneHourAgo, inThirty);

        // 4c) Pending delivery: starts in 30m ? ends in 2h
        sys.addDelivery(inThirty, inTwoHours);
        int maxDrivers = sys.getMaxActiveDriversInLast24Hours(LocalDateTime.now());
        int maxDrivers2 = sys.getMaxActiveDriversIn24HrWindow(LocalDateTime.now());
        System.out.println("Peak active drivers last 24h = " + maxDrivers);
        System.out.println("Peak active drivers last 24h = " + maxDrivers2);

    }
}
