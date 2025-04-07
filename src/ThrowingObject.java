import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class ThrowingObject extends JPanel {
    private Image image;
    private int x;
    private int y;
    private double speedX;
    private JFrame frame;
    private double Amplitude = (int)(Math.random() * 201) + 800;
    private double angle;
    private int counter;
    private int elapsedSeconds = 0;
    private GameLogic gameLogic;
    private String path;
    private Frame backgroundPanel;

    public ThrowingObject(String imagePath, JFrame frame, GameLogic logic, Frame background) {
        path = imagePath;
        gameLogic = logic;
        backgroundPanel = background;
        counter =0;
        this.image = new ImageIcon(imagePath).getImage();
        setOpaque(false);
        this.setVisible(false);
        x = (int)(Math.random() * (800)) + 500;
        if (x>=750) {
            speedX = -((int)(Math.random() * 6) + 1);
        } else {
            speedX = (int)(Math.random() * 6) + 1;
        }
        this.frame = frame;
        setLocation(x, y);
        this.frame.setBounds(0,0,1940,1280);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                onObjectClick();
            }
        });
    }

    private void onObjectClick() {
        Splatter splatter = new Splatter(path, x, y, gameLogic);
        splatter.setBounds(x, y, 180, 180);
        if (backgroundPanel.getPanel() != null) {
            backgroundPanel.getPanel().add(splatter);
            backgroundPanel.getPanel().repaint();
        }
        gameLogic.addToObjects(splatter);
        Container parent = getParent();
        if (parent != null) {
            gameLogic.removeObject(this);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (y + 180 < frame.getContentPane().getHeight()) {
            g.drawImage(image, 0, 0, 180, 180, this);
        }
    }

    public void updateValues() {
        elapsedSeconds ++;
        angle += 0.05;
        x += (int) speedX;
        y = (int) (1292 - 180 + Amplitude * Math.sin(angle));
        setLocation(x, y);
        if (angle >= 2 * Math.PI) {
            Container parent = getParent();
            if (parent != null) {
                gameLogic.addFail();
                gameLogic.removeObject(this);
            }
        } else {
            repaint();
        }
        if (elapsedSeconds == 2) {
            this.setVisible(true);
        }
    }
}