import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<Socket> clientSockets = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientSockets.add(clientSocket);
                System.out.println("New client connected: " + clientSocket);

                Thread clientHandler = new Thread(() -> handleClient(clientSocket));
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try {
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

            while (true) {
                byte[] buffer = new byte[1024];
                int bytesRead = input.read(buffer);
                if (bytesRead == -1) {
                    break;
                }

                String message = new String(buffer, 0, bytesRead);
                System.out.println("Received message from " + clientSocket + ": " + message);

                // Broadcast the message to all clients
                broadcastMessage(message, clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clientSockets.remove(clientSocket);
            System.out.println("Client disconnected: " + clientSocket);
        }
    }

    private static void broadcastMessage(String message, Socket senderSocket) throws IOException {
        for (Socket clientSocket : clientSockets) {
            if (clientSocket != senderSocket) {
                clientSocket.getOutputStream().write(message.getBytes());
            }
        }
    }
}
