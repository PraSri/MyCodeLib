package Rippling.logs;

import java.util.*;

class LogEntry implements Comparable<LogEntry> {
    int userId;
    String date;
    long timestamp;

    public LogEntry(int userId, String date, long timestamp) {
        this.userId = userId;
        this.date = date;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(LogEntry other) {
        return Long.compare(this.timestamp, other.timestamp);
    }
}

class LogProcessor {
    private Map<String, TreeSet<LogEntry>> logData = new HashMap<>();

    public void addLogEntry(int userId, String date, long timestamp) {
        logData.putIfAbsent(date, new TreeSet<>());
        logData.get(date).add(new LogEntry(userId, date, timestamp));
    }

    public int countMatchingUsers(String date) {
        if (!logData.containsKey(date)) return 0;

        TreeSet<LogEntry> logs = logData.get(date);
        List<LogEntry> sortedLogs = new ArrayList<>(logs);
        int count = 0;

        for (int i = 0; i < sortedLogs.size(); i++) {
            for (int j = i + 1; j < sortedLogs.size(); j++) {
                if (sortedLogs.get(i).userId > sortedLogs.get(j).userId &&
                        sortedLogs.get(i).timestamp < sortedLogs.get(j).timestamp) {
                    count++;
                }
            }
        }
        return count;
    }
}

public class StreamingLogAnalysis {
    public static void main(String[] args) {
        LogProcessor processor = new LogProcessor();

        // Simulating stream logs input
        processor.addLogEntry(102, "2025-06-16", 1624873410);
        processor.addLogEntry(101, "2025-06-16", 1624873405);
        processor.addLogEntry(105, "2025-06-16", 1624873420);
        processor.addLogEntry(103, "2025-06-16", 1624873400);

        System.out.println("Matching users count: " + processor.countMatchingUsers("2025-06-16"));
    }
}