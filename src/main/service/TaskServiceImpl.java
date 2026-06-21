package src.main.service;

import java.time.LocalDateTime;

import src.main.model.TaskEntity;
import src.main.model.TaskStatus;
import src.main.repository.FileTaskRepository;
import src.main.repository.TaskRepository;

public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(){
        taskRepository = new FileTaskRepository();
    }

    @Override
    public void addTask(String taskName) {
        TaskEntity taskEntity = new TaskEntity(taskName, TaskStatus.TODO, 0);
        this.taskRepository.createTaskEntity(taskEntity);
    }

    @Override
    public void addTaskWithStatus(String taskName, String taskStatus) {
        TaskEntity taskEntity = new TaskEntity(taskName, 
            TaskStatus.valueOf(taskStatus), 0);
        taskRepository.createTaskEntity(taskEntity);
    }

    @Override
    public void removeTask(int id) {
        taskRepository.deleteTask(id);
    }

    @Override
    public String getAllTasks() {
        return taskRepository.getAllTasks().toString();
    }

    @Override
    public String getTaskById(int id) {
        return taskRepository.getTaskById(id).toString();
    }

    @Override
    public void moveTask(int id, String newStatus) {
        TaskEntity oldEntity = taskRepository.getTaskById(id);
        oldEntity.setTaskStatus(TaskStatus.valueOf(newStatus));
        oldEntity.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public void updateTask(int id, String newTaskName) {
        TaskEntity oldEntity = taskRepository.getTaskById(id);
        oldEntity.setTaskTitle(newTaskName);
        oldEntity.setUpdatedAt(LocalDateTime.now());
    }
    
}
