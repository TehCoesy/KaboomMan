package Core;

import Audio.AudioPlayer;
import Entities.*;
import Entities.Enemies.Enemy;
import Entities.Statics.PowerUp;
import Entities.Statics.StaticEntity;
import Graphics.*;
import IO.Keyboard;
import Level.LevelLoader;
import States.ApplicationSetting;
import States.GameSetting;
import States.PlayerState;
import Container.GameEntities;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    Keyboard keyboard;

    //GAME PARAMETERS
    public static int BLOCK_SIZE;

    //GAMEPLAY
    private boolean canPlaceBomb = true , gameOver, gameCompleted;
    int tickCounter = 0;
    private String current_State = new String("Initializing");

    //ENTITIES
    private PlayerState playerState = new PlayerState();
    private GameEntities gameEntities;
    private Player player;
    private GameOverseer overseer;
    private GameSetting gameSetting = new GameSetting();
    private ApplicationSetting applicationSetting = new ApplicationSetting();
    private Camera camera;
    private Renderer render;

    public Game(Keyboard key) {
        this.keyboard = key;
        setFocusable(true);
    }

    public void setAudio(AudioPlayer myAudio) {
        this.myAudio = myAudio;
    }

    public void newGame() {
        startGame();
        myAudio.playMusic();
    }

    public void clearGame() {

    }

    public void firstLevel() {
        try {
            _levelLoader.loadLevel("level1.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextLevel() {
        gameSetting.CURRENT_LEVEL++;
        try {
            switch (gameSetting.CURRENT_LEVEL) {
                case 0: _levelLoader.loadLevel("level1.txt"); break;
                case 1: _levelLoader.loadLevel("level2.txt"); break;
                case 2: _levelLoader.loadLevel("level3.txt"); break;
                case 4: this.gameCompleted = true; break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        initialize();
    }

    public void pause() {

    }
    public void unpause() {

    }

    public void startGame() {
        firstLevel();
        initialize();
    }

    public void initialize() {
        initEntities();


        this.camera = new Camera(gameEntities, gameSetting);
        this.render = new Renderer(gameEntities, camera, gameSetting, applicationSetting);
    }

    private void initEntities() {
        gameEntities = _levelLoader.getEntities();
        gameSetting = _levelLoader.getSettings();
        overseer = new GameOverseer();

        overseer.set(gameEntities, gameSetting, this);

        gameEntities.subscribeAll(overseer, gameSetting);

        this.BLOCK_SIZE = gameSetting.BLOCK_SIZE;
        this.gameSetting.GAME_SCREEN_OFFSET_Y = 50;
        player = gameEntities.player;
    }


    //Render the game
    public void renderScreen() {
        BufferStrategy _strategy = this.getBufferStrategy();

        if (_strategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = _strategy.getDrawGraphics();

        g.clearRect(0,0, WIDTH, HEIGHT);


        if (!gameCompleted) {
            render.renderGame(g);
            render.drawScoreBar(g, gameSetting.CURRENT_SCORE, gameSetting.CURRENT_LIVES);
        } else {
            render.endScreen(g);
        }


        if (gameOver) {
            render.gameOver(g);
        }

        g.dispose();
        _strategy.show();
    }


    public void updateGame() {
        if (gameOver || gameCompleted) {
            return;
        }

        overseer.update();
        getPowerUp();
        //player.setVelocity(playerState.PLAYER_SPEED);

        for (Bomb bomb : gameEntities.bombs) {
            bomb.update();
            if (bomb.isDead()) {
                gameEntities.explosions.add(new Explosion(bomb.getX(), bomb.getY(), BLOCK_SIZE, playerState.BOMB_POWER, gameEntities.staticEntities));
                myAudio.EXPLODE();
            }
        }

        for (Enemy enemy : gameEntities.enemies) {
            enemy.update();
            enemy.updateAI();
        }

        playAudio();
        myAudio.update();
        gameEntities.update();
        getKey();
    }

    private void getPowerUp() {
        Vector2i player = gameEntities.player.getRelativePosition();

        for (PowerUp powerUp : gameEntities.powerUps) {
            if (powerUp.getX() == player.getX() && powerUp.getY() == player.getY()) {
                String effect = powerUp.eat();
                if (effect == "BOMB_COUNT") {
                    playerState.BOMB_COUNT++;
                } else if (effect == "BOMB_SIZE") {
                    playerState.BOMB_POWER++;
                } else if (effect == "SPEED") {
                    this.player.addVelocity(1);
                }
            }
        }
    }

    public void tick() {
        if (tickCounter == 600) {
            tickCounter = 0; //Maximum 10 seconds
        }

        tickCounter++;

        player.tick();
        for (Bomb bomb : gameEntities.bombs) {
            bomb.tick();
        }
        for (Explosion explosion : gameEntities.explosions) {
            explosion.tick();
        }
        for (Enemy enemy : gameEntities.enemies) {
            enemy.tick();
        }
    }

    public void gameOver() {
        this.gameOver = true;
    }

    public void gameComplete() {
        this.gameCompleted = true;
    }

    private StaticEntity findStatic(int posX, int posY) {
        for (StaticEntity entity : gameEntities.staticEntities) {
            if (entity.getX() == posX && entity.getY() == posY) {
                return entity;
            }
        }
        return null;
    }

    private boolean findBomb(int posX, int posY) {
        for (Bomb bomb : gameEntities.bombs) {
            if (bomb.getX() == posX && bomb.getY() == posY) {
                return true;
            }
        }
        return false;
    }

    private void getKey() {
        if (keyboard.C_UP()) {
            player.moveUp();
            camera.moveUp();
            overseer.playerUp();
        } else if (!keyboard.C_UP()) {
            player.stopUp();
        }
        if (keyboard.C_DOWN()) {
            player.moveDown();
            camera.moveDown();
            overseer.playerDown();
        } else if (!keyboard.C_DOWN()) {
            player.stopDown();
        }
        if (keyboard.C_LEFT()) {
            player.moveLeft();
            camera.moveLeft();
            overseer.playerLeft();
        } else if (!keyboard.C_LEFT()) {
            player.stopLeft();
        }
        if (keyboard.C_RIGHT()) {
            player.moveRight();
            camera.moveRight();
            overseer.playerRight();
        } else if (!keyboard.C_RIGHT()) {
            player.stopRight();
        }

        if (keyboard.getSpace()) {
            placeBomb();
        }
    }

    public void earnPoints(int points) {
        this.gameSetting.CURRENT_SCORE += points;
    }

    private void placeBomb() {
        Vector2i position = player.getRelativePosition();

        if (gameEntities.bombs.size() >= playerState.BOMB_COUNT) {
            canPlaceBomb = false;
        } else {
            canPlaceBomb = true;
        }

        if (!findBomb(position.getX(), position.getY()) && canPlaceBomb) {
            Bomb bomb = new Bomb();
            bomb.setPosition(position.getX(),position.getY());
            gameEntities.bombs.add(bomb);
        }
    }

    private void playAudio() {
        if (tickCounter % 30 == 0 && player.moving()) {
            myAudio.PLAYER_WALK();
        }
    }

    public int getStateRequest() {
        return 2;
    }
}
