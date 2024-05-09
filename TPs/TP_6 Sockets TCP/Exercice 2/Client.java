import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost"; 
        int serverPort = 1234;
        try (Socket socket = new Socket(serverAddress, serverPort);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
             
            voiture v = new voiture("Sedan", "Toyota");
            out.writeObject(v);
            out.flush();
            
            String response = (String) in.readObject();
            System.out.println("Réponse du serveur : " + response);
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
