package lld;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class TaskSchedulerLLD {

    public enum TaskState {
        SCHEDULED,
        RUNNING,
        COMPLETED,
        FAILED,
        CANCELLED
    }

    public static class Task implements Comparable<Task> {
        private final String taskId;
        private final Runnable runnable;
        private long nextExecutionTime;
        private final boolean isRecurring;
        private final long periodMs;
        private final Integer maxExecutions;
        private int executionsCount;
        private TaskState state;

        public Task(String taskId, Runnable runnable, long nextExecutionTime) {
            this(taskId, runnable, nextExecutionTime, false, 0, null);
        }

        public Task(String taskId, Runnable runnable, long nextExecutionTime, boolean isRecurring, long periodMs, Integer maxExecutions) {
            this.taskId = taskId;
            this.runnable = runnable;
            this.nextExecutionTime = nextExecutionTime;
            this.isRecurring = isRecurring;
            this.periodMs = periodMs;
            this.maxExecutions = maxExecutions;
            this.executionsCount = 0;
            this.state = TaskState.SCHEDULED;
        }

        public synchronized TaskState getState() {
            return state;
        }

        public synchronized void setState(TaskState state) {
            this.state = state;
        }

        public synchronized void incrementExecutionsCount() {
            this.executionsCount++;
        }

        public synchronized int getExecutionsCount() {
            return executionsCount;
        }

        @Override
        public int compareTo(Task other) {
            return Long.compare(this.nextExecutionTime, other.nextExecutionTime);
        }

        @Override
        public String toString() {
            return "Task{" +
                    "taskId='" + taskId + '\'' +
                    ", nextExecutionTime=" + nextExecutionTime +
                    ", isRecurring=" + isRecurring +
                    ", executionsCount=" + executionsCount +
                    ", state=" + state +
                    '}';
        }
    }

    public static class CustomTaskScheduler {
        private final PriorityQueue<Task> queue;
        private final ReentrantLock lock;
        private final Condition condition;
        private final ExecutorService workerPool;
        private final Map<String, Task> taskRegistry;
        private final Thread coordinatorThread;
        private volatile boolean running;

        public CustomTaskScheduler(int workerPoolSize) {
            this.queue = new PriorityQueue<>();
            this.lock = new ReentrantLock();
            this.condition = lock.newCondition();
            this.workerPool = Executors.newFixedThreadPool(workerPoolSize);
            this.taskRegistry = new ConcurrentHashMap<>();
            this.running = true;

            this.coordinatorThread = new Thread(this::coordinatorLoop, "Scheduler-Coordinator");
            this.coordinatorThread.start();
        }

        public void schedule(Task task) {
            lock.lock();
            try {
                if (!running) {
                    throw new IllegalStateException("Scheduler is stopped.");
                }
                taskRegistry.put(task.taskId, task);
                queue.offer(task);
                System.out.println("[Scheduled] " + task.taskId + " at time: " + task.nextExecutionTime);
                
                // Wake up coordinator early if this new task is now the earliest one due
                if (queue.peek() == task) {
                    condition.signal();
                }
            } finally {
                lock.unlock();
            }
        }

        public void cancel(String taskId) {
            lock.lock();
            try {
                Task task = taskRegistry.get(taskId);
                if (task != null) {
                    task.setState(TaskState.CANCELLED);
                    queue.remove(task);
                    taskRegistry.remove(taskId);
                    System.out.println("[Cancelled] Task " + taskId);
                    
                    // Signal to re-evaluate wait time since the head of queue might have changed
                    condition.signal();
                }
            } finally {
                lock.unlock();
            }
        }

        private void coordinatorLoop() {
            while (running) {
                lock.lock();
                try {
                    if (queue.isEmpty()) {
                        condition.await();
                    } else {
                        Task nextTask = queue.peek();
                        long now = System.currentTimeMillis();
                        
                        if (now >= nextTask.nextExecutionTime) {
                            queue.poll();
                            
                            // Double check if cancelled before running
                            if (nextTask.getState() == TaskState.CANCELLED) {
                                continue;
                            }
                            
                            nextTask.setState(TaskState.RUNNING);
                            workerPool.submit(() -> executeTask(nextTask));
                        } else {
                            long delay = nextTask.nextExecutionTime - now;
                            condition.awaitNanos(TimeUnit.MILLISECONDS.toNanos(delay));
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } finally {
                    lock.unlock();
                }
            }
        }

        private void executeTask(Task task) {
            System.out.println("[Executing] Task " + task.taskId + " on Thread: " + Thread.currentThread().getName());
            try {
                task.runnable.run();
                task.setState(TaskState.COMPLETED);
                System.out.println("[Success] Task " + task.taskId);
            } catch (Exception e) {
                task.setState(TaskState.FAILED);
                System.out.println("[Failed] Task " + task.taskId + " threw exception: " + e.getMessage());
            } finally {
                task.incrementExecutionsCount();
                
                // Handle Rescheduling for recurring tasks
                lock.lock();
                try {
                    if (task.isRecurring && task.getState() != TaskState.CANCELLED) {
                        boolean scheduleNext = true;
                        if (task.maxExecutions != null && task.getExecutionsCount() >= task.maxExecutions) {
                            scheduleNext = false;
                        }
                        
                        if (scheduleNext) {
                            task.nextExecutionTime = System.currentTimeMillis() + task.periodMs;
                            task.setState(TaskState.SCHEDULED);
                            queue.offer(task);
                            condition.signal(); // Re-evaluate queue
                            System.out.println("[Rescheduled] Task " + task.taskId + " for next run at: " + task.nextExecutionTime);
                        } else {
                            taskRegistry.remove(task.taskId);
                            System.out.println("[Completed All Runs] Task " + task.taskId);
                        }
                    } else {
                        taskRegistry.remove(task.taskId);
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

        public void shutdown() {
            running = false;
            lock.lock();
            try {
                condition.signalAll();
            } finally {
                lock.unlock();
            }
            coordinatorThread.interrupt();
            workerPool.shutdown();
            try {
                if (!workerPool.awaitTermination(2, TimeUnit.SECONDS)) {
                    workerPool.shutdownNow();
                }
            } catch (InterruptedException e) {
                workerPool.shutdownNow();
            }
            System.out.println("[Shutdown] Scheduler stopped.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CustomTaskScheduler scheduler = new CustomTaskScheduler(3);
        long baseTime = System.currentTimeMillis();

        // 1. Test One-time Task (Runs after 2 seconds)
        scheduler.schedule(new Task("OneTime-1", () -> {
            System.out.println("-> Hello from OneTime-1!");
        }, baseTime + 2000));

        // 2. Test Recurring Task (Runs every 1 second, up to 3 times)
        scheduler.schedule(new Task("Recurring-1", () -> {
            System.out.println("-> Hello from Recurring-1!");
        }, baseTime + 1000, true, 1000, 3));

        // 3. Test Task Cancellation (Runs after 4 seconds, cancelled after 1.5 seconds)
        Task taskToCancel = new Task("CancelMe", () -> {
            System.out.println("-> This should NEVER print!");
        }, baseTime + 4000);
        scheduler.schedule(taskToCancel);

        // 4. Test Faulty Task (Throws exception, shouldn't crash scheduler)
        scheduler.schedule(new Task("Faulty-1", () -> {
            System.out.println("-> Executing Faulty Task, about to crash...");
            throw new RuntimeException("Simulated Task Failure!");
        }, baseTime + 1500));

        // Wait to observe execution
        Thread.sleep(1500);
        System.out.println("\n--- Cancelling task CancelMe ---");
        scheduler.cancel("CancelMe");

        // Wait for remaining tasks to finish
        Thread.sleep(3000);

        System.out.println("\n--- Shutting down scheduler ---");
        scheduler.shutdown();
    }
}
