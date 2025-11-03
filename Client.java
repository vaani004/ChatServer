import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Connected to the chat server!");

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Sending thread
        new Thread(() -> {
            String message;
            try {
                while ((message = userInput.readLine()) != null) {
                    String time = getCurrentTime();
                    System.out.println("[" + time + "] You: " + message);
                    out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Receiving thread
        String msgFromServer;
        while ((msgFromServer = in.readLine()) != null) {
            String time = getCurrentTime();
            System.out.println("[" + time + "] Server: " + msgFromServer);
        }

        socket.close();
    }

    // Step 2: Method to get current time in hh:mm AM/PM format
    private static String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return LocalTime.now().format(formatter);
    }
}
