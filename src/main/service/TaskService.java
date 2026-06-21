package src.main.service;

public interface TaskService {
    void addTask(String taskName);
    void addTaskWithStatus(String taskName, String TaskStatus);
    void removeTask(int id);
    String getAllTasks();
    String getTaskById(int id);
    void moveTask(int id, String newStatus);
    void updateTask(int id, String newTaskName);
}
