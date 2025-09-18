import java.util.*;

class TaskManager {

    static class Task {
        int userId;
        int taskId;
        int priority;
        Task(int u, int t, int p) {
            userId = u;
            taskId = t;
            priority = p;
        }
    }

    // Max heap: highest priority first, break ties by larger taskId
    private PriorityQueue<Task> pq;
    // Map: taskId -> Task (latest valid state)
    private Map<Integer, Task> taskMap;

    public TaskManager(List<List<Integer>> tasks) {
        taskMap = new HashMap<>();
        pq = new PriorityQueue<>((a, b) -> {
            if (a.priority != b.priority) return b.priority - a.priority;
            return b.taskId - a.taskId;
        });

        for (List<Integer> t : tasks) {
            int u = t.get(0), tid = t.get(1), p = t.get(2);
            Task task = new Task(u, tid, p);
            taskMap.put(tid, task);
            pq.offer(task);
        }
    }

    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority);
        taskMap.put(taskId, task);
        pq.offer(task);
    }

    public void edit(int taskId, int newPriority) {
        Task old = taskMap.get(taskId);
        if (old != null) {
            Task updated = new Task(old.userId, taskId, newPriority);
            taskMap.put(taskId, updated);
            pq.offer(updated);
        }
    }

    public void rmv(int taskId) {
        taskMap.remove(taskId); // lazy removal
    }

    public int execTop() {
        while (!pq.isEmpty()) {
            Task top = pq.peek();
            Task valid = taskMap.get(top.taskId);
            if (valid != null && valid.priority == top.priority) {
                pq.poll();
                taskMap.remove(top.taskId);
                return top.userId;
            }
            pq.poll(); // discard stale task
        }
        return -1;
    }
}
