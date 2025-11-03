import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClientController {
    @FXML
    private TextField messageField;

    @FXML
    private Button sendButton;

    @FXML
    private TextArea chatArea;

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public void initialize() {
        try {
            socket = new Socket("localhost", 1234);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            // Start a new thread to listen for messages from the server
            new Thread(() -> {
                try {
                    while (true) {
                        String message = input.readUTF();
                        Platform.runLater(() -> chatArea.appendText("Server: " + message + "\n"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        sendButton.setOnAction(event -> sendMessage());
    }

    private void sendMessage() {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            try {
                output.writeUTF(message);
                chatArea.appendText("Me: " + message + "\n");
                messageField.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


