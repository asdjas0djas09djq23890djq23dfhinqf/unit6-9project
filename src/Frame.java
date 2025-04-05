import javax.swing.*;
import java.awt.*;

public class Frame {
    public DisplayPanel backgroundPanel;
    public JFrame frame;

    public Frame() {
        //create frame
        frame = new JFrame("Fruit Ninja");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1940, 1292);
        frame.setLocationRelativeTo(null);

        //background
        backgroundPanel = new DisplayPanel();
        backgroundPanel.setLayout(null);
        frame.setContentPane(backgroundPanel);

        frame.setVisible(true);

        backgroundPanel.revalidate();
        backgroundPanel.repaint();
    }

    public JPanel getPanel() {
        return backgroundPanel;
    }

    public JFrame getFrame() {
        return frame;
    }
}