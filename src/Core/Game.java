package Core;

import Container.MyFrame;

import java.awt.*;
import java.awt.image.BufferStrategy;

//Governs and runs the Game
//Contain the gameLoop;

public class Game extends Canvas {
    Frame _frame;

    private boolean _running = true;

    public static final String TITLE = "KaBoomMan";

    public static int WIDTH = 400;
    public static int HEIGHT = 400;

    public Game(MyFrame frame) {
        this._frame = frame;
        _frame.setTitle(TITLE);
    }

    public void start() {
        _running = true;

        while(_running) {
            renderScreen();
        }
    }

    private void renderScreen() {
        BufferStrategy _strategy = this.getBufferStrategy();

        if (_strategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = _strategy.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);
        g.setColor(Color.GREEN);
        g.fillRect(0,0,WIDTH,HEIGHT);

        g.dispose();
        _strategy.show();
    }
}
