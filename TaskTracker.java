import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class TaskTracker {

    private static List<TaskEntity> TASKS = new LinkedList<>();
    private static boolean wasDbModified = false;

    public static void main(String[] args) {
        System.out.println("Logging::: Loading data from file");
        loadTasks();

        String option = args[0];
        switch (option) {
            case "add":
                addTask(args[1]);
                wasDbModified = true;
                break;
            case "remove":
                deleteTask(Integer.parseInt(args[1]));
                wasDbModified = true;
                break;
            case "all":
                showTask(-1);
                break;
            case "show":
                showTask(Integer.parseInt(args[1]));
                break;
            case "move":
                moveTask(Integer.parseInt(args[1]), args[2]);
                wasDbModified = true;
                break;
            case "update":
                updatTask(Integer.parseInt(args[1]), args[2]);
                wasDbModified = true;
                break;
            default:
                System.out.println(option);
                break;
        }

        if (wasDbModified)
            saveTasks();

    }

    public static void moveTask(int id, String newStatus) {
        TaskEntity toUpdateTask = TASKS.get(id);
        toUpdateTask.taskStatus = TaskStatus.valueOf(newStatus);
        toUpdateTask.updatedAt = LocalDateTime.now();
    }

    public static void updatTask(int id, String newMessage) {
        TaskEntity toUpdateTask = TASKS.get(id);
        toUpdateTask.taskTitle = newMessage;
        toUpdateTask.updatedAt = LocalDateTime.now();
    }

    public static void saveTasks() {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("tasks.csv"));
            TASKS.forEach(x -> {
                try {
                    fileWriter.write(String.format("%d , %s , %s , %s , %s\n", x.Id, x.taskTitle, x.taskStatus,
                            x.createdAt, x.updatedAt));
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

    public static void loadTasks() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("tasks.csv"));
            TASKS = new LinkedList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                System.err.printf("Logging::: line: %s ", line);
                String[] parts = line.split(" , ");
                System.out.printf("Logging::: parts: %s", parts.toString());
                TaskEntity newTask = new TaskEntity(parts[1], TaskStatus.valueOf(parts[2]), Integer.parseInt(parts[0]),
                        parts[3], parts[4]);
                TASKS.add(newTask);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTask(int id) {
        TASKS.remove(id);
    }

    public static void showTask(int index) {
        if (index == -1) {
            System.out.println(TASKS.toString());
        } else {
            try {
                System.out.println(TASKS.get(index).toString());
            } catch (IndexOutOfBoundsException | NullPointerException ex) {
                System.err.println("Seems like your task does not exists");
            }
        }
    }

    public static void addTask(String taskName, TaskStatus taskStatus) {
        System.out.println("Logging::: Adding a new Task");
        TASKS.add(new TaskEntity(taskName, taskStatus));
    }

    public static void addTask(String taskName) {
        System.out.println("Logging::: Adding a new Task");
        TASKS.add(new TaskEntity(taskName, TaskStatus.TODO));
    }

    private static enum TaskStatus {
        TODO, IN_PROGRESS, DONE;
    }

    private static class TaskEntity {
        private String taskTitle;
        private TaskStatus taskStatus;
        private long Id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public TaskEntity(String taskName, TaskStatus taskStatus) {
            this.taskStatus = taskStatus;
            this.taskTitle = taskName;
            this.Id = TASKS.size();
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
    }

}