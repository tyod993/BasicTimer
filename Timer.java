
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Timer extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    private boolean isRunning = false;

    public long startTime = System.currentTimeMillis();

    public int width = 600, height = 200;

    private Thread thread;

    private int timerLengthMin = 1;

    public Timer() {

        Window window = new Window(width, height, "Timer", this);

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            isRunning = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        long timeElapsed = System.currentTimeMillis() - startTime;
        long elapsedSeconds = timeElapsed / 1000;
        long secondsDisplay = elapsedSeconds % 60;
        long elapsedMinutes = elapsedSeconds / 60;
        int fontSize = 100;

        g.setColor(Color.WHITE);
        if (elapsedMinutes >= timerLengthMin){
            g.setColor(Color.RED);
        }
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
        g.drawString(elapsedMinutes + ":" + secondsDisplay, (width / 2) - fontSize, height / 2);
        g.dispose();
        bs.show();

    }

    public void run() {
        while (isRunning) {
            if (isRunning) {
                render();
            }
        }
        stop();
    }

    public static void main(String args[]) {

        Timer timer = new Timer();

    }
}