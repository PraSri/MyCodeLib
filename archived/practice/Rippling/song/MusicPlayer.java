package Rippling.song;

public class MusicPlayer {
    public static void main(String[] args) {
        SongAnalytics analytics = new SongAnalytics();

        // Adding songs to the system
        int id1 = analytics.add_song("Song A");
        int id2 = analytics.add_song("Song B");
        int id3 = analytics.add_song("Song C");

        // Recording play events (user IDs are provided)
        analytics.play_song(id1, 1); // User 1 plays Song A
        analytics.play_song(id1, 2); // User 2 plays Song A
        analytics.play_song(id2, 1); // User 1 plays Song B
        analytics.play_song(id3, 3); // User 3 plays Song C
        analytics.play_song(id3, 3); // Duplicate play by user 3 (won't increase unique count)

        // Display analytics
        analytics.print_analytics();

        analytics.play_song(id1, 1);
        analytics.play_song(id2, 1);
        analytics.play_song(id3, 1);
        analytics.play_song(id1, 1); // replaying Song A again

        analytics.printRecentPlaysPerUser(1);
// Output: Top 3 recent songs for user 1: Song A, Song C, Song B
    }
}
