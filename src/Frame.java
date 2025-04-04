import javax.swing.*;
import java.awt.*;

public class Frame {

    public Frame() {
        JFrame frame = new JFrame("Fruit Ninja");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1940, 1292);
        frame.setLocationRelativeTo(null);




        //background
        DisplayPanel panel = new DisplayPanel();
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
}