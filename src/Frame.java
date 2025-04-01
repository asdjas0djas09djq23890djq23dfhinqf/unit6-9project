import javax.swing.*;
import java.awt.*;

public class Frame {

    public Frame() {
        JFrame frame = new JFrame("Fruit Ninja");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1940, 1292);
        frame.setLocationRelativeTo(null);

        DisplayPanel panel = new DisplayPanel();
        panel.setLayout(null);
        frame.setContentPane(panel);

        ThrowingObject object = new ThrowingObject("images/Apple.png");
        object.setBounds(200, 200, 180, 180);
        panel.add(object);

        frame.setVisible(true);

        panel.revalidate();
        panel.repaint();
    }
}