import javax.swing.*;

public class NonFruit extends ThrowingObject {
    public NonFruit(String imagePath, JFrame frame, GameLogic logic, Frame background, String type) {
        super(imagePath, frame, logic, background, type);
    }

    @Override
    protected void playImpactSound() {
        switch (type) {
            case "Wrench":
                gameLogic.playSound("music/metal pipe falling sound effect.wav");
                break;
            case "Glass":
                gameLogic.playSound("music/GlassVaseSmash.wav");
                break;
            case "Duck":
                gameLogic.playSound("music/QuackSound.wav");
                break;
            case "Lawnmower":
                gameLogic.playSound("music/LawnmowerOnScreenSound.wav");
                break;
            case "Chainsaw":
                gameLogic.playSound("music/ChainsawOnScreenSound.wav");
                break;
            case "Fence":
                gameLogic.playSound("music/clong-1.wav");
                break;
            case "Car":
                gameLogic.playSound("music/CarBeep.wav");
                break;
            default:
                gameLogic.playSound("music/Metal-Impact.wav"); // fallback
        }
        gameLogic.playSound("music/Danger-Alert.wav"); // extra warning
    }

    @Override
    protected void onObjectClick() {
        super.onObjectClick();
        gameLogic.playSound("music/clong-1.wav");
    }
}