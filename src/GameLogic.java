import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.util.ArrayList;

public class GameLogic {
    private int moves;
    private Timer throwTimer;
    private Timer updateFruitTimer;
    private Frame frame;
    private TimerTask throwFruit;
    private TimerTask updateFruit;
    private ArrayList<JPanel> objects;

    public GameLogic() {
        moves = 0;
        objects = new ArrayList<>();
        frame = new Frame();
        throwTimer = new Timer();
        updateFruitTimer = new Timer();
        throwFruit = new TimerTask() {
            public void run() {
                decideFruitAndThrow();
            }
        };
        updateFruit = new TimerTask() {
            public void run() {
                updateFruit();
            }
        };
        throwTimer.schedule(throwFruit, 1000, 500);
        updateFruitTimer.schedule(updateFruit, 30, 30);
    }

    public void removeObject(ThrowingObject object) {
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
        int nextCooldown = (int) (Math.random() * 1500 + 1500);
        int amount = (int) (Math.random() * 4 + 2);
        if (moves >= 3 && moves < 10) {
            amount = (int) (Math.random() * 3 + 1);
            nextCooldown = (int) (Math.random() * 2000 + 2000);
            level = 2;
        } else if (moves < 3) {
            nextCooldown = 5000;
            amount = 1;
            level = 1;
        }
        throwSequence(nextCooldown, amount, level);
    }

    public void throwSequence(int nextCooldown, int amount, int level) {
        for (int i = 0; i < amount; i++) {
            throwObject();
        }
    }

    public void throwObject() {
        SwingUtilities.invokeLater(() -> {
            String randomImage = FruitDataLoader.getRandomImagePath();
            JPanel object = new ThrowingObject(randomImage, frame.getFrame());
            objects.add(object);
            object.setBounds(0, 0, 180, 180);
            if (frame.getPanel() != null) {
                frame.getPanel().add(object);
                frame.getPanel().repaint();
            }
        });
    }

    public void updateFruit() {
        for (int i = 0; i < objects.size(); i++) {
            ThrowingObject objectTemp = (ThrowingObject) objects.get(i);
            objectTemp.updateValues();
            objectTemp = null;
        }
    }
}