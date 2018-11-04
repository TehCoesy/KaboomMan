package Core;

import Container.MyFrame;
import Entities.Entity;
import Entities.Player;
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

    public static int BLOCK_SIZE = 50;
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

    //ENTITIES
    Player player = new Player();

    public void initialize() {
        initEntities();
    }

    private void initEntities() {

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

        g.drawImage(player.getSprite(),10,10,BLOCK_SIZE,BLOCK_SIZE, null);

        g.dispose();
        _strategy.show();
    }

    private void updateGame() {

    }

    private void getKey() {
        if (keyboard.up) {
            player.move(0);
        }
        if (keyboard.down) {
            player.move(1);
        }
        if (keyboard.left) {
            player.move(2);
        }
        if (keyboard.right) {
            player.move(3);
        }
        if (keyboard.space) {
        }
    }
}
