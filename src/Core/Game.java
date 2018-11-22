package Core;

import Audio.AudioPlayer;
import Audio.SoundEffect;
import Container.MyFrame;
import Entities.*;
import Entities.Enemies.Ballom;
import Entities.Enemies.Enemy;
import Entities.Statics.StaticEntity;
import Graphics.*;
import IO.Keyboard;
import IO.Mouse;
import Level.LevelLoader;
import States.ApplicationSetting;
import States.PlayerState;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.util.ArrayList;

//Governs and runs the Game
//Contain the gameLoop;

/*
FOR ENTITY DESIGN Consult Entity.java
 */

public class Game extends Canvas {
    private AudioPlayer myAudio;

    //LEVEL
    LevelLoader _levelLoader = new LevelLoader();
    //IO
    Keyboard keyboard = new Keyboard();

    //GAME PARAMETERS
    private boolean _stop;
    private static final int SCALE = 3;

    public static final int BLOCK_SIZE = ApplicationSetting.BLOCK_SIZE;
    public static final int GAME_SIZE = ApplicationSetting.GAME_SIZE;
    public static final int WIDTH = ApplicationSetting.WIDTH;
    public static final int HEIGHT = ApplicationSetting.HEIGHT;

    private Renderer render = new Renderer(this);

    //GAMEPLAY
    int tickCounter = 0;
    public PlayerState playerState = new PlayerState();

    //ENTITIES
    public Player player = new Player(this);
    public List<StaticEntity> staticEntities;
    public List<Enemy> enemies = new ArrayList<>();
    public List<Explosion> explosions = new ArrayList<>();
    public List<Bomb> bombs = new ArrayList<>();

    public Game() {
        setFocusable(true);
        addKeyListener(keyboard);
        initialize();
    }

    public void setAudio(AudioPlayer myAudio) {
        this.myAudio = myAudio;
        this.myAudio.playMusic();
    }

    public void initialize() {
        _levelLoader.loadLevel("Data/Levels/level1.txt",GAME_SIZE);
        initEntities();
        enemies.add(new Ballom(4 * BLOCK_SIZE,4 * BLOCK_SIZE));
    }

    private void initEntities() {
        player.setPosition(BLOCK_SIZE,BLOCK_SIZE);
        staticEntities = _levelLoader.getStatics();
    }


    //Render the game
    public void renderScreen() {
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


    public void updateGame() {

        getKills();

        for (Bomb bomb : bombs) {
            bomb.update();
            if (bomb.isDead()) {
                explosions.add(new Explosion(bomb.getX(), bomb.getY(), BLOCK_SIZE, playerState.BOMB_POWER, this.staticEntities));
                myAudio.EXPLODE();
            }
        }

        playAudio();
        myAudio.update();
        collectEntities();
        getKey();
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

    public void tick() {
        if (tickCounter == 600) {
            tickCounter = 0; //Maximum 10 seconds
        }

        tickCounter++;

        player.tick();
        for (Bomb bomb : bombs) {
            bomb.tick();
        }
        for (Explosion explosion : explosions) {
            explosion.tick();
        }
        for (Enemy enemy : enemies) {
            enemy.tick();
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

    private void playAudio() {
        if (tickCounter % 30 == 0 && player.moving()) {
            myAudio.PLAYER_WALK();
        }
    }

    public void stop() {
        this._stop = true;
    }

    public int getBlockSize() {
        return this.BLOCK_SIZE;
    }

    public int getStateRequest() {
        return 2;
    }
}
