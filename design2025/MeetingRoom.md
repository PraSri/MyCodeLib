Below is a clean, interview-ready Low Level Design (LLD + Concurrency + Classes + APIs) for the Meeting Room Reservation Platform (Salesforce asked).
I’ll give you:
? Final LLD
? Class Diagrams
? Method Flow
? Concurrency-safe approach
? Optional extensions (recurring meetings, DB schema, caching)

? 1. Core Requirements Summary


View available rooms for a time interval


Book room if free


Cancel booking


List bookings for room / employee


Prevent overlapping bookings


Handle concurrency: two users booking same room at the same time


Extensible to many rooms and future features



? 2. Core Entities / Classes
2.1 TimeSlot (immutable)
public class TimeSlot {
private final LocalDateTime start;
private final LocalDateTime end;

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start) || end.equals(start)) {
            throw new IllegalArgumentException("Invalid time range");
        }
        this.start = start;
        this.end = end;
    }

    public boolean overlaps(TimeSlot other) {
        return !(this.end.isBefore(other.start) || this.start.isAfter(other.end));
    }
}


2.2 MeetingRoom
public class MeetingRoom {
private final String roomId;
private final String name;
private final int capacity;
private final String location;

    public MeetingRoom(String roomId, String name, int capacity, String location) {
        this.roomId = roomId;
        this.name = name;
        this.capacity = capacity;
        this.location = location;
    }
}


2.3 BookingStatus
public enum BookingStatus {
ACTIVE,
CANCELLED,
COMPLETED;
}


2.4 Booking
public class Booking {
private final String bookingId;
private final String employeeId;
private final String roomId;
private final TimeSlot timeSlot;
private BookingStatus status;
private final LocalDateTime createdAt;

    public Booking(String employeeId, String roomId, TimeSlot timeSlot) {
        this.bookingId = UUID.randomUUID().toString();
        this.employeeId = employeeId;
        this.roomId = roomId;
        this.timeSlot = timeSlot;
        this.status = BookingStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    public boolean overlaps(TimeSlot slot) {
        return status == BookingStatus.ACTIVE && timeSlot.overlaps(slot);
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
    }
}


? 3. Repository Layer (In-memory for LLD)
We maintain per-room bookings for efficient search.
public class BookingRepository {

    // roomId -> List of bookings
    private final Map<String, List<Booking>> roomBookings = new HashMap<>();

    public synchronized List<Booking> getBookingsForRoom(String roomId) {
        return roomBookings.getOrDefault(roomId, new ArrayList<>());
    }

    public synchronized void addBooking(Booking booking) {
        roomBookings.computeIfAbsent(booking.getRoomId(), k -> new ArrayList<>())
                    .add(booking);
    }
}


? 4. Concurrency Control
To avoid double booking:
? Use a lock per room
class RoomLockProvider {
private final Map<String, ReentrantLock> roomLocks = new ConcurrentHashMap<>();

    public ReentrantLock getLock(String roomId) {
        return roomLocks.computeIfAbsent(roomId, id -> new ReentrantLock());
    }
}


? 5. BookingService (Business Logic)
public class BookingService {

    private final BookingRepository bookingRepo;
    private final RoomLockProvider roomLockProvider;

    public BookingService(BookingRepository repo, RoomLockProvider lockProvider) {
        this.bookingRepo = repo;
        this.roomLockProvider = lockProvider;
    }

    public Booking bookRoom(String employeeId, String roomId, TimeSlot slot) {
        ReentrantLock lock = roomLockProvider.getLock(roomId);
        lock.lock();

        try {
            // Check for overlap
            List<Booking> existing = bookingRepo.getBookingsForRoom(roomId);
            for (Booking b : existing) {
                if (b.overlaps(slot)) {
                    throw new RuntimeException("Room already booked for this timeslot");
                }
            }

            // Create booking
            Booking booking = new Booking(employeeId, roomId, slot);
            bookingRepo.addBooking(booking);
            return booking;

        } finally {
            lock.unlock();
        }
    }

    public void cancelBooking(String bookingId, String roomId) {
        List<Booking> bookings = bookingRepo.getBookingsForRoom(roomId);
        for (Booking b : bookings) {
            if (b.getBookingId().equals(bookingId)) {
                b.cancel();
                return;
            }
        }
        throw new RuntimeException("Booking not found");
    }

    public List<MeetingRoom> getAvailableRooms(List<MeetingRoom> allRooms, TimeSlot slot) {
        List<MeetingRoom> available = new ArrayList<>();

        for (MeetingRoom room : allRooms) {
            boolean free = true;

            for (Booking booking : bookingRepo.getBookingsForRoom(room.getRoomId())) {
                if (booking.overlaps(slot)) {
                    free = false;
                    break;
                }
            }

            if (free) available.add(room);
        }

        return available;
    }
}


? 6. How Overlap is Checked
Two intervals overlap if:
NOT (end1 <= start2 OR start1 >= end2)

Your implementation already follows this.

? 7. Example Scenario
Let’s simulate:
Room A: booked 10–11
User X tries 10:30–11:30 ? FAIL
User Y tries Room B 10:30–11:30 ? SUCCESS

BookingService code handles this through:


lock on room


overlap check



? 8. API Layer (Controller style)
Just for completeness:
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService service) {
        this.bookingService = service;
    }

    public Booking bookRoom(String empId, String roomId,
                            LocalDateTime start, LocalDateTime end) {

        TimeSlot slot = new TimeSlot(start, end);
        return bookingService.bookRoom(empId, roomId, slot);
    }
}


? 9. DB Schema (If using SQL)
rooms table
room_id (PK)
name
capacity
location

bookings table
booking_id (PK)
employee_id
room_id (FK)
start_time
end_time
status
created_at

Indexes:
INDEX room_time_idx (room_id, start_time, end_time)


? 10. Optional Enhancements
Recurring Meetings


store recurrence rule (RRULE)


generate event instances or compute on query


collision detection identical to normal booking


Auto-expire bookings


A scheduler marks past meetings as COMPLETED


Search by capacity / location


Extend MeetingRoomService



? 11. Ready-to-say Interview Summary
If you need a final "2-minute verbal summary" for the interviewer, I can write that too.

If you want, I can also prepare:
? Class Diagram (UML)
? Sequence Diagram (booking flow)
? Thread-safe design explanation
? How to scale to thousands of rooms
Just tell me “give diagrams” or “explain scaling”.