import javax.swing.*;
import java.awt.*;

public class Frame {

    public Frame() {
        JFrame frame = new JFrame("Fruit Ninja");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);

        DisplayPanel panel = new DisplayPanel();
        frame.setContentPane(panel);

        frame.setVisible(true);
    }
}