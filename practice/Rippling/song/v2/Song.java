package Rippling.song.v2;

import java.util.*;

class Song {
    int id;
    String name;
    Set<Integer> listeners;

    public Song(int id, String name) {
        this.id = id;
        this.name = name;
        this.listeners = new HashSet<>();
    }

    public void play(int userId) {
        listeners.add(userId);
    }

    public int getUniqueListeners() {
        return listeners.size();
    }
}
