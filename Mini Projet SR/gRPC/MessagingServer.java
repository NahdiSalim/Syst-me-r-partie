import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class MessagingServer {
    private Server server;

    public void start(int port) throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new MessagingServiceImpl())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down gRPC server since JVM is shutting down");
            MessagingServer.this.stop();
            System.err.println("Server shut down");
        }));
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        MessagingServer server = new MessagingServer();
        int port = 50051;
        server.start(port);
        server.blockUntilShutdown();
    }
}
