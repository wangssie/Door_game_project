import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    private static final int WIDTH = 640, HEIGHT = WIDTH/12*9;
    private Thread thread;
    private boolean running;

    public Game() {
        new Window(WIDTH, HEIGHT, "Trial", this);
        this.running = false;
    }

    // start the game
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
        this.run();
    }

    // stop the game
    public synchronized void stop() {
        try {
            // stops the thread
            thread.join();
            running = false;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    /* Game loop */
    public void run() {
        // origin of time of run
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = Math.pow(10, 9) / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            // current time
            long now = System.nanoTime();

            delta += (now - lastTime) / ns; // ????????????
            lastTime = now;

            while (delta>=1) {
                tick();
                delta--;
            }

            if (running) {
                render();
            }
            // increase frame by one
            frames++;

            // if a second has passed
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                // record how many frames were rendered in the second
                System.out.println("FPS: "+ frames);
                // restart frame back to 0
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            // creates 3 buffers
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.green);
        g.fillRect(0,0,WIDTH, HEIGHT);
        g.dispose();
        bs.show();
    }

    /* engine of the game - starts it */
    public static void main(String[] args) {
        new Game().run();

    }

}
