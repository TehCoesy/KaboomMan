package Core;

import Container.MyFrame;
import Entities.Entity;
import Entities.Player;
import Entities.StaticEntity;
import Entities.Wall;
import Graphics.*;
import IO.Keyboard;
import Level.LevelLoader;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.util.ArrayList;

//Governs and runs the Game
//Contain the gameLoop;

/*
FOR ENTITY DESIGN Consult Entity.java
 */

//TODO: Upgrade Entity system

public class Game extends Canvas {
    Frame _frame;

    //LEVEL
    LevelLoader _levelLoader = new LevelLoader();
    //IO
    Keyboard keyboard = new Keyboard();

    //GAME PARAMETERS
    private boolean _running = true;
    private static final int SCALE = 3;

    public static final String TITLE = "KaBoomMan";

    private static final double TARGET_FRAME = 60.0;

    public static final int BLOCK_SIZE = 65;
    public static final int GAME_SIZE = 15;
    public static final int WIDTH = GAME_SIZE * BLOCK_SIZE;
    public static final int HEIGHT = GAME_SIZE * BLOCK_SIZE;

    //GAMEPLAY
    private List<StaticEntity> staticEntities;
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
        _levelLoader.loadLevel("Data/Levels/level1.txt",GAME_SIZE);
        initEntities();
    }

    private void initEntities() {
        player.setPosition(1,1);
        staticEntities = _levelLoader.getStatics();
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

        renderStaticEntities(g);


        g.drawImage(staticSprite.get(0).getSprite(),4 * BLOCK_SIZE, 4 * BLOCK_SIZE, BLOCK_SIZE,BLOCK_SIZE,null);
        g.drawImage(player.getSprite(),player.getX(),player.getY(),BLOCK_SIZE,BLOCK_SIZE, null);

        g.dispose();
        _strategy.show();
    }

    private void updateGame() {

    }

    private void getKey() {
        boolean up = false, down = false, left = false, right = false;
        if (keyboard.C_UP()) {
            player.move(0,GAME_SIZE);
        }
        if (keyboard.C_DOWN()) {
            player.move(1,GAME_SIZE);
        }
        if (keyboard.C_LEFT()) {
            player.move(2,GAME_SIZE);
        }
        if (keyboard.C_RIGHT()) {
            player.move(3,GAME_SIZE);
        }
        if (keyboard.getSpace()) {
        }
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0,0,WIDTH,HEIGHT);
    }

    private void renderStaticEntities(Graphics g) {
        int n = staticEntities.size();

        for (int i = 0; i < n; i++) {
            if (staticEntities.get(i) instanceof Wall) {
                g.drawImage(staticSprite.get(0).getSprite(),staticEntities.get(i).getX() * BLOCK_SIZE, staticEntities.get(i).getY() * BLOCK_SIZE, BLOCK_SIZE,BLOCK_SIZE,null);
            }
        }
    }
}
