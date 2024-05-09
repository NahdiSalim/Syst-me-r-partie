import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Serveur {
    public static void main(String[] args) {
        try {
            CalculatriceImpl calcImpl = new CalculatriceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Calculatrice", calcImpl);
            System.out.println("Service de calculatrice RMI prêt.");
        } catch (Exception e) {
            System.err.println("Exception serveur: " + e.toString());
            e.printStackTrace();
        }
    }
}
