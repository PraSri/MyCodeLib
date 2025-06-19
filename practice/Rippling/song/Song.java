package Rippling.song;

import java.util.*;

public class Song {
    private int id;
    private String name;
    private Set<Integer> uniqueListeners;
    private long lastPlayedOrder; // a counter-based timestamp for recency

    public Song(int id, String name) {
        this.id = id;
        this.name = name;
        this.uniqueListeners = new HashSet<>();
        this.lastPlayedOrder = 0; // 0 indicates it hasn't yet been played
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUniqueListenerCount() {
        return uniqueListeners.size();
    }

    public void addListener(int userId) {
        uniqueListeners.add(userId);
    }

    public long getLastPlayedOrder() {
        return lastPlayedOrder;
    }

    public void updateLastPlayedOrder(long order) {
        this.lastPlayedOrder = order;
    }
}
