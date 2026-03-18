package Rippling.song.v2;

public class MusicPlayer {
    public static void main(String[] args) {
        // Test Case 1: Basic functionality (from problem example)
        System.out.println("Test Case 1:");
        SongAnalytics analytics1 = new SongAnalytics();
        int id1 = analytics1.add_song("Song A");
        int id2 = analytics1.add_song("Song B");
        int id3 = analytics1.add_song("Song C");
        System.out.println("Added songs: IDs=" + id1 + "," + id2 + "," + id3);

        analytics1.play_song(1, 1);
        analytics1.play_song(1, 2);
        analytics1.play_song(2, 1);
        analytics1.play_song(3, 3);
        analytics1.play_song(3, 3); // Duplicate play

        System.out.println("\nAnalytics:");
        analytics1.print_analytics();

        System.out.println("\nMost played song:");
        analytics1.print_most_played_song();

        System.out.println("\nRecently played songs:");
        analytics1.print_recently_played_songs();

        // Test Case 2: Error handling and tie-breaking
        System.out.println("\n\nTest Case 2:");
        SongAnalytics analytics2 = new SongAnalytics();
        analytics2.play_song(99, 1); // Invalid song

        int id4 = analytics2.add_song("Popular Song");
        int id5 = analytics2.add_song("Another Hit");
        int id6 = analytics2.add_song("Tie Song 1");
        int id7 = analytics2.add_song("Tie Song 2");

        // Multiple plays from different users
        analytics2.play_song(id4, 1);
        analytics2.play_song(id4, 2);
        analytics2.play_song(id4, 3);

        analytics2.play_song(id5, 1);
        analytics2.play_song(id5, 2);

        analytics2.play_song(id6, 5);
        analytics2.play_song(id7, 5); // Both have 1 unique listener

        System.out.println("\nAnalytics (sorted by listeners then name):");
        analytics2.print_analytics();

        System.out.println("\nMost played song:");
        analytics2.print_most_played_song();

        // Test recent plays with distinct songs
        analytics2.play_song(id7, 6); // Now id7 is most recent
        analytics2.play_song(id6, 7);
        analytics2.play_song(id5, 8);
        analytics2.play_song(id4, 9); // Should push out older plays

        System.out.println("\nRecently played songs after new plays:");
        analytics2.print_recently_played_songs();

        // Test Case 3: Edge cases (empty system)
        System.out.println("\n\nTest Case 3:");
        SongAnalytics analytics3 = new SongAnalytics();
        System.out.println("Analytics (empty):");
        analytics3.print_analytics();

        System.out.println("Most played (empty):");
        analytics3.print_most_played_song();

        System.out.println("Recently played (empty):");
        analytics3.print_recently_played_songs();
    }
}
