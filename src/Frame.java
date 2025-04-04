import javax.swing.*;
import java.awt.*;

public class Frame {
    private DisplayPanel panel;
    private JFrame frame;

    public Frame() {
        frame = new JFrame("Fruit Ninja");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1940, 1292);
        frame.setLocationRelativeTo(null);

        //background
        panel = new DisplayPanel();
        panel.setLayout(null);
        frame.setContentPane(panel);

        //test object moving
        ThrowingObject object = new ThrowingObject("images/Apple.png", frame);
        object.setSize(frame.getWidth(), frame.getHeight());
        panel.add(object);

        frame.setVisible(true);

        panel.revalidate();
        panel.repaint();
    }

    public DisplayPanel getPanel() {
        return panel;
    }

    public JFrame getFrame() {
        return frame;
    }
}