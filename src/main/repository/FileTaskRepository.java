package src.main.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import src.main.model.TaskEntity;
import src.main.model.TaskStatus;

public class FileTaskRepository implements TaskRepository {
    private final String path = System.getProperty("user.home") + File.separator
            + ".taskTracker" + File.separator + "data" + File.separator + "Tasks.csv";
    private List<TaskEntity> TASKS;

    public FileTaskRepository() {
        this.TASKS = loadTasks();
    }

    private List<TaskEntity> loadTasks() {
        List<TaskEntity> taskEntities = new ArrayList<>();
        try {
            System.out.println("Logging::: " + path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            taskEntities = new LinkedList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                System.err.printf("Logging::: line: %s ", line);
                String[] parts = line.split(" , ");
                System.out.printf("Logging::: parts: %s", parts.toString());
                TaskEntity newTask = new TaskEntity(parts[1], TaskStatus.valueOf(parts[2]), Integer.parseInt(parts[0]),
                        parts[3], parts[4]);
                taskEntities.add(newTask);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return taskEntities;
    }

    private void saveTasks() {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(path));
            TASKS.forEach(x -> {
                try {
                    fileWriter.write(
                            String.format("%d , %s , %s , %s , %s\n", x.getId(), x.getTaskTitle(), x.getTaskStatus(),
                                    x.getCreatedAt(), x.getUpdatedAt()));
                } catch (FileNotFoundException e) {
                    System.out.println("Logging::: File does not exists, creating it");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TaskEntity> getAllTasks() {
        return TASKS;
    }

    @Override
    public TaskEntity getTaskById(int id) {
        return TASKS.get(id);
    }

    @Override
    public void deleteTask(int id) {
        TASKS.remove(id);
        saveTasks();
    }

    @Override
    public void updateTask(int id, TaskEntity taskEntity) {
        TaskEntity oldEntity = TASKS.get(id);
        oldEntity.setTaskTitle(taskEntity.getTaskTitle());
        oldEntity.setTaskStatus(taskEntity.getTaskStatus());
        oldEntity.setUpdatedAt(LocalDateTime.now());
        saveTasks();
    }

    @Override
    public TaskEntity createTaskEntity(TaskEntity taskEntity) {
        taskEntity.setCreatedAt(LocalDateTime.now());
        taskEntity.setId(TASKS.size());
        TASKS.add(taskEntity);
        saveTasks();
        return taskEntity;
    }

}
