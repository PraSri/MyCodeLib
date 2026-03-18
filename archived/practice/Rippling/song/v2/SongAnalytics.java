package Rippling.song.v2;

import java.util.*;

public class SongAnalytics {
    private int nextId = 1;
    private Map<Integer, Song> songsById;
    private List<Integer> recentlyPlayed;

    public SongAnalytics() {
        songsById = new HashMap<>();
        recentlyPlayed = new ArrayList<>();
    }

    public int add_song(String name) {
        int id = nextId++;
        Song song = new Song(id, name);
        songsById.put(id, song);
        return id;
    }

    public void play_song(int songId, int userId) {
        if (!songsById.containsKey(songId)) {
            System.out.println("Error: Song ID " + songId + " does not exist.");
            return;
        }
        Song song = songsById.get(songId);
        song.play(userId);

        int index = recentlyPlayed.indexOf(songId);
        if (index != -1) {
            recentlyPlayed.remove(index);
        }
        recentlyPlayed.add(0, songId);
        if (recentlyPlayed.size() > 3) {
            recentlyPlayed.remove(recentlyPlayed.size() - 1);
        }
    }

    public void print_analytics() {
        List<Song> songsList = new ArrayList<>(songsById.values());
        songsList.sort((s1, s2) -> {
            int countComparison = Integer.compare(s2.getUniqueListeners(), s1.getUniqueListeners());
            if (countComparison != 0) {
                return countComparison;
            }
            return s1.name.compareTo(s2.name);
        });

        for (Song song : songsList) {
            System.out.println(song.name + " (" + song.getUniqueListeners() + " unique listeners)");
        }
    }

    public void print_most_played_song() {
        if (songsById.isEmpty()) {
            System.out.println("No songs available");
            return;
        }

        Song bestSong = null;
        for (Song song : songsById.values()) {
            if (bestSong == null) {
                bestSong = song;
            } else {
                if (song.getUniqueListeners() > bestSong.getUniqueListeners()) {
                    bestSong = song;
                } else if (song.getUniqueListeners() == bestSong.getUniqueListeners()) {
                    if (song.name.compareTo(bestSong.name) < 0) {
                        bestSong = song;
                    }
                }
            }
        }
        System.out.println(bestSong.name + " (" + bestSong.getUniqueListeners() + " unique listeners)");
    }

    public void print_recently_played_songs() {
        if (recentlyPlayed.isEmpty()) {
            System.out.println("No songs played yet");
            return;
        }

        for (int songId : recentlyPlayed) {
            Song song = songsById.get(songId);
            System.out.println(song.name);
        }
    }
}
