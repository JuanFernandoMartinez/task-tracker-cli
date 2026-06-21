package src.main.model;

import java.time.LocalDateTime;

public class TaskEntity {
    private String taskTitle;
    private TaskStatus taskStatus;
    private long Id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TaskEntity(String taskName, TaskStatus taskStatus, int id) {
        this.taskStatus = taskStatus;
        this.taskTitle = taskName;
        this.Id = id;
        this.createdAt = LocalDateTime.now();
    }

    public TaskEntity(String taskName, TaskStatus taskStatus, long id,
            String createdAt, String updatedAt) {
        this.taskStatus = taskStatus;
        this.taskTitle = taskName;
        this.Id = id;
        this.createdAt = LocalDateTime.now();
        this.createdAt = (createdAt.equals("null")) ? null : LocalDateTime.parse(createdAt);
        this.updatedAt = (updatedAt.equals("null")) ? null : LocalDateTime.parse(updatedAt);
    }

    @Override
    public String toString() {
        return String.format("{\n  ID:%d\n  Task: %s\n  Status: %s\n  created At:%s\n  updated At:%s\n}",
                this.Id, this.taskTitle, this.taskStatus, this.createdAt, this.updatedAt);
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    
}
