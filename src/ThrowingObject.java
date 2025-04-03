import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ThrowingObject extends JPanel implements ActionListener {
    private Image image;
    private int x;
    private int y;
    private double angle;
    private Timer timer;
    private JFrame frame;

    public ThrowingObject(String imagePath, JFrame frame) {
        this.image = new ImageIcon(imagePath).getImage();
        setOpaque(false);
        x = 200;
        angle = 0;
        this.frame = frame;
        timer = new Timer(1, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, 180, 180, this);
    }

    public void actionPerformed(ActionEvent e) {
        x += 2;
        angle += 0.02;
        y = 350;
        y *= Math.cos(angle);
        if (x > getWidth()) {
            x = -image.getWidth(this);
        }
        if (y > 900) {
            //frame.remove()
        }
        repaint();
    }
}