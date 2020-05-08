import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    public Window(int width, int height, String title, Game game) {
        // frame of our window
        JFrame frame = new JFrame(title);

        // set window dimension to set size
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        // stop operations once frame is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        // sets the window location to middle of screen
        frame.setLocationRelativeTo(null);
        // adding the game to the frame
        frame.add(game);
        // makes our window visible
        frame.setVisible(true);
        // starting the game
        game.start();
    }

}
