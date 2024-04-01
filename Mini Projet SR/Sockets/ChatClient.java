import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            System.out.println("Connected to server");

            Thread receivingThread = new Thread(() -> receiveMessages(socket));
            receivingThread.start();

            OutputStream output = socket.getOutputStream();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String message = scanner.nextLine();
                output.write(message.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void receiveMessages(Socket socket) {
        try {
            InputStream input = socket.getInputStream();
            byte[] buffer = new byte[1024];

            while (true) {
                int bytesRead = input.read(buffer);
                if (bytesRead == -1) {
                    break;
                }

                String message = new String(buffer, 0, bytesRead);
                System.out.println("Received message: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
