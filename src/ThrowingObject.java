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
    private double Amplitude = (int)(Math.random() * 201) + 800;
    private double angle;
    private int counter;
    private  int elapsedSeconds = 0;

    public ThrowingObject(String imagePath, JFrame frame) {
        counter =0;
        this.image = new ImageIcon(imagePath).getImage();
        setOpaque(false);
        this.setVisible(false);
        x = (int)(Math.random() * (800)) + 300;
        if (x>=750) {
            speedX = -((int)(Math.random() * 3) + 1);
        } else {
            speedX = (int)(Math.random() * 3) + 1;
        }
        this.frame = frame;
        setLocation(x, y);
        this.frame.setBounds(0,0,1940,1280);
        timer = new Timer(30, this);
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
        elapsedSeconds ++;
        if (counter < 1) {
            angle += 0.05;
            x += (int) speedX;
            y = (int) (1292 - 180 + Amplitude * Math.sin(angle));
            setLocation(x, y);
            if (angle >= 2 * Math.PI) {
                counter++;
            }
            repaint();
            if (elapsedSeconds == 2) {
                this.setVisible(true);
            }
        }
    }
}