package Rippling.delivery.v4;

import java.time.LocalDateTime;

public class Assignment {
    final Driver driver;
    final Delivery delivery;

    private LocalDateTime createdAt;

    public Driver getDriver() {
        return driver;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    Assignment(Driver d, Delivery del) {
        this.driver = d;
        this.delivery = del;
    }
}
