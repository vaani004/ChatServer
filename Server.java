import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    static Vector<ClientHandler> clientHandlers = new Vector<>();
    static int clientCount = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server started on port 1234...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected: " + clientSocket);

            ClientHandler clientHandler = new ClientHandler(clientSocket, "client" + (++clientCount));
            clientHandlers.add(clientHandler);

            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }
}
