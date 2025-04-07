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

    public Image image1;
    public int x1 = 0;
    public int y1 = 0;
    public int mymouseClicked = 0;
    public int clearThrow =0;
    private int mouseX = 0;
    private int mouseY =0;
    private int deathbyswipecount = 0 ;

    public ThrowingObject(String imagePath, JFrame frame, GameLogic logic, Frame background) {
        path = imagePath;
        gameLogic = logic;
        backgroundPanel = background;
        counter =0;
        this.image = new ImageIcon(imagePath).getImage();
        this.image1 = new ImageIcon("images/AppleSplatter.png").getImage();
        addMouseListener(this);

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


//        addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent e) {
//                // onObjectClick();
//
//                this.image = this.image1;
//                mymouseClicked = 1;
//                clearThrow = 1;
//            }
//        });
    }

    private void onObjectClick() {
//        Splatter splatter = new Splatter(path, x, y, gameLogic);
//        splatter.setBounds(x, y, 180, 180);
//        if (backgroundPanel.getPanel() != null) {
//            backgroundPanel.getPanel().add(splatter);
//            backgroundPanel.getPanel().repaint();
//        }
//        gameLogic.addToObjects(splatter);
//        Container parent = getParent();
//        if (parent != null) {
//            gameLogic.removeObject(this);
//        }

//        this.image = this.image1;
//        mymouseClicked = 1;
//        clearThrow = 1;
//        repaint();
//

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (y + 180 < frame.getContentPane().getHeight()) {
            g.drawImage(image, 0, 0, 180, 180, this);
        }
    }

    public void updateValues() {
//        elapsedSeconds ++;
//        angle += 0.05;
//        x += (int) speedX;
//        y = (int) (1292 - 180 + Amplitude * Math.sin(angle));
//        setLocation(x, y);
//        if (angle >= 2 * Math.PI) {
//            Container parent = getParent();
//            if (parent != null) {
//                gameLogic.addFail();
//                gameLogic.removeObject(this);
//            }
//        } else {
//            repaint();
//        }
//        if (elapsedSeconds == 2) {
//            this.setVisible(true);
//        }


        elapsedSeconds++;
        // System.out.println("Frame y: " + y + "clearThrow " + clearThrow);
        // Remove the object from the panel if y exceeds 11000

        if (y > 1100+180  )  {
            this.setVisible(false);
            remove(this);

        } else   {
            this.setVisible(true);
            clearThrow = 0;
        }

        if (counter < 1) {
            if (mymouseClicked == 1)
            {
                // Fall straight down after clicking
                y = y+ 15; // falling speed
                setLocation(x, y);
            } else
            {
                angle += 0.05;
                x += (int) speedX;
                y = (int) (1292 - 180 + Amplitude * Math.sin(angle));
                setLocation(x, y);
                x1 = x;
                y1 = y;
                if (angle >= 2 * Math.PI) {
                    counter++;
                }
            }
            if (mymouseClicked == 1) {
                // Fall straight down after clicking
                y = y + 10; // falling speed
                setLocation(x, y);
            }
            repaint();
            if (elapsedSeconds == 1) {
                this.setVisible(true);
            }
        }
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        updateValues();
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        // only change image when mouseClicked anywhere in the frame no to check getX getY
        deathbyswipecount = 0;
        int hardMode =0;
        if (hardMode ==0) {
            this.image = this.image1;
            mymouseClicked = 1;
            clearThrow = 1;

        }else {
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



    @Override
    public void mouseEntered(MouseEvent e) {
        // Swipe Easy Mode with mouse hover
if (deathbyswipecount<4) {
    int clickX = e.getX();
    int clickY = e.getY();
    // Swipe mode too easy so changed frame size to smaller more targeted.
    // System.out.println("Mouse clicked at: " + clickX + ", " + clickY);
    int easylevel = 20;
    if (clickX >= 0 + easylevel && clickX <= 180 - easylevel && clickY >= 0 + easylevel && clickY <= 180 - easylevel) {
        this.image = this.image1;
        mymouseClicked = 1;
        clearThrow = 1;
        repaint();
        deathbyswipecount++;
    } else {
        clearThrow = 0;
    }
}
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        this.image = this.image1;
//        mymouseClicked = 1;
//        clearThrow = 1;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public int getClearThrow() {
        return clearThrow;
    }

}


