import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ThrowingObject extends JPanel implements ActionListener {
    private Image image;
    private int x;
    private int y;
    private double speedX;
    private Timer timer;
    private JFrame frame;
    private double Amplitude = (int)(Math.random() * (101)) + 500;
    private double angle;

    public ThrowingObject(String imagePath, JFrame frame) {
        this.image = new ImageIcon(imagePath).getImage();
        setOpaque(false);
        x = (int)(Math.random() * (501)) + 500;
        if (x>=750) {
            speedX =-2;
        } else {
            speedX =2;
        }
        this.frame = frame;
        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (y + 90 < frame.getContentPane().getHeight()) {
            g.drawImage(image, x, y, 180, 180, this);  // Draw the object at new position
        }
    }

    public ThrowingObject getObject() {
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += (int) speedX;
        angle+=0.05;
        y = (int) (frame.getContentPane().getHeight() - 180 + Amplitude * Math.sin(angle));
        if (y > 1900) {
            System.out.println("sucecss");
            frame.remove(getObject());
        }
        repaint();
    }
}
