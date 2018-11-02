package Core;

import Container.MyFrame;
import Entities.Entity;
import Graphics.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

//Governs and runs the Game
//Contain the gameLoop;

public class Game extends Canvas {
    Frame _frame;

    //GAME PARAMETERS
    private boolean _running = true;
    private static final int SCALE = 3;

    public static final String TITLE = "KaBoomMan";

    private static final double TARGET_FRAME = 60.0;

    public static int WIDTH = 400;
    public static int HEIGHT = 400;

    //GAMEPLAY
    private Entity[] gameEntities;
    private Sprite[] playerSprite = new Sprite[1];

    public Game(MyFrame frame) {
        this._frame = frame;
        _frame.setTitle(TITLE);

        initialize();
    }

    public void initialize() {
        initEntities();
    }

    private void initEntities() {
        playerSprite[0] = new Sprite();
        playerSprite[0].setSprite(SpriteSheet.getSpriteImage("Data/Sprite/player_down.png"));
    }

    public void start() {
        _running = true;

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / TARGET_FRAME; //60 Frames per second

        double delta = 0;

        requestFocus();
        renderScreen();
        while(_running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                renderScreen();
                delta--;
            }
        }
    }

    //Leave alone for now
    private void runLoop() {

    }

    //Render the game
    private void renderScreen() {
        BufferStrategy _strategy = this.getBufferStrategy();

        if (_strategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = _strategy.getDrawGraphics();

        g.drawImage(playerSprite[0].getSprite(), 20, 20, 100,100, null);

        g.dispose();
        _strategy.show();
    }

    private void updateGame() {

    }
}
