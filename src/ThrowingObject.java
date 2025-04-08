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
    private double Amplitude = (int)(Math.random() * 201) + 800;
    private double angle;
    private int counter;
    private int elapsedSeconds = 0;
    private GameLogic gameLogic;
    private String path;
    private Frame backgroundPanel;
    private double angleIncrement = (Math.random() * 0.02 + 0.04);
    private String type;

    public int x1 = 0;
    public int y1 = 0;
    public int mymouseClicked = 0;
    public int clearThrow =0;
    private int mouseX = 0;
    private int mouseY =0;
    private int deathbyswipecount = 0 ;

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
            speedX = -((int)(Math.random() * 10) + 1);
        } else {
            speedX = (int)(Math.random() * 10) + 1;
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

    //detects object breaking, creates a Splatter object and deletes the object
    private void onObjectClick() {
        Splatter splatter = new Splatter(path, gameLogic);
        splatter.setBounds(x - 75, y - 50, 400, 400);
        if (backgroundPanel.getPanel() != null) {
            backgroundPanel.getPanel().add(splatter);
            backgroundPanel.getPanel().repaint();
        }
        gameLogic.addToObjects(splatter);
        if (type.equals("GlassVase")) {
            gameLogic.playSound("music/GlassVaseSmash.wav");
        } else if (type.equals("Wrench")) {
            gameLogic.playSound("music/metal pipe falling sound effect.wav");
        }
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

            //gives different objects different sounds when they spawn in
            if (elapsedSeconds == (int) (1240 * angleIncrement)) {
                if (type.equals("Fruit") || type.equals("Glass") || type.equals("Fence")) {
                    gameLogic.playSound("music/Throw-fruit.wav");
                } else if (type.equals("Duck")) {
                    gameLogic.playSound("music/QuackSound.wav");
                } else if (type.equals("Car")) {
                    gameLogic.playSound("music/CarBeep.wav");
                } else if (type.equals("Wrench")) {
                    gameLogic.playSound("music/clong-1.wav");
                }
                this.setVisible(true);
            }

            //for chainsaw and lawnmower, when they are on the screen they play this sound the whole time until they are deleted
            if (!(type.equals("Fruit")) && elapsedSeconds % 5 == 0 && elapsedSeconds >= (int) (1240 * angleIncrement)) {
                if (type.equals("Chainsaw")) {
                    gameLogic.playSound("music/ChainsawOnScreenSound.wav");
                } else if (type.equals("Lawnmower")) {
                    gameLogic.playSound("music/LawnmowerOnScreenSound.wav");
                }
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

   /* public void mouseClicked(MouseEvent e) {
        // only change image when mouseClicked anywhere in the frame no to check getX getY
        deathbyswipecount = 0;
        int hardMode = 0;
        if (hardMode == 0) {
            this.image = this.image1;
            mymouseClicked = 1;
            clearThrow = 1;

        } else {
            int clickX = e.getX();
            int clickY = e.getY();
            int easylevel = 5;
            if (clickX >= 0 + easylevel && clickX <= 180 - easylevel && clickY >= 0 + easylevel && clickY <= 180 - easylevel) {
                this.image = this.image1;
                mymouseClicked = 1;
                clearThrow = 1;
            }
        }
        repaint();
    }
*/
    // public void mouseEntered(MouseEvent e) {
    //         // Swipe Easy Mode with mouse hover
    // if (deathbyswipecount<4) {
    //     int clickX = e.getX();
    //     int clickY = e.getY();
    //     // Swipe mode too easy so changed frame size to smaller more targeted.
    //     // System.out.println("Mouse clicked at: " + clickX + ", " + clickY);
    //     int easylevel = 20;
    //     if (clickX >= 0 + easylevel && clickX <= 180 - easylevel && clickY >= 0 + easylevel && clickY <= 180 - easylevel) {
    //         this.image = this.image1;
    //         mymouseClicked = 1;
    //         clearThrow = 1;
    //         repaint();
    //         deathbyswipecount++;
    //     } else {
    //         clearThrow = 0;
    //     }
    // }
    //     }
    //
    //     @Override
    //     public void mousePressed(MouseEvent e) {
    // //        this.image = this.image1;
    // //        mymouseClicked = 1;
    // //        clearThrow = 1;
    //
    //     }
    //
    //     @Override
    //     public void mouseReleased(MouseEvent e) {
    //     }
    //
    //     @Override
    //     public void mouseExited(MouseEvent e) {
    //     }
    //
    //     public int getClearThrow() {
    //         return clearThrow;
    //     }
}