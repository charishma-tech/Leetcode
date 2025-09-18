import java.util.*;

class TaskManager {

    private static class Node {
        int priority;
        int taskId;
        Node(int p, int t) { this.priority = p; this.taskId = t; }
    }

    private static class TaskInfo {
        int userId;
        int priority;
        TaskInfo(int u, int p) { this.userId = u; this.priority = p; }
    }

    // Max-heap: higher priority first; if tie, higher taskId first
    private final PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
        if (a.priority != b.priority) return Integer.compare(b.priority, a.priority);
        return Integer.compare(b.taskId, a.taskId);
    });

    // Live truth: taskId -> {userId, currentPriority}
    private final Map<Integer, TaskInfo> live = new HashMap<>();

    public TaskManager(List<List<Integer>> tasks) {
        for (List<Integer> t : tasks) {
            int userId = t.get(0), taskId = t.get(1), priority = t.get(2);
            add(userId, taskId, priority);
        }
    }

    public void add(int userId, int taskId, int priority) {
        live.put(taskId, new TaskInfo(userId, priority));
        pq.offer(new Node(priority, taskId));
    }

    public void edit(int taskId, int newPriority) {
        TaskInfo info = live.get(taskId); // guaranteed to exist
        info.priority = newPriority;      // update live truth
        pq.offer(new Node(newPriority, taskId)); // old heap entry becomes stale
    }

    public void rmv(int taskId) {
        live.remove(taskId); // mark as removed; heap entries (if any) are now stale
    }

    public int execTop() {
        while (!pq.isEmpty()) {
            Node top = pq.peek();
            TaskInfo info = live.get(top.taskId);
            // Discard stale entries: removed or priority mismatch
            if (info == null || info.priority != top.priority) {
                pq.poll();
                continue;
            }
            // Valid top: execute it
            pq.poll();
            live.remove(top.taskId);
            return info.userId;
        }
        return -1;
    }
}
