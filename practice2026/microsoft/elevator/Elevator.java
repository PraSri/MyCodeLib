package microsoft.elevator;

import java.util.HashSet;
import java.util.Set;

enum Direction {
    UP,
    DOWN,
    IDLE
}

public class Elevator {
    private int currentFloor;
    private Direction direction;
    private Set<Request> requests;

    public Elevator() {
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.requests = new HashSet<>();
    }

    public boolean addRequest(Request request) {
        if (request.getFloor() < 0 || request.getFloor() > 9) {
            return false;
        }
        if (request.getFloor() == currentFloor) {
            return true;
        }
        if (requests.contains(request)) {
            return false;
        }
        return requests.add(request);
    }

    public void step() {
        if (requests.isEmpty()) {
            direction = Direction.IDLE;
            return;
        }

        if (direction == Direction.IDLE) {
            // Find nearest request to establish initial direction (deterministic)
            Request nearest = null;
            int minDistance = Integer.MAX_VALUE;

            for (Request req : requests) {
                int distance = Math.abs(req.getFloor() - currentFloor);
                if (distance < minDistance ||
                        (distance == minDistance && (nearest == null || req.getFloor() < nearest.getFloor()))) {
                    minDistance = distance;
                    nearest = req;
                }
            }

            direction = (nearest.getFloor() > currentFloor) ? Direction.UP : Direction.DOWN;
        }

        RequestType pickupType = (direction == Direction.UP) ? RequestType.PICKUP_UP : RequestType.PICKUP_DOWN;
        Request pickupRequest = new Request(currentFloor, pickupType);
        Request destinationRequest = new Request(currentFloor, RequestType.DESTINATION);

        if (requests.contains(pickupRequest) || requests.contains(destinationRequest)) {
            requests.remove(pickupRequest);
            requests.remove(destinationRequest);
            if (requests.isEmpty()) {
                direction = Direction.IDLE;
            }
            return;
        }

        if (!hasRequestsAhead(direction)) {
            direction = (direction == Direction.UP) ? Direction.DOWN : Direction.UP;
            return;
        }

        if (direction == Direction.UP) {
            currentFloor++;
        } else if (direction == Direction.DOWN) {
            currentFloor--;
        }
    }

    public boolean hasRequestsAhead(Direction dir) {
        for (Request request : requests) {
            if (dir == Direction.UP && request.getFloor() > currentFloor) {
                return true;
            }
            if (dir == Direction.DOWN && request.getFloor() < currentFloor) {
                return true;
            }
        }
        return false;
    }

    public boolean hasRequestsAtOrBeyond(int floor, Direction dir) {
        for (Request request : requests) {
            if (dir == Direction.UP && request.getFloor() >= floor) {
                if (request.getType() == RequestType.PICKUP_UP || request.getType() == RequestType.DESTINATION) {
                    return true;
                }
            }
            if (dir == Direction.DOWN && request.getFloor() <= floor) {
                if (request.getType() == RequestType.PICKUP_DOWN || request.getType() == RequestType.DESTINATION) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }
}
