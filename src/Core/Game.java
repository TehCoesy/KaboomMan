package Core;

import Container.MyFrame;

import java.awt.*;
import java.awt.image.BufferStrategy;

//Governs and runs the Game
//Contain the gameLoop;

public class Game extends Canvas {
    Frame _frame;

    //GAME PARAMETERS
    private boolean _running = true;
    private boolean _square = true;

    public static final String TITLE = "KaBoomMan";

    private static final double TARGET_FRAME = 60.0;

    public static int WIDTH = 400;
    public static int HEIGHT = 400;

    public Game(MyFrame frame) {
        this._frame = frame;
        _frame.setTitle(TITLE);
    }

    public void start() {
        _running = true;

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / TARGET_FRAME; //60 Frames per second

        double delta = 0;

        requestFocus();

        while(_running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                _square = !_square;
                renderScreen(_square);
                delta--;
            }
        }
    }

    private void runLoop() {

    }

    private void renderScreen(boolean _square) {
        BufferStrategy _strategy = this.getBufferStrategy();

        if (_strategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = _strategy.getDrawGraphics();

        if (_square) {
            g.setColor(Color.BLUE);
            g.fillRect(0,0,WIDTH,HEIGHT);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(0,0,WIDTH,HEIGHT);
        }

        g.dispose();
        _strategy.show();
    }

    private void updateGame() {

    }
}
