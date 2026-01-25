package intervals;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRooms {

    //Given an array of meeting time interval objects consisting of start and end times
    // [[start_1,end_1],[start_2,end_2],...] (start_i < end_i),
    // determine if a person could add all meetings to their schedule without any conflicts.

    //Note: (0,8),(8,10) is not considered a conflict at 8

    // Tumhe meetings merge nahi karni, sirf conflict detect karna hai.

    //Intervals ko start time ke basis pe sort karo
    // fir dekho ki piche wala agr phele khatam ho chuka toh theek
    //nahi toh conflict hai

    //visualize kro calendar
    // last meeting the end time kam hona chahiye aane wali meeting ke start time se

    // summary - tum muhje ye batao ki pichli meeting meri current meeting se conflict kregi ki nhi
    // accha btata hu, ruku
    // main tumhari pichli meeting the end time
    // aur current meeting ka start time dekhta hu
    // arey ye pichli meeting ka end time toh jada hai tumhari current meeting, tum kaise kroge
    // return false;
    // aise saari meetings ka dekho
    // last me true return kro

    public boolean canAttendMeetings(List<Interval> intervals) {

        intervals.sort(Comparator.comparingInt(a -> a.start));

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i - 1).end > intervals.get(i).start) {
                return false;
            }
        }

        return true;
    }

    //   Definition of Interval:
    public static class Interval {
        public int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    //Meeting Rooms I sirf overlap detect karta hai,
    //Meeting Rooms II overlap ka maximum count nikalta hai.

    static class MeetingRoomsII {

        //Given an array of meeting time interval objects consisting of start and end times
        // [[start_1,end_1],[start_2,end_2],...] (start_i < end_i),
        // find the minimum number of days required to schedule all meetings without any conflicts.

        //Tumhe meetings ke intervals diye hain.
        // Tumhe batana hai minimum kitne rooms (ya days) chahiye taaki koi conflict na ho.

        // Ek room me overlapping meetings allowed nahi
        // Agar 2 meetings same time pe chal rahi hain ? alag room chahiye

        //Intervals = [(0,40), (5,10), (15,20)]

        // 0----5----10----15----20----40
        //|--------- meeting A --------|
        //     |--B--|
        //             |--C--|

        // Rooms required = kisi bhi moment pe maximum overlapping meetings

        // story - bhai, maine heap me meetings daali, theek hai koi baat nhi
        // phir maine socha, time kyu waste krna, jaise hi pichli meeting khatam uss room ko lelo
        // theek hai abhi tak socha hai
        // abb maine socha ki kyu na main heap se meetings nikal do
        // jo meetings khatam ho gyi hai
        // iss liye, min heap ke peak pe end time hoga, aur jo meeting abhi hai meri uska start agr baad me hai
        // matlab main uss meeting ko nikal skta hu
        // thik hai, nikaal diya
        // abb last me jo bach gye, wahi required rooms hai

        public int minMeetingRooms(List<Interval> intervals) {
            // phele to tum sort kroo timings on start time
            intervals.sort(Comparator.comparingInt(a -> a.start));

            //MinHeap me hum har room ka end time track karte hain.
            //Heap ka size = kitne rooms currently occupied hain.
            //Top pe hamesha sabse jaldi khatam hone wali meeting
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            for (Interval interval : intervals) {
                //Case 1: Agar koi room free ho chuka hai
                //Jo meeting sabse pehle khatam hui (peek)
                // Agar uska end chota hai current meeting ka start
                //wahi room reuse kar sakte hain
                if (!minHeap.isEmpty() && interval.start >= minHeap.peek()) {
                    //heap se remove kar diya (room free)
                    minHeap.poll();
                }
                // Case 2: Current meeting ko assign karo
                //Is meeting ke liye room chahiye
                //Us room ka end time heap me daal diya
                minHeap.offer(interval.end);
            }
            //Heap me jitne end times hain
            //utne rooms simultaneously occupied
            //wahi minimum rooms required
            // matlab wo meetings simultaneously chal rhi hai
            //toh hume different rooms dene hi hoge
            return minHeap.size();
        }
    }

}
