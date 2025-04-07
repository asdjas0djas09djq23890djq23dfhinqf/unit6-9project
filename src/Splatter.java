import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Splatter extends JPanel {
    private BufferedImage image;
    private Color overlayColor;
    private int x;
    private int y;
    private GameLogic gameLogic;
    private float opacity;

    public Splatter(String imagePath, int x, int y, GameLogic logic) {
        String splatterImagePath = "images/black-paint-splatter-png-2.png";
        this.x = x;
        this.y = y;
        opacity = 0.5f;
        gameLogic = logic;
        if (imagePath.equals("images/Apple.png")) {
            overlayColor = new Color(255, 0, 0);
        } else if (imagePath.equals("images/Banana.png")) {
            overlayColor = new Color(255, 255, 102);
        } else if (imagePath.equals("images/Coconut.png")) {
            overlayColor = new Color(51, 51, 0);
        } else if (imagePath.equals("images/Pineapple.png")) {
            overlayColor = new Color(255, 255, 153);
        } else if (imagePath.equals("images/Watermelon.png")) {
            overlayColor = new Color(76, 153, 0);
            }
        try {
            image = ImageIO.read(new File(splatterImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        if (image != null) {
            graphics.drawImage(image, x, y, 180, 180, this);
        }
        if (overlayColor != null) {
            graphics.setColor(overlayColor);
            graphics.setComposite(AlphaComposite.SrcOver.derive(opacity));
            graphics.fillRect(x, y, 180, 180);
        }
    }

    public void updateValues() {
        repaint();
        opacity -= 0.01f;
        System.out.println(opacity);
        if (opacity <= 0f) {
            gameLogic.removeObject(this);
        }
    }
}
