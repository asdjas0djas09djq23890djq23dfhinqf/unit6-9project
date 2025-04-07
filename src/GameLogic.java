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
        counterLabel = new JLabel("FAILS REMAINING: " + (3 - fails));
        counterLabel.setFont(new Font("Arial", Font.BOLD, 36));
        counterLabel.setForeground(Color.RED);
        counterLabel.setBounds(1400, 0, 200, 200);
        frame.getJFrame().add(counterLabel);
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
        int nextCooldown = (int) (Math.random() * 1000 + 1000);
        int amount = (int) (Math.random() * 4 + 2);
        if (moves >= 3 && moves < 10) {
            amount = (int) (Math.random() * 3 + 1);
            nextCooldown = (int) (Math.random() * 1500 + 1500);
            level = 2;
        } else if (moves < 3) {
            nextCooldown = 3000;
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
            throwObject();
        }
        moves++;
        throwFruit = new TimerTask() {
            public void run() {
                decideFruitAndThrow();
            }
        };
        throwTimer.schedule(throwFruit, nextCooldown);
    }

    public void throwObject() {
        SwingUtilities.invokeLater(() -> {
            String randomImage = FruitDataLoader.getRandomImagePath();
            JPanel object = new ThrowingObject(randomImage, frame.getJFrame(), this, frame);
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