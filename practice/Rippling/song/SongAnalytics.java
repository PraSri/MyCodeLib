package Rippling.song;

import java.util.*;

class SongAnalytics {
    private final Map<Integer, Song> songs;
    private int nextSongId;
    private long playCounter; // acts as a timestamp counter for recency

    private Map<Integer, Deque<Integer>> userPlayHistory;

    public SongAnalytics() {
        songs = new HashMap<>();
        nextSongId = 1;
        playCounter = 0;
        userPlayHistory = new HashMap<>();
    }

    /**
     * Adds a new song to the system and returns a unique, auto-incrementing song ID.
     */
    public int add_song(String name) {
        Song song = new Song(nextSongId, name);
        songs.put(nextSongId, song);
        return nextSongId++;
    }

    /**
     * Records a play event for a song by a user.
     * If song_id does not exist, prints an error message.
     * Each user is counted only once per song.
     */
    public void play_song(int song_id, int user_id) {
        Song song = songs.get(song_id);
        if (song == null) {
            System.out.println("Error: Song ID " + song_id + " does not exist.");
            return;
        }
        // Using a Set ensures each user ID is counted only once per song.
        song.addListener(user_id);
        // Update play order to simulate the recency of the play event.
        song.updateLastPlayedOrder(++playCounter);

        userPlayHistory.putIfAbsent(user_id, new LinkedList<>());
        Deque<Integer> history = userPlayHistory.get(user_id);

        // Avoid duplicate consecutive plays
        if (history.contains(song_id)) {
            history.remove(song_id);
        }
        history.addFirst(song_id);

        // Limit to top 3 entries
        if (history.size() > 3) {
            history.removeLast();
        }
    }

    /**
     * Prints a summary of songs sorted primarily by the number of unique listeners in descending order,
     * and secondarily by their names in lexicographic ascending order if counts are equal.
     * Additionally, prints:
     * 1. The most played song by unique users.
     * 2. The 3 unique most recently played songs.
     */
    public void print_analytics() {
        // PART 1: Print songs sorted by unique listener count (desc) and name (asc)
        List<Song> songList = new ArrayList<>(songs.values());
        songList.sort((s1, s2) -> {
            int cmp = Integer.compare(s2.getUniqueListenerCount(), s1.getUniqueListenerCount());
            if (cmp == 0) {
                cmp = s1.getName().compareTo(s2.getName());
            }
            return cmp;
        });
        for (Song song : songList) {
            System.out.println(song.getName() + " (" + song.getUniqueListenerCount() + " unique listeners)");
        }

        // PART 2: Extra Requirement 1: Print the most played song by unique users.
        Song mostPlayedSong = null;
        for (Song song : songs.values()) {
            if (mostPlayedSong == null || song.getUniqueListenerCount() > mostPlayedSong.getUniqueListenerCount()) {
                mostPlayedSong = song;
            }
        }
        if (mostPlayedSong != null) {
            System.out.println("Most played song by unique users: " + mostPlayedSong.getName() + " (" + mostPlayedSong.getUniqueListenerCount() + " unique listeners)");
        } else {
            System.out.println("No play events recorded.");
        }

        // PART 3: Extra Requirement 2: Print 3 unique most recently played songs.
        List<Song> recentSongs = new ArrayList<>();
        for (Song song : songs.values()) {
            if (song.getLastPlayedOrder() > 0) { // only consider songs that have been played
                recentSongs.add(song);
            }
        }
        recentSongs.sort((s1, s2) -> Long.compare(s2.getLastPlayedOrder(), s1.getLastPlayedOrder()));

        System.out.print("3 unique most recently played songs: ");
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (Song song : recentSongs) {
            if (count < 3) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(song.getName());
                count++;
            } else {
                break;
            }
        }
        if (sb.length() > 0) {
            System.out.println(sb);
        } else {
            System.out.println("No songs have been played yet.");
        }
    }

    public void printRecentPlaysPerUser(int user_id) {
        Deque<Integer> history = userPlayHistory.get(user_id);
        if (history == null || history.isEmpty()) {
            System.out.println("No recent plays for user " + user_id);
            return;
        }

        System.out.print("Top 3 recent songs for user " + user_id + ": ");
        List<String> recentSongs = new ArrayList<>();
        for (int songId : history) {
            Song song = songs.get(songId);
            if (song != null) {
                recentSongs.add(song.getName());
            }
        }
        System.out.println(String.join(", ", recentSongs));
    }

}
