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
    private double rotation;

    public Splatter(String originalImagePath, GameLogic logic) {
        String splatterImagePath = "";
        gameLogic = logic;
        String impactSound = "";
        rotation = Math.random() * 6.28;
        //each fruit has a custom impact sound on top of the splatter sound
        if (originalImagePath.equals("images/Apple.png")) {
            splatterImagePath = "images/AppleSplatter.png";
            impactSound = "music/Impact-Apple.wav";
        } else if (originalImagePath.equals("images/Banana.png")) {
            splatterImagePath = "images/BananaSplatter.png";
            impactSound = "music/Impact-Banana.wav";
        } else if (originalImagePath.equals("images/Coconut.png")) {
            splatterImagePath = "images/CoconutSplatter.png";
            impactSound = "music/Impact-Coconut.wav";
        } else if (originalImagePath.equals("images/Pineapple.png")) {
            splatterImagePath = "images/PineappleSplatter.png";
            impactSound = "music/Impact-Pineapple.wav";
        } else if (originalImagePath.equals("images/Watermelon.png")) {
            splatterImagePath = "images/WatermelonSplatter.png";
            impactSound = "music/Impact-Watermelon.wav";
        }
        int soundRandom = (int) ((Math.random() * 3));
        String fruitSound = "music/Splatter-Small-1.wav";
        if (soundRandom == 0) {
            fruitSound = "music/Splatter-Small-2.wav";
        } else if (soundRandom == 1) {
            fruitSound = "music/Splatter-Medium-1.wav";
        } else if (soundRandom == 2) {
            fruitSound = "music/Splatter-Medium-2.wav";
        }
        gameLogic.playSound(impactSound);
        gameLogic.playSound(fruitSound);
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
            g2d.rotate(rotation, 200, 150);
            g2d.drawImage(image, 0, 0, 400, 300, null);
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
