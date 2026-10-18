import java.util.UUID;

public class parkingLotLLD {

    enum SpotType {
        SMALL, MEDIUM, LARGE
    }

    enum VehicleType {
        CAR, BIKE, TRUCK
    }

    class Spot {
        int id;
        SpotType type;
        boolean isAvailable;
    }

    class Vehicle {
        String licensePlate;
        String color;
        VehicleType type;
    }

    class Ticket {
        String ticketId;
        Vehicle vehicle;
        Spot spot;
        long entryTime;
        long exitTime;
        double amount;
    }

    class ParkingFloor {
        int floorNumber;
        List<Spot> spots;
        int availableSpots;
    }

    class ParkingLot {
        List<ParkingFloor> parkingFloors;

    }

    public interface AssignmentService {
        Ticket assignTicket(Vehicle vehicle);

    }

    public class SmallestSpotFirstStrategy implements AssignmentService {
        @Override
        public Ticket assignTicket(Vehicle vehicle, List<Spot> availableSpots) {
            VehicleType vehicleType = vehicle.type;
            SpotType preferredSpotType = getPreferredSpotType(vehicleType);
            for (Spot spot : availableSpots) {
                if (spot.type == preferredSpotType) {
                    spot.isAvailable = false;
                    return new Ticket(UUID.randomUUID().toString(), vehicle, spot, System.currentTimeMillis());
                }
            }
            return null;
        }

        private SpotType getPreferredSpotType(VehicleType vehicleType) {
            if (vehicleType == VehicleType.BIKE) {
                return SpotType.SMALL;
            } else if (vehicleType == VehicleType.CAR) {
                return SpotType.MEDIUM;
            } else if (vehicleType == VehicleType.TRUCK) {
                return SpotType.LARGE;
            } else {
                return SpotType.SMALL;
            }
        }
    }

    public class ParkingService {
        private ParkingLot parkingLot;
        private AssignmentService assignService;

        public ParkingService(AssignmentService assignService, ParkingLot parkingLot) {
            this.assignService = assignService;
            this.parkingLot = parkingLot;
        }

        public Ticket parkVehicle(Vehicle vehicle) {
            List<Spot> availableSpots = checkAvailability();
            return assignService.assignTicket(vehicle, availableSpots);
        }

        public void unParkVehicle(Ticket ticket) {
            ticket.spot.isAvailable = true;
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

    }

    public static void main(String[] args) {

        ParkingLot parkingLot = new ParkingLot();
        SmallestSpotFirstStrategy strategy = new SmallestSpotFirstStrategy();
        ParkingService parkingService = new ParkingService(strategy, parkingLot);

        Vehicle vehicle = new Vehicle("MH12AB1234", "Red", VehicleType.CAR);
        Ticket ticket = parkingService.parkVehicle(vehicle);
        System.out.println("Ticket: " + ticket.ticketId);

    }

}
