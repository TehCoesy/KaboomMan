package Core;

import Container.MyFrame;
import Entities.Entity;
import Graphics.*;
import IO.Keyboard;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

//Governs and runs the Game
//Contain the gameLoop;

public class Game extends Canvas {
    Frame _frame;

    //IO
    Keyboard keyboard = new Keyboard();

    //GAME PARAMETERS
    private boolean _running = true;
    private static final int SCALE = 3;

    public static final String TITLE = "KaBoomMan";

    private static final double TARGET_FRAME = 60.0;

    public static int WIDTH = 400;
    public static int HEIGHT = 400;

    //GAMEPLAY
    private Entity[] gameEntities;
    private List<Sprite> playerSprite = new ArrayList<>();

    public Game(MyFrame frame) {
        this._frame = frame;
        _frame.setTitle(TITLE);

        addKeyListener(keyboard);
        initialize();
    }

    public void initialize() {
        initEntities();
    }

    private void initEntities() {
        playerSprite.add(new Sprite());
        playerSprite.get(0).setSprite(SpriteSheet.getSpriteImage("Data/Sprite/player_down.png"));
        playerSprite.add(new Sprite());
        playerSprite.get(1).setSprite(SpriteSheet.getSpriteImage("Data/Sprite/player_up.png"));
        playerSprite.add(new Sprite());
        playerSprite.get(2).setSprite(SpriteSheet.getSpriteImage("Data/Sprite/player_left.png"));
        playerSprite.add(new Sprite());
        playerSprite.get(3).setSprite(SpriteSheet.getSpriteImage("Data/Sprite/player_up.png"));
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

            getKey();

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

        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 2; k++) {
                g.drawImage(playerSprite.get(i * 2 + k).getSprite(), 20 + 100 * i, 20 + 100 * k, 100,100, null);
            }
        }

        g.dispose();
        _strategy.show();
    }

    private void updateGame() {

    }

    private void getKey() {
        if (keyboard.up) {
        }
        if (keyboard.down) {
        }
        if (keyboard.left) {
        }
        if (keyboard.right) {
        }
        if (keyboard.space) {
        }
    }
}
