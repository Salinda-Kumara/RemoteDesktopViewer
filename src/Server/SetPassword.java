package Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class SetPassword extends JFrame implements ActionListener {
    private static final String DEFAULT_PORT = "4907";
    private JButton submit;
    private JPanel panel;
    private JTextField text1;
    private String value1;
    private JLabel label1, label;

    public SetPassword() {
        label1 = new JLabel("Set Password");
        text1 = new JTextField(15);
        label = new JLabel();
        this.setLayout(new BorderLayout());
        submit = new JButton("Submit");
        panel = new JPanel(new GridLayout(2, 1));
        panel.add(label1);
        panel.add(text1);
        panel.add(label);
        panel.add(submit);
        add(panel, BorderLayout.CENTER);
        submit.addActionListener(this);
        setTitle("Setting password for client");
    }

    public void actionPerformed(ActionEvent e) {
        value1 = text1.getText();
        dispose();
        new InitConnection(Integer.parseInt(DEFAULT_PORT), value1);
    }

    public static String getValue1() {
        return getValue1();
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            SetPassword frame = new SetPassword();
            frame.setSize(300, 80);
            frame.setLocation(500, 300);
            frame.setVisible(true);
        });
    }
}
