import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameLogic extends Frame {
    private int moves;
    Timer timer;
    Frame frame;
    TimerTask throwFruit;

    public GameLogic() {
        moves = 0;

        frame = new Frame();
        timer = new Timer();


        throwFruit = new TimerTask() {
            public void run() {
                decideFruitAndThrow();
            }
        };

        timer.schedule(throwFruit, 10);
    }

//    private static Clip loadSound(String soundFilePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(soundFilePath));
//        Clip clip = AudioSystem.getClip();
//        clip.open(audioIn);
//        return clip;
//    }



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
        timer.schedule(throwFruit, nextCooldown);
    }

    public void throwObject() {
        ThrowingObject object = new ThrowingObject("images/Apple.png", frame);
        object.setSize(frame.getWidth(), frame.getHeight());
        getPanel().add(object);
        getPanel().repaint();
        getPanel().revalidate();
    }
}
