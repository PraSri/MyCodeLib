package Rippling.delivery.ps;


import java.time.LocalDateTime;

public class Delivery implements Comparable<Delivery> {

    public LocalDateTime startTime;
    public LocalDateTime endTime;
    public Status deliveryStatus;
    public Double cost;

    @Override
    public int compareTo(Delivery delivery) {
        int cmp = this.startTime.compareTo(delivery.startTime);
        return cmp != 0 ? cmp : this.endTime.compareTo(delivery.endTime);
    }

    public Delivery(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Status getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Status deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
