package microsoft;

import java.util.*;
import java.util.concurrent.*;

public class TaskScheduler {
    private final PriorityBlockingQueue<Task> taskQueue;
    private final ScheduledExecutorService executor;
    private final DependencyManager dependencyManager;
    private final List<TaskObserver> observers = new ArrayList<>();

    public TaskScheduler(int threads) {
        this.taskQueue = new PriorityBlockingQueue<>(11,
                Comparator.comparingLong(Task::getScheduledTime));
        this.executor = Executors.newScheduledThreadPool(threads);
        this.dependencyManager = new DependencyManager();
    }

    public void submitTask(Task task) {
        dependencyManager.updateStatus(task.getId(), TaskStatus.PENDING);
        taskQueue.add(task);
    }

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String taskId, TaskStatus status) {
        dependencyManager.updateStatus(taskId, status);
        observers.forEach(o -> o.onStatusChange(taskId, status));
    }

    public void start() {
        executor.submit(() -> {
            while (true) {
                try {
                    Task task = taskQueue.take();
                    long delay = task.getScheduledTime() - System.currentTimeMillis();

                    if (dependencyManager.isReady(task)) {
                        executor.schedule(() -> executeTask(task), Math.max(0, delay), TimeUnit.MILLISECONDS);
                    } else {
                        // Re-queue if dependencies aren't met
                        Thread.sleep(100);
                        taskQueue.add(task);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
    }

    private void executeTask(Task task) {
        notifyObservers(task.getId(), TaskStatus.RUNNING);
        try {
            task.execute();
            notifyObservers(task.getId(), TaskStatus.COMPLETED);

            if (task.isRecurring()) {
                // Schedule next run
                long nextRun = System.currentTimeMillis() + task.getInterval();
                // Create a new task instance or update the existing one
                // submitTask(new RecurringTask(...));
            }
        } catch (Exception e) {
            notifyObservers(task.getId(), TaskStatus.FAILED);
        }
    }

    enum TaskStatus {PENDING, RUNNING, COMPLETED, FAILED}

    interface Task {
        String getId();

        void execute();

        long getScheduledTime();

        boolean isRecurring();

        Long getInterval(); // null if one-time
    }

    interface TaskObserver {
        void onStatusChange(String taskId, TaskStatus status);
    }

    class DependencyManager {
        private final Map<String, Set<String>> adjList = new ConcurrentHashMap<>();
        private final Map<String, TaskStatus> taskStatuses = new ConcurrentHashMap<>();

        public void addDependency(String taskId, String dependsOnId) {
            adjList.computeIfAbsent(taskId, k -> new HashSet<>()).add(dependsOnId);
        }

        public boolean isReady(Task task) {
            Set<String> dependencies = adjList.getOrDefault(task.getId(), Collections.emptySet());
            for (String depId : dependencies) {
                if (taskStatuses.getOrDefault(depId, TaskStatus.PENDING) != TaskStatus.COMPLETED) {
                    return false;
                }
            }
            return true;
        }

        public void updateStatus(String taskId, TaskStatus status) {
            taskStatuses.put(taskId, status);
        }
    }

    class ConsoleLoggerObserver implements TaskObserver {
        @Override
        public void onStatusChange(String taskId, TaskStatus status) {
            System.out.println("[Observer] Task " + taskId + " is now " + status);
        }
    }

}
