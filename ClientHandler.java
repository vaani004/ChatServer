import java.io.*;
import java.net.*;

class ClientHandler implements Runnable {
    private String name;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket, String name) throws IOException {
        this.socket = socket;
        this.name = name;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        sendMessage("Welcome " + name + "! Start typing to chat.");
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        String message;

        try {
            while ((message = in.readLine()) != null) {
                System.out.println(name + ": " + message);

                for (ClientHandler client : Server.clientHandlers) {
                    if (client != this) {
                        client.sendMessage(name + ": " + message);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(name + " disconnected.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {}
            Server.clientHandlers.remove(this);
        }
    }
}
