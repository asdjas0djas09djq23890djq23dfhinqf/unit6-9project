import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;
import javax.swing.*;
import java.util.ArrayList;

public class GameLogic {
    private int moves;
    private Timer throwTimer;
    private Timer updateFruitTimer;
    private Frame frame;
    private TimerTask throwFruit;
    private TimerTask update;
    private ArrayList<JPanel> objects;
    private int fails;
    private JLabel counterLabel;
    private String[][] options;

    public GameLogic() {
        moves = 0;
        fails = 0;
        objects = new ArrayList<>();
        frame = new Frame();
        throwTimer = new Timer();
        updateFruitTimer = new Timer();
        throwFruit = new TimerTask() {
            public void run() {
                decideFruitAndThrow();
            }
        };
        update = new TimerTask() {
            public void run() {
                update();
            }
        };
        throwTimer.schedule(throwFruit, 1000);
        updateFruitTimer.schedule(update, 30, 30);

        //supposed to be the fail counter but it doesnt show correctly
        counterLabel = new JLabel("FAILS REMAINING: " + (3 - fails));
        counterLabel.setFont(new Font("Arial", Font.BOLD, 36));
        counterLabel.setForeground(Color.RED);
        counterLabel.setBounds(1400, 0, 200, 200);
        frame.getJFrame().add(counterLabel);

        initializeOptions();
    }

    //2d array of of item's images and their type (used for connecting images to sounds)
    public void initializeOptions() {
        options = new String[2][12];
        options[0][0] = "images/Apple.png";
        options[0][1] = "images/Banana.png";
        options[0][2] = "images/Coconut.png";
        options[0][3] = "images/Pineapple.png";
        options[0][4] = "images/Watermelon.png";
        options[0][5] = "images/RubberDuck.png";
        options[0][6] = "images/Wrench.png";
        options[0][7] = "images/GlassVase.png";
        options[0][8] = "images/Fence.png";
        options[0][9] = "images/Car.png";
        options[0][10] = "images/Chainsaw.png";
        options[0][11] = "images/Lawnmower.png";
        options[1][0] = "Fruit";
        options[1][1] = "Fruit";
        options[1][2] = "Fruit";
        options[1][3] = "Fruit";
        options[1][4] = "Fruit";
        options[1][5] = "Duck";
        options[1][6] = "Wrench";
        options[1][7] = "Glass";
        options[1][8] = "Fence";
        options[1][9] = "Car";
        options[1][10] = "Chainsaw";
        options[1][11] = "Lawnmower";
    }

    public void addFail() {
        fails++;
        if (fails == 4) {
            lose();
        }
    }

    public void lose() {
        update = null;
        throwFruit = null;
    }

    public void addToObjects(JPanel input) {
        objects.add(input);
    }

    public void removeObject(JPanel object) {
        if (frame.getPanel() != null) {
            frame.getPanel().remove(object);
            frame.getPanel().revalidate();
            frame.getPanel().repaint();
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i) == object) {
                    objects.remove(i);
                }
            }
        }
    }

    public void decideFruitAndThrow() {
        int level = 3;
        int nextCooldown = (int) (Math.random() * 750 + 750);
        int amount = (int) (Math.random() * 4 + 2);
        if (moves >= 3 && moves < 10) {
            amount = (int) (Math.random() * 3 + 1);
            nextCooldown = (int) (Math.random() * 1000 + 1000);
            level = 2;
        } else if (moves < 3) {
            nextCooldown = 2000;
            amount = 1;
            level = 1;
        }
        throwSequence(nextCooldown, amount, level);
    }

    public void throwSequence(int nextCooldown, int amount, int level) {
        for (int i = 0; i < amount; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            throwObject(level);
        }
        moves++;
        throwFruit = new TimerTask() {
            public void run() {
                decideFruitAndThrow();
            }
        };
        throwTimer.schedule(throwFruit, nextCooldown);
    }

    public void throwObject(int level) {
        SwingUtilities.invokeLater(() -> {
            String image = "";
            int number = 0;
            if (level == 1) {
                number = (int) (Math.random() * 5);
            } else if (level == 2) {
                number = (int) (Math.random() * 9);
            } else if (level == 3) {
                number = (int) (Math.random() * 12);
            }
            image = options[0][number];
            JPanel object = new ThrowingObject(image, frame.getJFrame(), this, frame, options[1][number]);
            objects.add(object);
            object.setBounds(0, 0, 180, 180);
            if (frame.getPanel() != null) {
                frame.getPanel().add(object);
                frame.getPanel().repaint();
            }
        });
    }

    public void update() {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof ThrowingObject) {
                ThrowingObject objectTemp = (ThrowingObject) objects.get(i);
                objectTemp.updateValues();
                objectTemp = null;
            } else if (objects.get(i) instanceof Splatter) {
                Splatter objectTemp = (Splatter) objects.get(i);
                objectTemp.updateValues();
                objectTemp = null;
            }
        }
    }

    public static void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
}