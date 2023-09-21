package Client;

import java.net.Socket;
import javax.swing.JOptionPane;

public class Start {
    private static final String DEFAULT_PORT = "4907";

    public static void main(String[] args) {
        String ip = JOptionPane.showInputDialog("Please enter the server IP");
        new Start().initialize(ip, Integer.parseInt(DEFAULT_PORT));
    }

    public void initialize(String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
            System.out.println("Connected to the server");

            Authentication frame1 = new Authentication(socket);
            frame1.setSize(300, 80);
            frame1.setLocation(500, 300);
            frame1.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
