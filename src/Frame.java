import javax.swing.JFrame;

public class Frame {

    public Frame() {
        // create a JFrame (a window) and with a particular text for the title bar
        JFrame frame = new JFrame("Drawing Game");

        // apply setting to that clicking X in top right of window will close window and end program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set frame to have 300 width, 200 height
        frame.setSize(300, 300);

        // auto-center the frame on the screen
        frame.setLocationRelativeTo(null);

        // display the frame (window)
        frame.setVisible(true);
        DisplayPanel panel = new DisplayPanel();

        // add it to the frame
        frame.add(panel);

        frame.setVisible(true);
    }
}