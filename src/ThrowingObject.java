import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

class ThrowingObject extends JPanel implements ActionListener, MouseListener {

    private Image image;
    private int x;
    private int y;
    private double speedX;
    private JFrame frame;
    double Amplitude = (int)(Math.random() * 201) + 800;
    private double angle;
    private int counter;
    private int elapsedSeconds = 0;
    protected GameLogic gameLogic;
    private String path;
    private Frame backgroundPanel;
    double angleIncrement = (Math.random() * 0.02 + 0.04);
    protected String type;
    public int x1 = 0;
    public int y1 = 0;
    public int clearThrow =0;
    private int totalFruitsSliced =0;

    public ThrowingObject(String imagePath, JFrame frame, GameLogic logic, Frame background, String type) {
        path = imagePath;
        gameLogic = logic;
        backgroundPanel = background;
        counter =0;
        this.image = new ImageIcon(imagePath).getImage();
        addMouseListener(this);
        this.type = type;
        setOpaque(false);
        this.setVisible(false);
        x = (int)(Math.random() * (800)) + 500;
        if (x>=750) {
            speedX = -((int)(Math.random() * 4) + 1);
        } else {
            speedX = (int)(Math.random() * 4) + 1;
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

    protected void playImpactSound() {
        gameLogic.playSound("music/Default-Impact.wav");
        System.out.println("Object type: " + this.getClass().getName());  // Check the actual class name
        if (this instanceof Fruit) {
            playFruitSounds();
            gameLogic.sliceFruit();
        } else if (this instanceof NonFruit) {
            playNonFruitSounds();
            gameLogic.sliceFruit();
        }
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    protected void playFruitSounds() {
    }

    protected void playNonFruitSounds() {

    }


    //detects object breaking, creates a Splatter object and deletes the object
    protected void onObjectClick() {
        Splatter splatter = new Splatter(path, gameLogic);
        splatter.setBounds(x - 75, y - 50, 400, 400);
        if (backgroundPanel.getPanel() != null) {
            totalFruitsSliced++;
            backgroundPanel.getPanel().add(splatter);
            backgroundPanel.getPanel().repaint();
        }
        gameLogic.addToObjects(splatter);
        playImpactSound();
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
        elapsedSeconds++;
        if (y > 1100) {
            this.setVisible(false);
            remove(this);
        } else {
            this.setVisible(true);
            clearThrow = 0;
        }
        if (counter < 1) {
            {
                angle += angleIncrement;
                x += (int) speedX;
                y = (int) (1292 - 180 + Amplitude * Math.sin(angle));
                setLocation(x, y);
                x1 = x;
                y1 = y;
                if (angle >= 2 * Math.PI) {
                    counter++;
                }
            }
            repaint();
            if (elapsedSeconds == (int) (1240 * angleIncrement)) {
                gameLogic.playSound("music/Throw-fruit.wav");
            }
        }
    }




    @Override
    public void actionPerformed(ActionEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}