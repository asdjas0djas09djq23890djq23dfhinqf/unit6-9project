import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class DisplayPanel extends JPanel {

    // add button as instance variable
    private JButton button;

    public DisplayPanel() {

        // create a new JButton component
        button = new JButton("Click me");

        // call add method (inherited from JPanel) to add
        // the JButton component to the DisplayPanel
        add(button);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.RED);
        g.drawString("This is the display panel!", 50, 30);

        // set location of where button gets drawn on the screen
        // (keep in mind +y is DOWN, so this appears UNDER the message)
        button.setLocation(50, 100);
    }
}