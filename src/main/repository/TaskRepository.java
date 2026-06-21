package src.main.repository;

import java.util.List;

import src.main.model.TaskEntity;

public interface TaskRepository {
    List<TaskEntity> getAllTasks();
    TaskEntity getTaskById(int id);
    void deleteTask(int id);
    void updateTask(int id, TaskEntity taskEntity);
    TaskEntity createTaskEntity(TaskEntity taskEntity);
}
