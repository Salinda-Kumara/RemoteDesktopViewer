package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteDesktopServer {

    public static void main(String[] args) {
        int port = 12345;  // Change this to your desired port number

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for a client...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Start a new thread to handle the client
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {

    private Socket clientSocket;
    private DataInputStream verification;
    private DataOutputStream passchk;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            verification = new DataInputStream(clientSocket.getInputStream());
            passchk = new DataOutputStream(clientSocket.getOutputStream());

            String expectedPassword = "your_expected_password";  // Set your expected password here

            // Read the client's password attempt
            String clientPassword = verification.readUTF();

            if (clientPassword.equals(expectedPassword)) {
                passchk.writeUTF("valid");

                // Send width and height information to the client
                passchk.writeUTF("1920");  // Replace with the actual width
                passchk.writeUTF("1080");  // Replace with the actual height

                // CreateFrame class should handle further actions for a valid connection
                // CreateFrame abc = new CreateFrame(clientSocket, "1920", "1080");
                // abc.start();  // You should define and implement CreateFrame accordingly
            } else {
                passchk.writeUTF("invalid");
                System.out.println("Invalid password attempt from client.");
            }

            // Close the connection
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
