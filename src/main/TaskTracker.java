package src.main;
import src.main.service.TaskService;
import src.main.service.TaskServiceImpl;

public class TaskTracker {
    public static void main(String[] args) {
        TaskService taskService = new TaskServiceImpl();
        
        String option = args[0];
        switch (option) {
            case "add":
                taskService.addTask(args[1]);
                break;
            case "remove":
                taskService.removeTask(Integer.parseInt(args[1]));
                break;
            case "all":
                System.out.println(taskService.getAllTasks());
                break;
            case "show":
                taskService.getTaskById(Integer.parseInt(args[1]));
                break;
            case "move":
                taskService.moveTask(Integer.parseInt(args[1]), args[2]);
                break;
            case "update":
                taskService.updateTask(Integer.parseInt(args[1]), args[2]);
                break;
            default:
                System.out.println(option);
                break;
        }
    }
}