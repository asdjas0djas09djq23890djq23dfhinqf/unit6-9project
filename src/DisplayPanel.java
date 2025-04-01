import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon background = new ImageIcon("backgroundImage/backgroundfruitninja.jpg");
        g.drawImage(background.getImage(), 0, 0 ,getWidth(), getHeight(), this);
    }
}