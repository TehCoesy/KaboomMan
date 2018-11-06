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

/*
FOR ENTITY DESIGN Consult Entity.java
 */
public class Game extends Canvas {
    Frame _frame;

    //IO
    Keyboard keyboard = new Keyboard();

    //GAME PARAMETERS
    private boolean _running = true;
    private static final int SCALE = 3;

    public int posX = 0;

    public static final String TITLE = "KaBoomMan";

    private static final double TARGET_FRAME = 60.0;

    public static final int BLOCK_SIZE = 50;
    public static final int GAME_SIZE = 15;
    public static final int WIDTH = GAME_SIZE * BLOCK_SIZE;
    public static final int HEIGHT = GAME_SIZE * BLOCK_SIZE;

    //GAMEPLAY
    private Entity[] gameEntities;
    private List<Sprite> staticSprite = new ArrayList<>();

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
        staticSprite.add(new Sprite(SpriteSheet.getSpriteImage("Data/Sprite/wall.png")));
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
                getKey();
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

        g.clearRect(0,0,WIDTH,HEIGHT);

        drawBackground(g);

        g.drawImage(staticSprite.get(0).getSprite(),4 * BLOCK_SIZE, 4 * BLOCK_SIZE, BLOCK_SIZE,BLOCK_SIZE,null);
        g.drawImage(player.getSprite(),player.getX() * BLOCK_SIZE,player.getY() * BLOCK_SIZE,BLOCK_SIZE,BLOCK_SIZE, null);

        g.dispose();
        _strategy.show();
    }

    private void updateGame() {

    }

    private void getKey() {
        boolean up = false, down = false, left = false, right = false;
        if (keyboard.up) {
            if (!up) {
                up = true;
            }

            if (up) {
                player.move(0,GAME_SIZE);
                if (keyboard.up) {
                    up = false;
                }
            }
        }
        if (keyboard.down) {
            player.move(1,GAME_SIZE);
        }
        if (keyboard.left) {
            player.move(2,GAME_SIZE);
        }
        if (keyboard.right) {
            player.move(3,GAME_SIZE);
        }
        if (keyboard.space) {
        }
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0,0,WIDTH,HEIGHT);
    }
}
