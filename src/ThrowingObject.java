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
    private double Amplitude = (int)(Math.random() * 201) + 800; // Was 500-600, now 800-1000
    private double angle;
    private int counter;

    public ThrowingObject(String imagePath, JFrame frame) {
        counter =0;
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
        if (counter < 1 || y + 180 < frame.getContentPane().getHeight()) {
            g.drawImage(image, 0, 0, 180, 180, this);
        }
    }

    public ThrowingObject getObject() {
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (counter < 1) {
            angle += 0.05;
            x += (int) speedX;
            y = (int)(1292 - 180 + Amplitude * Math.sin(angle));
            setLocation(x, y);
            System.out.printf("x: %d y: %d angle: %.2f counter: %d%n",
                    x, y, angle, counter);
            if (angle >= 2 * Math.PI) {
                counter++;
            }
            repaint();
        }
    }
}