import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Low-Level Design: Meeting Scheduler (Microsoft LLD)
 *
 * Requirements:
 * 1. Rooms have capacity and distinct IDs.
 * 2. Book meetings within time intervals [start, end).
 * 3. Thread-safe conflict detection to prevent double-booking.
 * 4. Find available rooms for a given interval and capacity.
 */
public class Solution {

    public static class Interval {
        public final long start;
        public final long end;

        public Interval(long start, long end) {
            if (start >= end) throw new IllegalArgumentException("Start must be strictly before end");
            this.start = start;
            this.end = end;
        }

        public boolean overlaps(Interval other) {
            return this.start < other.end && other.start < this.end;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }

    public static class Meeting {
        public final String id;
        public final String title;
        public final Interval interval;
        public final int roomId;
        public final List<String> attendees;

        public Meeting(String id, String title, Interval interval, int roomId, List<String> attendees) {
            this.id = id;
            this.title = title;
            this.interval = interval;
            this.roomId = roomId;
            this.attendees = Collections.unmodifiableList(new ArrayList<>(attendees));
        }
    }

    public static class Room {
        public final int id;
        public final String name;
        public final int capacity;
        private final List<Meeting> meetings = new ArrayList<>();
        private final ReentrantLock roomLock = new ReentrantLock();

        public Room(int id, String name, int capacity) {
            this.id = id;
            this.name = name;
            this.capacity = capacity;
        }

        public boolean isAvailable(Interval interval) {
            roomLock.lock();
            try {
                for (Meeting m : meetings) {
                    if (m.interval.overlaps(interval)) return false;
                }
                return true;
            } finally {
                roomLock.unlock();
            }
        }

        public boolean addMeeting(Meeting meeting) {
            roomLock.lock();
            try {
                for (Meeting m : meetings) {
                    if (m.interval.overlaps(meeting.interval)) {
                        return false; // Double-booking detected
                    }
                }
                meetings.add(meeting);
                return true;
            } finally {
                roomLock.unlock();
            }
        }

        public List<Meeting> getMeetings() {
            roomLock.lock();
            try {
                return new ArrayList<>(meetings);
            } finally {
                roomLock.unlock();
            }
        }
    }

    public static class MeetingScheduler {
        private final Map<Integer, Room> rooms = new ConcurrentHashMap<>();

        public void addRoom(Room room) {
            rooms.put(room.id, room);
        }

        public List<Room> findAvailableRooms(Interval interval, int requiredCapacity) {
            List<Room> available = new ArrayList<>();
            for (Room room : rooms.values()) {
                if (room.capacity >= requiredCapacity && room.isAvailable(interval)) {
                    available.add(room);
                }
            }
            return available;
        }

        public Meeting bookMeeting(String title, Interval interval, int requiredCapacity, List<String> attendees) {
            List<Room> candidates = findAvailableRooms(interval, requiredCapacity);
            if (candidates.isEmpty()) return null;

            // Pick room with closest matching capacity to avoid wasting space
            candidates.sort(Comparator.comparingInt(r -> r.capacity));

            for (Room room : candidates) {
                String meetingId = UUID.randomUUID().toString();
                Meeting meeting = new Meeting(meetingId, title, interval, room.id, attendees);
                if (room.addMeeting(meeting)) {
                    return meeting;
                }
            }
            return null; // All candidate attempts encountered concurrent collision
        }
    }

    public static void main(String[] args) {
        MeetingScheduler scheduler = new MeetingScheduler();
        scheduler.addRoom(new Room(1, "Boardroom", 20));
        scheduler.addRoom(new Room(2, "Huddle A", 4));

        Interval slot1 = new Interval(9, 10);
        Meeting m1 = scheduler.bookMeeting("Sprint Planning", slot1, 4, Arrays.asList("Alice", "Bob"));
        System.out.println("Booked room: " + (m1 != null ? m1.roomId : "Failed"));

        // Attempt concurrent overlap on the same room
        Meeting m2 = scheduler.bookMeeting("Design Review", slot1, 4, Arrays.asList("Charlie", "Dave"));
        System.out.println("Booked next room: " + (m2 != null ? m2.roomId : "Failed"));
    }
}
