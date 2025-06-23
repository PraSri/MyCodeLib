package Rippling.delivery.v4;

import java.time.Duration;
import java.time.LocalDateTime;

public class Delivery implements Comparable<Delivery> {
    final LocalDateTime start;
    final LocalDateTime end;
    final double distanceKm;

    public Delivery(LocalDateTime start, LocalDateTime end, double distanceKm) {
        this.start = start;
        this.end = end;
        this.distanceKm = distanceKm;
    }

    public Delivery(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
        this.distanceKm = 0;
    }

    Duration getDuration() {
        return Duration.between(start, end);
    }

    @Override
    public int compareTo(Delivery o) {
        int cmp = this.start.compareTo(o.start);
        return cmp != 0 ? cmp : this.end.compareTo(o.end);
    }
}
