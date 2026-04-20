package microsoft;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MeetingSchduler {

    class User {
        String name;
    }

    class Interval {
        int start, end;

        public Interval(int start, int end) {
            if (start >= end) throw new IllegalArgumentException("Start must be before end.");
            this.start = start;
            this.end = end;
        }

        // Standard overlap logic: (StartA < EndB) and (EndA > StartB)
        public boolean overlaps(Interval other) {
            return this.start < other.end && other.start < this.end;
        }
    }

    class Meeting {
        private int id;
        private Interval interval;
        private MeetingRoom room;
        private List<User> participants;

        public Meeting(int id, Interval interval, MeetingRoom room, List<User> participants) {
            this.id = id;
            this.interval = interval;
            this.room = room;
            this.participants = participants;
        }

        public Interval getInterval() {
            return interval;
        }
    }

    class MeetingRoom {
        private String name;
        private List<Meeting> scheduledMeetings;

        public MeetingRoom(String name) {
            this.name = name;
            this.scheduledMeetings = new ArrayList<>();
        }

        public boolean isAvailable(Interval interval) {
            for (Meeting m : scheduledMeetings) {
                if (m.getInterval().overlaps(interval)) return false;
            }
            return true;
        }

        public void addMeeting(Meeting meeting) {
            scheduledMeetings.add(meeting);
        }

        public String getName() {
            return name;
        }
    }

    class Scheduler {
        private List<MeetingRoom> rooms;

        public Scheduler(List<MeetingRoom> rooms) {
            this.rooms = rooms;
        }

        public Meeting scheduleMeeting(List<User> users, int start, int end) {
            Interval interval = new Interval(start, end);

            for (MeetingRoom room : rooms) {
                if (room.isAvailable(interval)) {
                    Meeting newMeeting = new Meeting(new Random().nextInt(1000), interval, room, users);
                    room.addMeeting(newMeeting);
                    System.out.println("Meeting scheduled in room: " + room.getName());
                    return newMeeting;
                }
            }

            System.out.println("No rooms available for this time slot.");
            return null;
        }
    }
}
