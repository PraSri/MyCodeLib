package microsoft.elevator;

import java.util.Objects;

public class Request {
    private final int floor;
    private final RequestType type;

    public Request(int floor, RequestType type) {
        this.floor = floor;
        this.type = type;
    }

    public int getFloor() {
        return floor;
    }

    public RequestType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return floor == request.floor && type == request.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(floor, type);
    }
}


