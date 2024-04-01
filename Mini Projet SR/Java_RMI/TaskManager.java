import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TaskManager extends Remote {
    void addTask(String task) throws RemoteException;
    void removeTask(int index) throws RemoteException;
    List<String> getAllTasks() throws RemoteException;
}
