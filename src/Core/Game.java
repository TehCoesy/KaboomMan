package Core;

import Container.MyFrame;
import Entities.*;
import Entities.Statics.StaticEntity;
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

    private Renderer render = new Renderer(this);

    //GAMEPLAY


    //ENTITIES
    public Player player = new Player(this);
    public List<StaticEntity> staticEntities;
    public List<LocomotiveEntity> enemies = new ArrayList<>();
    public List<Explosion> explosions = new ArrayList<>();
    public List<Bomb> bombs = new ArrayList<>();

    public Game(MyFrame frame) {
        this._frame = frame;
        _frame.setTitle(TITLE);

        addKeyListener(keyboard);
        initialize();
    }

    private void initialize() {
        _levelLoader.loadLevel("Data/Levels/level1.txt",GAME_SIZE);
        initEntities();
    }

    private void initEntities() {
        player.setPosition(BLOCK_SIZE,BLOCK_SIZE);
        staticEntities = _levelLoader.getStatics();
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
                tick(); // 1/60s
                updateGame();
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

        render.renderGame(g);

        g.dispose();
        _strategy.show();
    }


    private void updateGame() {
        getKills();

        for (Bomb bomb : bombs) {
            bomb.update();
            if (bomb.isDead()) {
                explosions.add(new Explosion(bomb.getX(), bomb.getY(), BLOCK_SIZE, 3, this.staticEntities));
            }
        }


        collectEntities();
    }

    private void getKills() {
        for (Explosion explosion : explosions) {
            for (FlameSegment flame : explosion.getSegments()) {
                StaticEntity entity = null;
                entity = findStatic(flame.getX(),flame.getY());
                if (entity != null) {
                    entity.kill();
                }

                for (Bomb bomb : bombs) {
                    if (bomb.getX() == flame.getX() && bomb.getY() == flame.getY()) {
                        bomb.kill();
                    }
                }
            }
        }
    }

    private void collectEntities() {
        List<StaticEntity> removeStatic = new ArrayList<>();
        for (StaticEntity staticEntity : staticEntities) {
            if (staticEntity.isDone()) {
                removeStatic.add(staticEntity);
            }
        }

        staticEntities.removeAll(removeStatic);

        List<Bomb> removeBombs = new ArrayList<>();
        for (Bomb bomb : bombs) {
            if (bomb.isDead()) {
                removeBombs.add(bomb);
            }
        }

        bombs.removeAll(removeBombs);

        List<Explosion> removeExplosion = new ArrayList<>();
        for (Explosion explosion : explosions) {
            if (explosion.done) {
                removeExplosion.add(explosion);
            }
        }

        explosions.removeAll(removeExplosion);
    }

    private void tick() {
        player.tick();
        for (Bomb bomb : bombs) {
            bomb.tick();
        }
        for (Explosion explosion : explosions) {
            explosion.tick();
        }
    }

    private StaticEntity findStatic(int posX, int posY) {
        for (StaticEntity entity : staticEntities) {
            if (entity.getX() == posX && entity.getY() == posY) {
                return entity;
            }
        }
        return null;
    }

    private void getKey() {
        if (keyboard.C_UP()) {
            player.moveUp();
        } else if (!keyboard.C_UP()) {
            player.stopUp();
        }
        if (keyboard.C_DOWN()) {
            player.moveDown();
        } else if (!keyboard.C_DOWN()) {
            player.stopDown();
        }
        if (keyboard.C_LEFT()) {
            player.moveLeft();
        } else if (!keyboard.C_LEFT()) {
            player.stopLeft();
        }
        if (keyboard.C_RIGHT()) {
            player.moveRight();
        } else if (!keyboard.C_RIGHT()) {
            player.stopRight();
        }

        if (keyboard.getSpace()) {
            Bomb bomb = new Bomb();
            Vector2i position = player.getRelativePosition();
            bomb.setPosition(position.getX(),position.getY());
            bombs.add(bomb);
        }
    }

    public int getBlockSize() {
        return this.BLOCK_SIZE;
    }
}
