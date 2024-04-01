import java.rmi.Naming;
import java.util.List;

public class TaskClient {
    public static void main(String[] args) {
        try {
            TaskManager taskManager = (TaskManager) Naming.lookup("//localhost/TaskManager");

            // Adding tasks
            taskManager.addTask("Task 1");
            taskManager.addTask("Task 2");
            taskManager.addTask("Task 3");

            // Getting all tasks
            List<String> allTasks = taskManager.getAllTasks();
            System.out.println("All tasks:");
            for (String task : allTasks) {
                System.out.println(task);
            }

            // Removing a task
            taskManager.removeTask(1);

            // Getting all tasks after removal
            allTasks = taskManager.getAllTasks();
            System.out.println("All tasks after removal:");
            for (String task : allTasks) {
                System.out.println(task);
            }
        } catch (Exception e) {
            System.err.println("TaskClient exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
