import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int serverPort = 1234;
        try (ServerSocket serverSocket = new ServerSocket(serverPort);
             Socket clientSocket = serverSocket.accept();
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
             
            voiture v = (voiture) in.readObject();
            v.setCarburant(100);
            
            out.writeObject("Carburant mis à jour avec succès.");
            out.flush();
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
