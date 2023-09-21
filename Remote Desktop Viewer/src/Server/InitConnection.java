package Server;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

public class InitConnection {

    private ServerSocket serverSocket;
    private DataInputStream password;
    private DataOutputStream verify;
    private String width;
    private String height;

    public InitConnection(int port, String value1) {
        Robot robot;
        Rectangle rectangle;
        try {
            System.out.println("Waiting for a client to connect...");
            serverSocket = new ServerSocket(port);

            GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            width = String.valueOf(dim.getWidth());
            height = String.valueOf(dim.getHeight());
            rectangle = new Rectangle(dim);
            robot = new Robot(gDev);

            while (true) {
                Socket sc = serverSocket.accept();
                password = new DataInputStream(sc.getInputStream());
                verify = new DataOutputStream(sc.getOutputStream());

                String receivedPassword = password.readUTF();
                if (receivedPassword.equals(value1)) {
                    verify.writeUTF("valid");
                    verify.writeUTF(width);
                    verify.writeUTF(height);
                    new SendScreen(sc, robot, rectangle);
                    new ReceiveEvents(sc, robot);
                } else {
                    verify.writeUTF("invalid");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawGUI() {
        // Implement your GUI drawing logic here
    }

    public static void main(String[] args) {
        int port = 4907; // Change this to the desired port
        String password = JOptionPane.showInputDialog("Enter server password:");
        new InitConnection(port, password);
    }
}
