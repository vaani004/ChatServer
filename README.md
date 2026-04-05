💬 Java Chat Server Application

<p align="center">
  <b>A Real-Time Multi-Client Chat Application using Java, JavaFX & Socket Programming</b>
</p>

<p align="center">
  🚀 Multi-Threaded Server | 💻 JavaFX GUI | 🌐 Real-Time Messaging
</p>

📌 Overview

This project is a **real-time chat application** built using **Java Socket Programming** and **JavaFX**.  
It allows multiple users to connect to a server and exchange messages instantly.

✨ Features

🔥 Real-time messaging between multiple clients  
👥 Multi-client support using multi-threading  
🖥️ GUI-based chat interface using JavaFX  
🧠 Auto-generated usernames  
📡 Message broadcasting to all connected clients  
⚡ Fast and lightweight implementation  

🛠️ Tech Stack

| Technology | Usage |
|-----------|------|
| ☕ Java | Core logic |
| 🎨 JavaFX | GUI |
| 🌐 TCP Sockets | Communication |
| 🧵 Multithreading | Handle multiple clients |

📂 Project Structure

ChatServer/
│── Main.java
│── ChatServer.java
│── ChatClientController.java
│── chat.fxml
│── style.css
│── other files...

▶️ How to Run

🧩 Step 1: Compile

```bash
javac --module-path "YOUR_JAVAFX_PATH\lib" --add-modules javafx.controls,javafx.fxml *.java

🚀 Step 2: Start Server
java ChatServer

💻 Step 3: Run Client
java --module-path "YOUR_JAVAFX_PATH\lib" --add-modules javafx.controls,javafx.fxml Main

👉 Run multiple times to simulate multiple users.


**🚧 Future Improvements**
🔐 Login & Authentication
💬 Private Chat Feature
🟢 Online Users List
😊 Emoji Support
📁 Chat History Storage
🎨 Improved UI (WhatsApp-style)
👩‍💻 Author

Vanshika Sharma
🔗 https://github.com/vaani004

⭐ Support
If you like this project, give it a ⭐ on GitHub!
