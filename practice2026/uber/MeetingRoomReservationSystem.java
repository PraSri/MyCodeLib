package uber;

import java.util.*;

/**
 * Represents a meeting with a unique ID, start time, end time, and the room it belongs to.
 */
class Meeting {
    private final String meetingId;
    private final int start;   // start time in minutes from midnight (0-1439)
    private final int end;     // end time in minutes from midnight
    private final String roomId;

    public Meeting(String meetingId, int start, int end, String roomId) {
        if (start < 0 || start >= 1440 || end <= start || end > 1440) {
            throw new IllegalArgumentException("Invalid meeting time");
        }
        this.meetingId = meetingId;
        this.start = start;
        this.end = end;
        this.roomId = roomId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getRoomId() {
        return roomId;
    }

    /**
     * Checks if this meeting overlaps with another meeting.
     * Overlap occurs if the intervals [start, end) intersect.
     */
    public boolean overlaps(Meeting other) {
        return (this.start < other.getEnd() && other.getStart() < this.end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return meetingId.equals(meeting.meetingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingId);
    }
}

/**
 * Represents a conference room that holds a list of meetings.
 */
class Room {
    private final String roomId;
    private final List<Meeting> meetings;  // simple list, O(n) checks

    public Room(String roomId) {
        this.roomId = roomId;
        this.meetings = new ArrayList<>();
    }

    public String getRoomId() {
        return roomId;
    }

    /**
     * Checks if the room is available for a new meeting at the given time slot.
     */
    public boolean isAvailable(int start, int end) {
        Meeting dummy = new Meeting("dummy", start, end, roomId);
        for (Meeting m : meetings) {
            if (m.overlaps(dummy)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a meeting to the room (assumes availability has been checked).
     */
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    /**
     * Removes a meeting from the room.
     *
     * @return true if the meeting was found and removed
     */
    public boolean removeMeeting(Meeting meeting) {
        return meetings.remove(meeting);
    }

    /**
     * Returns an unmodifiable view of the meetings (for testing/debugging).
     */
    public List<Meeting> getMeetings() {
        return Collections.unmodifiableList(meetings);
    }
}

/**
 * Main reservation system that manages rooms and meetings.
 * Rooms are fixed at creation time.
 */
public class MeetingRoomReservationSystem {
    private final Map<String, Room> rooms;            // roomId -> Room
    private final Map<String, Meeting> meetingsById;  // meetingId -> Meeting

    /**
     * Constructs the system with a fixed list of room identifiers.
     *
     * @param roomIds list of room IDs (e.g., ["A101", "A102"])
     */
    public MeetingRoomReservationSystem(List<String> roomIds) {
        rooms = new HashMap<>();
        meetingsById = new HashMap<>();
        for (String id : roomIds) {
            rooms.put(id, new Room(id));
        }
    }

    // ------------------------ Example usage ------------------------
    public static void main(String[] args) {
        // 1. Create system with fixed rooms
        List<String> roomIds = Arrays.asList("Conference A", "Conference B", "Board Room");
        MeetingRoomReservationSystem system = new MeetingRoomReservationSystem(roomIds);

        // 2. Book some meetings
        boolean success;

        success = system.bookMeeting("Conference A", "M1", 9 * 60, 10 * 60); // 9:00-10:00
        System.out.println("Book M1 in Conference A: " + success); // true

        success = system.bookMeeting("Conference A", "M2", 10 * 60, 11 * 60); // 10:00-11:00
        System.out.println("Book M2 in Conference A: " + success); // true (adjacent, no overlap)

        success = system.bookMeeting("Conference A", "M3", 9 * 60 + 30, 10 * 60 + 30); // 9:30-10:30
        System.out.println("Book M3 in Conference A: " + success); // false (overlaps M1 and M2)

        success = system.bookMeeting("Conference B", "M4", 14 * 60, 15 * 60); // 14:00-15:00
        System.out.println("Book M4 in Conference B: " + success); // true

        // 3. Cancel a meeting
        boolean cancelled = system.cancelMeeting("M1");
        System.out.println("Cancel M1: " + cancelled); // true

        // Now M3 can be booked in the freed slot
        success = system.bookMeeting("Conference A", "M3", 9 * 60 + 30, 10 * 60 + 30);
        System.out.println("Book M3 after cancellation: " + success); // true

        // 4. Show final state of Conference A
        System.out.println("\nMeetings in Conference A:");
        for (Meeting m : system.getMeetingsForRoom("Conference A")) {
            System.out.printf("  %s: %02d:%02d - %02d:%02d%n",
                    m.getMeetingId(),
                    m.getStart() / 60, m.getStart() % 60,
                    m.getEnd() / 60, m.getEnd() % 60);
        }
    }

    /**
     * Books a meeting in a specific room.
     *
     * @param roomId    the room identifier
     * @param meetingId unique identifier for the meeting (client provided)
     * @param start     start time in minutes from midnight (0-1439)
     * @param end       end time in minutes from midnight (1-1440, end > start)
     * @return true if booking succeeded, false if room not found, time invalid, meetingId already exists, or time slot overlaps
     */
    public boolean bookMeeting(String roomId, String meetingId, int start, int end) {
        // Validate meetingId uniqueness
        if (meetingsById.containsKey(meetingId)) {
            return false;
        }

        Room room = rooms.get(roomId);
        if (room == null) {
            return false;
        }

        // Basic time validation (could be extended)
        if (start < 0 || start >= 1440 || end <= start || end > 1440) {
            return false;
        }

        // Check availability
        if (!room.isAvailable(start, end)) {
            return false;
        }

        // Create and add meeting
        Meeting meeting = new Meeting(meetingId, start, end, roomId);
        room.addMeeting(meeting);
        meetingsById.put(meetingId, meeting);
        return true;
    }

    /**
     * Cancels a meeting by its unique ID.
     *
     * @param meetingId the ID of the meeting to cancel
     * @return true if the meeting existed and was cancelled, false otherwise
     */
    public boolean cancelMeeting(String meetingId) {
        Meeting meeting = meetingsById.remove(meetingId);
        if (meeting == null) {
            return false;
        }
        Room room = rooms.get(meeting.getRoomId());
        if (room != null) {
            room.removeMeeting(meeting);
        }
        return true;
    }

    /**
     * (Optional) Utility method to list all meetings in a room (for debugging).
     */
    public List<Meeting> getMeetingsForRoom(String roomId) {
        Room room = rooms.get(roomId);
        if (room == null) return Collections.emptyList();
        return room.getMeetings();
    }
}