import javax.swing.*;
import java.awt.*;

class ThrowingObject extends JPanel{
    private Image image;

    public ThrowingObject(String imagePath) {
        this.image = new ImageIcon(imagePath).getImage();
        setOpaque(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, 180, 180, this);
    }
}