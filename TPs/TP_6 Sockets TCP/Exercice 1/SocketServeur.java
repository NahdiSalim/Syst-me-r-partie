import java.io.*;
import java.net.*;
import java.util.Scanner;
public class SocketServeur {
    public static void main(String argv[]) {
        int port = 0;
        Scanner keyb = new Scanner(System.in);
        // Demander à l'utilisateur de saisir le port d'écoute du serveur
        System.out.print("Port d'écoute : ");
        try {
            port = keyb.nextInt(); // Lire le port du clavier
        } catch (NumberFormatException e) {
            System.err.println("Le paramètre n'est pas un entier.");
            System.err.println("Usage : java ServeurUDP port-serveur");
            System.exit(-1);
        }
        try {
            // Création du socket serveur sur le port spécifié
            ServerSocket serverSocket = new ServerSocket(port);
            // Accepter une connexion entrante
            Socket socket = serverSocket.accept();
            // Création des flux pour communiquer avec le client
            ObjectOutputStream output =
                new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input =
                new ObjectInputStream(socket.getInputStream());
            // Lire un objet depuis le client
            String chaine = (String) input.readObject();
            System.out.println(" recu : " + chaine);
            // Afficher l'adresse IP et le port du client
            System.out.println(" ca vient de : " + socket.getInetAddress() +
                ":" + socket.getPort());
            // Envoyer une réponse au client
            output.writeObject(new String("bien recu"));
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
