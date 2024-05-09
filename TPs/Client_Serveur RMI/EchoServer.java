import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class EchoServer {
    public static void main(String[] args) {
        try {
            EchoService service = new EchoServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("EchoService", service);
            System.out.println("Service Echo est prêt.");
        } catch (Exception e) {
            System.err.println("Erreur serveur: " + e.toString());
            e.printStackTrace();
        }
    }
}
