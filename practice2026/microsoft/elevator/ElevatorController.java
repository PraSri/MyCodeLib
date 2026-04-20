package microsoft.elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    private List<Elevator> elevators;

    public ElevatorController() {
        elevators = new ArrayList<>();
        elevators.add(new Elevator());
        elevators.add(new Elevator());
        elevators.add(new Elevator());
    }

    public boolean requestElevator(int floor, RequestType type) {
        if (floor < 0 || floor > 9) {
            return false;
        }
        if (type == RequestType.DESTINATION) {
            return false;
        }

        Request request = new Request(floor, type);
        Elevator best = selectBestElevator(request);
        if (best == null) {
            return false;
        }
        return best.addRequest(request);
    }

    public void step() {
        for (Elevator elevator : elevators) {
            elevator.step();
        }
    }

    private Elevator selectBestElevator(Request request) {
        Elevator best = findCommittedToFloor(request);
        if (best != null) {
            return best;
        }

        best = findNearestIdle(request.getFloor());
        if (best != null) {
            return best;
        }

        return findNearest(request.getFloor());
    }

    private Elevator findCommittedToFloor(Request request) {
        int floor = request.getFloor();
        Direction direction = (request.getType() == RequestType.PICKUP_UP) ? Direction.UP : Direction.DOWN;

        Elevator nearest = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            if (e.getDirection() != direction) {
                continue;
            }

            if ((direction == Direction.UP && e.getCurrentFloor() > floor) ||
                    (direction == Direction.DOWN && e.getCurrentFloor() < floor)) {
                continue;
            }

            if (!e.hasRequestsAtOrBeyond(floor, direction)) {
                continue;
            }

            int distance = Math.abs(e.getCurrentFloor() - floor);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = e;
            }
        }

        return nearest;
    }

    private Elevator findNearestIdle(int floor) {
        Elevator nearest = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            if (e.getDirection() != Direction.IDLE) {
                continue;
            }

            int distance = Math.abs(e.getCurrentFloor() - floor);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = e;
            }
        }

        return nearest;
    }

    private Elevator findNearest(int floor) {
        Elevator nearest = elevators.get(0);
        int minDistance = Math.abs(elevators.get(0).getCurrentFloor() - floor);

        for (Elevator e : elevators) {
            int distance = Math.abs(e.getCurrentFloor() - floor);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = e;
            }
        }

        return nearest;
    }
}
