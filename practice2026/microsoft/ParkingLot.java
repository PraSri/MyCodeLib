package microsoft;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ParkingLot {

    private static ParkingLot instance;
    private String name;
    private List<Floor> floors;

    private ParkingLot() {
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) instance = new ParkingLot();
        return instance;
    }

    public synchronized Ticket parkVehicle(Vehicle vehicle) {
        for (Floor floor : floors) {
            Optional<ParkingSlot> slot = floor.findAvailableSlot(vehicle.getType());
            if (slot.isPresent()) {
                slot.get().park(vehicle);
                return new Ticket(vehicle, slot.get());
            }
        }
        throw new RuntimeException("No available slots");
    }

    public enum VehicleType {
        BIKE, CAR, TRUCK;
    }

    public interface PaymentStrategy {
        double calculateCost(long durationInHours);
    }

    public abstract class Vehicle {
        private String licensePlate;
        private VehicleType type;

        public Vehicle(String licensePlate, VehicleType type) {
            this.licensePlate = licensePlate;
            this.type = type;
        }

        public VehicleType getType() {
            return type;
        }
    }

    public class Car extends Vehicle {
        public Car(String licensePlate) {
            super(licensePlate, VehicleType.CAR);
        }
    }

    public class ParkingSlot {
        private final String id;
        private final VehicleType supportedType;
        private boolean isFree = true;
        private Vehicle parkedVehicle;

        public ParkingSlot(String id, VehicleType type) {
            this.id = id;
            this.supportedType = type;
        }

        public synchronized boolean park(Vehicle vehicle) {
            if (!isFree || vehicle.getType() != supportedType) return false;
            this.parkedVehicle = vehicle;
            this.isFree = false;
            return true;
        }

        public synchronized void unpark() {
            this.parkedVehicle = null;
            this.isFree = true;
        }

        public boolean isFree() {
            return isFree;
        }

        public VehicleType getSupportedType() {
            return supportedType;
        }
    }

    public class Floor {
        private final int floorNumber;
        private final List<ParkingSlot> slots;

        public Floor(int floorNumber, List<ParkingSlot> slots) {
            this.floorNumber = floorNumber;
            this.slots = slots;
        }

        public Optional<ParkingSlot> findAvailableSlot(VehicleType type) {
            return slots.stream()
                    .filter(s -> s.isFree() && s.getSupportedType() == type)
                    .findFirst();
        }
    }

    public class Ticket {
        private String ticketId;
        private long startTime;
        private Vehicle vehicle;
        private ParkingSlot slot;

        public Ticket(Vehicle vehicle, ParkingSlot slot) {
            this.ticketId = UUID.randomUUID().toString();
            this.startTime = System.currentTimeMillis();
            this.vehicle = vehicle;
            this.slot = slot;
        }
        // Getters...
    }

    public class HourlyPaymentStrategy implements PaymentStrategy {
        @Override
        public double calculateCost(long hours) {
            return hours * 20.0; // Flat 20 per hour
        }
    }


}