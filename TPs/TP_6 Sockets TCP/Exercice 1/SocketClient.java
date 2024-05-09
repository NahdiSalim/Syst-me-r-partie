import java.io.*;
import java.net.*;
import java.util.Scanner;
class SocketClient {
    public static void main(String argv[]) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);
        // Demander à l'utilisateur le nom et le port du serveur
        System.out.print("Nom du serveur : ");
        host = keyb.next();
        System.out.print("Port du serveur : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            System.err.println("Le second paramètre n'est pas un entier.");
            System.exit(-1);
        }
        try {
            // Trouver l'adresse IP du serveur
            InetAddress adr = InetAddress.getByName(host);
            // Création du socket pour se connecter au serveur
            Socket socket = new Socket(adr, port);
            // Création des flux pour envoyer et recevoir des données
            ObjectOutputStream output =
                new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input =
                new ObjectInputStream(socket.getInputStream());
            // Envoyer un message au serveur
            output.writeObject(new String("ma première socket"));
            // Lire la réponse du serveur
            String chaine = (String) input.readObject();
            System.out.println(" recu du serveur : " + chaine);
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
