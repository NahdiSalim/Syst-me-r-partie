import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TaskManagerImpl extends UnicastRemoteObject implements TaskManager {
    private List<String> tasks;

    protected TaskManagerImpl() throws RemoteException {
        tasks = new ArrayList<>();
    }

    @Override
    public synchronized void addTask(String task) throws RemoteException {
        tasks.add(task);
        System.out.println("Task added: " + task);
    }

    @Override
    public synchronized void removeTask(int index) throws RemoteException {
        if (index >= 0 && index < tasks.size()) {
            String removedTask = tasks.remove(index);
            System.out.println("Task removed: " + removedTask);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    @Override
    public synchronized List<String> getAllTasks() throws RemoteException {
        return new ArrayList<>(tasks);
    }

    public static void main(String[] args) {
        try {
            TaskManager taskManager = new TaskManagerImpl();
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            java.rmi.Naming.rebind("//localhost/TaskManager", taskManager);
            System.out.println("TaskManager server is running...");
        } catch (Exception e) {
            System.err.println("TaskManager server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
