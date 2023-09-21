package Server;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import javax.imageio.ImageIO;

public class SendScreen extends Thread {
    private Socket socket;
    private Robot robot;
    private Rectangle rectangle;
    private boolean continueLoop = true;
    private OutputStream oos;

    public SendScreen(Socket socket, Robot robot, Rectangle rect) {
        this.socket = socket;
        this.robot = robot;
        rectangle = rect;
        start();
    }

    public void run() {
        try {
            oos = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (continueLoop) {
            BufferedImage image = robot.createScreenCapture(rectangle);
            try {
                ImageIO.write(image, "jpeg", oos);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
