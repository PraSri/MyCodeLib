package lld;

import java.util.*;

/**
 * Problem Statement:
 * Design an in-memory parking lot system that can be used to manage a multi-floor parking lot.
 *
 * Requirements:
 * 1. The parking lot has multiple floors. Each floor has multiple parking spots.
 * 2. There are 3 types of parking spots: SMALL, MEDIUM, LARGE.
 * 3. There are 3 types of vehicles: BIKE, CAR, TRUCK.
 *    - A Bike can park in SMALL, MEDIUM, or LARGE spot.
 *    - A Car can park in MEDIUM or LARGE spot.
 *    - A Truck can park only in a LARGE spot.
 * 4. The system should assign the smallest available spot that fits the vehicle (e.g., a Bike should prefer SMALL over MEDIUM).
 * 5. The system should support:
 *    - Park a vehicle -> returns a Ticket (with spot info, entry time)
 *    - Unpark a vehicle (given a ticket) -> frees the spot
 *    - Check availability -> display available spots per floor per type
 * 6. Each vehicle has a registration number and color.
 * 7. The system should not allow duplicate vehicles (same registration number) to park simultaneously.
 *
 * Constraints:
 * - Single entry/exit point (no concurrency needed for now)
 * - In-memory only, no database
 */
public class parkingLotLLD {

    public static void main(String[] args) {
        // Floor 1 with distinct spot IDs
        Spot s101 = new Spot(101, SpotType.SMALL, true);
        Spot s102 = new Spot(102, SpotType.MEDIUM, true);
        Spot s103 = new Spot(103, SpotType.LARGE, true);
        ParkingFloor f1 = new ParkingFloor(1, Arrays.asList(s101, s102, s103));

        // Floor 2 with distinct spot IDs
        Spot s201 = new Spot(201, SpotType.SMALL, true);
        Spot s202 = new Spot(202, SpotType.MEDIUM, true);
        Spot s203 = new Spot(203, SpotType.LARGE, true);
        ParkingFloor f2 = new ParkingFloor(2, Arrays.asList(s201, s202, s203));

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.parkingFloors = Arrays.asList(f1, f2);

        AssignmentService assignService = new SmallestSpotFirstStrategy();
        ParkingService parkingService = new ParkingService(assignService, parkingLot);

        // Vehicles
        Vehicle v1 = new Vehicle("123", "blue", VehicleType.BIKE);
        Vehicle v2 = new Vehicle("12345", "black", VehicleType.BIKE);
        Vehicle v3 = new Vehicle("1234578", "orange", VehicleType.BIKE);
        Vehicle v4 = new Vehicle("123457822", "black", VehicleType.CAR);
        Vehicle v5 = new Vehicle("123457822222", "red", VehicleType.TRUCK);
        Vehicle v51 = new Vehicle("123457822222", "red", VehicleType.TRUCK);
        Vehicle v6 = new Vehicle("12345782222112", "blue", VehicleType.CAR);
        Vehicle v7 = new Vehicle("12345722822222", "red", VehicleType.TRUCK);
        Vehicle v8 = new Vehicle("12342225722822222", "red", VehicleType.TRUCK);

        // Happy case
        System.out.println("--- Scenario 1: Park & Unpark Bike ---");
        Ticket t1 = parkingService.parkVehicle(v1);
        System.out.println("Parked: " + t1);
        System.out.println("Availability: " + parkingService.checkAvailabilityPerFloor());
        parkingService.unParkVehicle(t1);
        System.out.println("Unparked. Availability: " + parkingService.checkAvailabilityPerFloor());
        System.out.println();

        // Fallback working (park 3 bikes when 1 SMALL exists per floor = total 2 SMALL)
        System.out.println("--- Scenario 2: Bike Fallback (SMALL -> MEDIUM) ---");
        Ticket t2 = parkingService.parkVehicle(v2); // takes SMALL floor 1
        Ticket t3 = parkingService.parkVehicle(v3); // takes SMALL floor 2
        System.out.println("Bike 1: " + t2);
        System.out.println("Bike 2: " + t3);
        System.out.println("Availability before Bike 3: " + parkingService.checkAvailabilityPerFloor());
        
        // Bike 3 should fall back to MEDIUM on Floor 1
        Vehicle v3_extra = new Vehicle("EXTRA-BIKE", "green", VehicleType.BIKE);
        Ticket t3_extra = parkingService.parkVehicle(v3_extra);
        System.out.println("Bike 3 (fallback to MEDIUM): " + t3_extra);
        System.out.println("Availability after Bike 3: " + parkingService.checkAvailabilityPerFloor());
        System.out.println();

        // Park a Car and a Truck
        System.out.println("--- Scenario 3: Park Car and Truck ---");
        Ticket t4 = parkingService.parkVehicle(v4);
        Ticket t5 = parkingService.parkVehicle(v5);
        System.out.println("Car: " + t4);
        System.out.println("Truck: " + t5);
        System.out.println("Availability: " + parkingService.checkAvailabilityPerFloor());
        System.out.println();

        // Attempt to park a duplicate vehicle
        System.out.println("--- Scenario 4: Duplicate Vehicle Check ---");
        try {
            parkingService.parkVehicle(v51);
        } catch (Exception exception) {
            System.out.println("Expected Exception: " + exception.getMessage());
        }
        System.out.println();

        // Park until lot is full, then try one more
        System.out.println("--- Scenario 5: Park until Full & Full Lot Exception ---");
        Ticket t6 = parkingService.parkVehicle(v6);
        System.out.println("Availability before filling up: " + parkingService.checkAvailabilityPerFloor());
        try {
            Ticket t7 = parkingService.parkVehicle(v7);
        } catch (Exception exception) {
            System.out.println("Expected Exception for v7 (Truck): " + exception.getMessage());
        }
        System.out.println("Final Availability: " + parkingService.checkAvailabilityPerFloor());
    }

    public enum SpotType {
        SMALL, MEDIUM, LARGE
    }

    public enum VehicleType {
        CAR, BIKE, TRUCK
    }

    public interface AssignmentService {
        Ticket assignTicket(Vehicle vehicle, List<Spot> availableSpots);
    }

    public static class Spot {
        int id;
        SpotType type;
        boolean isAvailable;

        public Spot(int id, SpotType type, boolean isAvailable) {
            this.id = id;
            this.type = type;
            this.isAvailable = isAvailable;
        }

        @Override
        public String toString() {
            return "Spot{" +
                    "id=" + id +
                    ", type=" + type +
                    ", isAvailable=" + isAvailable +
                    '}';
        }
    }

    public static class Vehicle {
        String licensePlate;
        String color;
        VehicleType type;

        public Vehicle(String licensePlate, String color, VehicleType type) {
            this.licensePlate = licensePlate;
            this.color = color;
            this.type = type;
        }

        @Override
        public String toString() {
            return "Vehicle{" +
                    "licensePlate='" + licensePlate + '\'' +
                    ", color='" + color + '\'' +
                    ", type=" + type +
                    '}';
        }
    }

    public static class Ticket {
        String ticketId;
        Vehicle vehicle;
        Spot spot;
        long entryTime;
        long exitTime;
        double amount;

        public Ticket(String ticketId, Vehicle vehicle, Spot spot, long entryTime) {
            this.ticketId = ticketId;
            this.vehicle = vehicle;
            this.spot = spot;
            this.entryTime = entryTime;
            this.amount = 100.00; // hardcoded amount for all vehicles
        }

        @Override
        public String toString() {
            return "Ticket{" +
                    "ticketId='" + ticketId + '\'' +
                    ", vehicle=" + vehicle +
                    ", spot=" + spot +
                    ", entryTime=" + entryTime +
                    ", exitTime=" + exitTime +
                    ", amount=" + amount +
                    '}';
        }

        public void setExitTime(long exitTime) {
            this.exitTime = exitTime;
        }
    }

    public static class ParkingFloor {
        int floorNumber;
        List<Spot> spots;

        public ParkingFloor(int floorNumber, List<Spot> spots) {
            this.floorNumber = floorNumber;
            this.spots = spots;
        }
    }

    public static class ParkingLot {
        public List<ParkingFloor> parkingFloors;

        public ParkingLot() {
            this.parkingFloors = new ArrayList<>();
        }
    }

    public static class SmallestSpotFirstStrategy implements AssignmentService {

        private Map<SpotType, Integer> getSpotTypeCount(List<Spot> availableSpots) {
            Map<SpotType, Integer> map = new HashMap<>();
            for (Spot spot : availableSpots) {
                map.put(spot.type, map.getOrDefault(spot.type, 0) + 1);
            }
            return map;
        }

        @Override
        public Ticket assignTicket(Vehicle vehicle, List<Spot> availableSpots) {
            VehicleType vehicleType = vehicle.type;
            Map<SpotType, Integer> spotCount = getSpotTypeCount(availableSpots);
            SpotType preferredSpotType = getCompatibleSpotType(vehicleType, spotCount);
            for (Spot spot : availableSpots) {
                if (spot.type == preferredSpotType) {
                    spot.isAvailable = false;
                    return new Ticket(UUID.randomUUID().toString(), vehicle, spot, System.currentTimeMillis());
                }
            }
            return null;
        }

        private SpotType getCompatibleSpotType(VehicleType vehicleType, Map<SpotType, Integer> spotCount) {
            List<SpotType> allowedSpots = switch (vehicleType) {
                case BIKE -> List.of(SpotType.SMALL, SpotType.MEDIUM, SpotType.LARGE);
                case CAR -> List.of(SpotType.MEDIUM, SpotType.LARGE);
                case TRUCK -> List.of(SpotType.LARGE);
            };

            for (SpotType type : allowedSpots) {
                if (spotCount.getOrDefault(type, 0) > 0) {
                    return type;
                }
            }
            return null;
        }
    }

    public static class ParkingService {
        private final ParkingLot parkingLot;
        private final AssignmentService assignService;
        private final Set<String> parkedVehicles = new HashSet<>();

        public ParkingService(AssignmentService assignService, ParkingLot parkingLot) {
            this.assignService = assignService;
            this.parkingLot = parkingLot;
        }

        public Ticket parkVehicle(Vehicle vehicle) {
            if (parkedVehicles.contains(vehicle.licensePlate)) {
                throw new RuntimeException("Vehicle with license plate number " + vehicle.licensePlate + " is already parked");
            }
            List<Spot> availableSpots = checkAvailability();
            Ticket assignedTicket = assignService.assignTicket(vehicle, availableSpots);
            if (assignedTicket == null) {
                throw new RuntimeException("Parking lot is full. No compatible spot available for " + vehicle.type);
            }
            parkedVehicles.add(assignedTicket.vehicle.licensePlate);
            return assignedTicket;
        }

        public void unParkVehicle(Ticket ticket) {
            ticket.spot.isAvailable = true;
            ticket.setExitTime(System.currentTimeMillis());
            parkedVehicles.remove(ticket.vehicle.licensePlate);
        }

        public List<Spot> checkAvailability() {
            List<Spot> availableSpots = new ArrayList<>();
            for (ParkingFloor floor : parkingLot.parkingFloors) {
                for (Spot spot : floor.spots) {
                    if (spot.isAvailable) {
                        availableSpots.add(spot);
                    }
                }
            }
            return availableSpots;
        }

        public Map<Integer, Map<SpotType, Integer>> checkAvailabilityPerFloor() {
            Map<Integer, Map<SpotType, Integer>> map = new HashMap<>();
            for (ParkingFloor floor : parkingLot.parkingFloors) {
                int fn = floor.floorNumber;
                
                // Initialize counts for all possible types to 0 for this floor
                Map<SpotType, Integer> floorCounts = new HashMap<>();
                for (SpotType type : SpotType.values()) {
                    floorCounts.put(type, 0);
                }
                
                for (Spot spot : floor.spots) {
                    if (spot.isAvailable) {
                        floorCounts.put(spot.type, floorCounts.get(spot.type) + 1);
                    }
                }
                map.put(fn, floorCounts);
            }
            return map;
        }
    }
}
