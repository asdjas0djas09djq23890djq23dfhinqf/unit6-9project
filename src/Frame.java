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
        panel = new DisplayPanel();
        panel.setLayout(null);
        frame.setContentPane(panel);
        frame.setVisible(true);
        panel.revalidate();
        panel.repaint();
    }
    public JPanel getPanel() {
        return panel;
    }
    public JFrame getJFrame() {
        return frame;
    }
}