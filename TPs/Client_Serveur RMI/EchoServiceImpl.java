import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class EchoServiceImpl extends UnicastRemoteObject implements EchoService {

    public EchoServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String echo(String message) throws RemoteException {
        return "Echo: " + message;
    }
}
