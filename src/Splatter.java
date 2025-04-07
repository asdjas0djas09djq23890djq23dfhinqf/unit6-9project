import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Splatter extends JPanel {
    private BufferedImage image;
    private GameLogic gameLogic;
    private float opacity = 0.75f;

    public Splatter(String originalImagePath, GameLogic logic) {
        String splatterImagePath = "images/black-paint-splatter-png-2.png";
        gameLogic = logic;
        if (originalImagePath.equals("images/Apple.png")) {
            splatterImagePath = "images/black-paint-splatter-png-2.png";
        } else if (originalImagePath.equals("images/Banana.png")) {
            splatterImagePath = "images/black-paint-splatter-png-2.png";
        } else if (originalImagePath.equals("images/Coconut.png")) {
            splatterImagePath = "images/black-paint-splatter-png-2.png";
        } else if (originalImagePath.equals("images/Pineapple.png")) {
            splatterImagePath = "images/black-paint-splatter-png-2.png";
        } else if (originalImagePath.equals("images/Watermelon.png")) {
            splatterImagePath = "images/black-paint-splatter-png-2.png";
            }
        setOpaque(false);
        try {
            image = ImageIO.read(new File(splatterImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g2d.drawImage(image, 0, 0, 230, 180, null);
            g2d.dispose();
        }
    }

    public void updateValues() {
        opacity = Math.max(0f, opacity - 0.005f);
        repaint();
        System.out.println(opacity);
        if (opacity <= 0f) {
            gameLogic.removeObject(this);
        }
    }
}
