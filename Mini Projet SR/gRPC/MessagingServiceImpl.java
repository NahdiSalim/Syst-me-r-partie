import io.grpc.stub.StreamObserver;
import messaging.*;

public class MessagingServiceImpl extends MessagingServiceGrpc.MessagingServiceImplBase {
    @Override
    public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        // Logique pour envoyer un message
        System.out.println("Message sent from " + request.getSender() + " to " + request.getRecipient() + ": " + request.getText());

        // Envoyer la réponse
        MessageResponse response = MessageResponse.newBuilder()
                .setStatus("Message sent successfully")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getMessagesForUser(UserRequest request, StreamObserver<MessageList> responseObserver) {
        // Logique pour récupérer les messages pour un utilisateur donné
        String username = request.getUsername();
        // Supposez que vous ayez une liste de messages pour cet utilisateur
        MessageList messageList = MessageList.newBuilder()
                .addMessages(Message.newBuilder().setSender("John").setText("Hello").build())
                .addMessages(Message.newBuilder().setSender("Alice").setText("Hi").build())
                .build();

        // Envoyer la réponse
        responseObserver.onNext(messageList);
        responseObserver.onCompleted();
    }
}
