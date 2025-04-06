import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FruitDataLoader {
    private static ArrayList<String> imagePaths = new ArrayList<>();

    static {
        try {
            Scanner scanner = new Scanner(new File("fruits.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("//");
                if(parts.length == 2) {
                    imagePaths.add(parts[1].trim());
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println("Error loading fruit data: " + e.getMessage());
            imagePaths.add("images/Apple.png");
            imagePaths.add("images/Banana.png");
            imagePaths.add("images/Watermelon.png");
            imagePaths.add("images/Coconut.png");
            imagePaths.add("images/Pineapple.png");
        }
    }

    public static String getRandomImagePath() {
        if(imagePaths.isEmpty()) return "images/Apple.png";
        return imagePaths.get((int)(Math.random() * imagePaths.size()));
    }
}