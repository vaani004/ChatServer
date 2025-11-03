import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField messageField;

    @FXML
    private Button sendButton;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Connect to server
            socket = new Socket("localhost", 1234);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Start reader thread to listen for messages from server
            Thread readerThread = new Thread(() -> {
                try {
                    String msgFromServer;
                    while ((msgFromServer = reader.readLine()) != null) {
                        String timestamp = getCurrentTime();
                        String finalMsg = "[" + timestamp + "] " + msgFromServer;
                        Platform.runLater(() -> chatArea.appendText(finalMsg + "\n"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            readerThread.setDaemon(true);
            readerThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sendMessage() {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            String timestamp = getCurrentTime();
            writer.println(message); // send to server
            chatArea.appendText("[" + timestamp + "] You: " + message + "\n");
            messageField.clear();
        }
    }

    private String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return LocalTime.now().format(formatter);
    }
}

