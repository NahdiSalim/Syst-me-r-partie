import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class EchoClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            EchoService service = (EchoService) registry.lookup("EchoService");

            String response = service.echo("Hello RMI!");
            System.out.println("Réponse: " + response);
        } catch (Exception e) {
            System.err.println("Erreur client: " + e.toString());
            e.printStackTrace();
        }
    }
}
