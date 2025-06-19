package Rippling.delivery.v2;

/**
 * The Delivery class represents a single delivery with an epoch timestamp.
 */
public class Delivery {
    private final long epochTimestamp;

    // other attributes can be, start time, end time, location{source, destination}, status = [done, pending, on the way]

    /**
     * Constructs a Delivery using the provided timestamp (in epoch seconds).
     *
     * @param epochTimestamp the time the delivery was made (epoch seconds)
     */
    public Delivery(long epochTimestamp) {
        this.epochTimestamp = epochTimestamp;
    }

    /**
     * Returns the delivery timestamp.
     *
     * @return the epoch timestamp of the delivery
     */
    public long getEpochTimestamp() {
        return epochTimestamp;
    }
}
