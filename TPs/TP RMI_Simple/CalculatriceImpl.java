import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class CalculatriceImpl extends UnicastRemoteObject implements Calculatrice {

    protected CalculatriceImpl() throws RemoteException {
        super();
    }

    @Override
    public double addition(double a, double b) throws RemoteException {
        return a + b;
    }

    @Override
    public double soustraction(double a, double b) throws RemoteException {
        return a - b;
    }

    @Override
    public double multiplication(double a, double b) throws RemoteException {
        return a * b;
    }

    @Override
    public double division(double a, double b) throws RemoteException {
        if (b == 0) throw new RemoteException("Division par zéro.");
        return a / b;
    }
}
