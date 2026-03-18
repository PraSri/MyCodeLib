package Rippling;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Immutable model for one log entry.
 */
public class LogEntry {
    private final long userId;
    private final LocalDate date;
    private final LocalDateTime timestamp;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter TS_FMT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public LogEntry(long userId, LocalDate date, LocalDateTime timestamp) {
        this.userId = userId;
        this.date = date;
        this.timestamp = timestamp;
    }

    public long getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Parses a CSV line of form "userId,date,timestamp"
     * where date is yyyy-MM-dd and timestamp is yyyy-MM-ddTHH:mm:ss
     */
    public static Optional<LogEntry> parse(String csvLine) {
        if (csvLine == null || csvLine.isEmpty()) return Optional.empty();
        String[] parts = csvLine.split(",");
        if (parts.length != 3) return Optional.empty();
        try {
            long uid = Long.parseLong(parts[0].trim());
            LocalDate d = LocalDate.parse(parts[1].trim(), DATE_FMT);
            LocalDateTime ts = LocalDateTime.parse(parts[2].trim(), TS_FMT);
            return Optional.of(new LogEntry(uid, d, ts));
        } catch (Exception ex) {
            // malformed line
            return Optional.empty();
        }
    }


    /**
     * Utility class to count inversions in an array of long.
     */
    class InversionCounter {
        public static long count(long[] arr) {
            if (arr == null || arr.length < 2) return 0;
            long[] aux = new long[arr.length];
            return mergeSortCount(arr, aux, 0, arr.length - 1);
        }

        private static long mergeSortCount(long[] a, long[] aux, int lo, int hi) {
            if (lo >= hi) return 0;
            int mid = lo + (hi - lo) / 2;
            long leftInv = mergeSortCount(a, aux, lo, mid);
            long rightInv = mergeSortCount(a, aux, mid + 1, hi);
            long splitInv = mergeAndCount(a, aux, lo, mid, hi);
            return leftInv + rightInv + splitInv;
        }

        private static long mergeAndCount(long[] a, long[] aux, int lo, int mid, int hi) {
            System.arraycopy(a, lo, aux, lo, hi - lo + 1);
            int i = lo, j = mid + 1, k = lo;
            long invCount = 0;
            while (i <= mid && j <= hi) {
                // if left ? right, it's not an inversion
                if (aux[i] <= aux[j]) {
                    a[k++] = aux[i++];
                } else {
                    // aux[i] > aux[j] => all remaining in left [i..mid] form inversions
                    a[k++] = aux[j++];
                    invCount += (mid - i + 1);
                }
            }
            while (i <= mid) a[k++] = aux[i++];
            while (j <= hi) a[k++] = aux[j++];
            return invCount;
        }
    }

    /**
     * Orchestrates reading, filtering, sorting and inversion counting.
     */
    public static class LogProcessor {

        /**
         * Reads an append-only CSV file, filters entries by targetDate,
         * sorts by timestamp, and returns the inversion count on userIds.
         *
         * @param logFile    path to the CSV log
         * @param targetDate date for which to compute inversions
         * @return number of (i,j) pairs where ts[i]<ts[j] && uid[i]>uid[j]
         */
        public long countUserIdInversionsForDate(Path logFile, LocalDate targetDate) throws IOException {
            List<LogEntry> selected = new ArrayList<>();

            // 1) Stream file, parse & filter
            try (BufferedReader reader = Files.newBufferedReader(logFile)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    LogEntry.parse(line).filter(e -> e.getDate().equals(targetDate)).ifPresent(selected::add);
                }
            }

            if (selected.isEmpty()) {
                return 0;
            }

            // 2) Sort by timestamp ascending
            selected.sort(Comparator.comparing(LogEntry::getTimestamp));

            // 3) Extract userId array
            long[] userIds = selected.stream().mapToLong(LogEntry::getUserId).toArray();

            // 4) Count inversions in userId sequence
            return InversionCounter.count(userIds);
        }

        // Example usage
        public static void main(String[] args) throws Exception {
            if (args.length != 2) {
                System.err.println("Usage: java LogProcessor <logFilePath> <yyyy-MM-dd>");
                System.exit(1);
            }
            Path path = Path.of(args[0]);
            LocalDate date = LocalDate.parse(args[1], DateTimeFormatter.ISO_LOCAL_DATE);
            LogProcessor lp = new LogProcessor();
            long inversions = lp.countUserIdInversionsForDate(path, date);
            System.out.printf("Date %s ? %d inverted pairs%n", date, inversions);
        }
    }
}
