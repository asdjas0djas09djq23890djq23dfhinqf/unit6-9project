import javax.swing.*;

public class Fruit extends ThrowingObject {

    public Fruit(String imagePath, JFrame frame, GameLogic logic, Frame background, String type) {
        super(imagePath, frame, logic, background, type);
        this.Amplitude = (int)(Math.random() * 201) + 1000;
        this.angleIncrement = 0.04;
    }

    @Override
    protected void playFruitSounds() {
        switch (type) {
            case "Apple":
                gameLogic.playSound("music/Impact-Apple.wav");
                break;
            case "Banana":
                gameLogic.playSound("music/Impact-Banana.wav");
                break;
            case "Pineapple":
                gameLogic.playSound("music/Impact-Pineapple.wav");
                break;
            case "Watermelon":
                gameLogic.playSound("music/Impact-Watermelon.wav");
                break;
            case "Coconut":
                gameLogic.playSound("music/Impact-Coconut.wav");
                break;
            default:
                super.playFruitSounds();
        }
    }

    @Override
    protected void onObjectClick() {
        super.onObjectClick();
        gameLogic.playSound("music/Juicy-Splash.wav");
    }
}
