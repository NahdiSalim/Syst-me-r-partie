import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import messaging.*;

import java.util.Iterator;

public class MessagingClient {
    private final ManagedChannel channel;
    private final MessagingServiceGrpc.MessagingServiceBlockingStub blockingStub;

    public MessagingClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = MessagingServiceGrpc.newBlockingStub(channel);
    }

    public void sendMessage(String sender, String recipient, String text) {
        MessageRequest request = MessageRequest.newBuilder()
                .setSender(sender)
                .setRecipient(recipient)
                .setText(text)
                .build();
        MessageResponse response = blockingStub.sendMessage(request);
        System.out.println("Response: " + response.getStatus());
    }

    public void getMessagesForUser(String username) {
        UserRequest request = UserRequest.newBuilder()
                .setUsername(username)
                .build();
        Iterator<Message> messages = blockingStub.getMessagesForUser(request).getMessagesList().iterator();
        while (messages.hasNext()) {
            Message message = messages.next();
            System.out.println("Message from " + message.getSender() + ": " + message.getText());
        }
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws Exception {
        MessagingClient client = new MessagingClient("localhost", 50051);
        client.sendMessage("Alice", "Bob", "Hello Bob!");
        client.getMessagesForUser("Bob");
        client.shutdown();
    }
}
