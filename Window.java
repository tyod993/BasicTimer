
import javax.swing.*;

public class Window extends JFrame {

    public Window(int width, int height, String name, Timer timer) {
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.add(timer);
        timer.start();
    }
}
