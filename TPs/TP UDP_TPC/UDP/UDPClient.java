import java.io.IOException;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] sendData = new byte[1024];
            String sentence = "Hello, UDP Server!";
            sendData = sentence.getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
            System.out.println("Message sent to UDP server");

        } catch (SocketException e) {
            System.out.println("SocketException: " + e.getMessage());
        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            if (clientSocket != null) {
                clientSocket.close();
            }
        }
    }
}
