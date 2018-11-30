package Graphics;

import Core.Vector2i;
import Entities.Bomb;
import Entities.Enemies.Enemy;
import Entities.Statics.*;
import Container.GameEntities;
import States.ApplicationSetting;
import States.GameSetting;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Renderer {
    private GameEntities gameEntities;
    private GameSetting gameSetting;
    private ApplicationSetting applicationSetting;
    private Camera camera;

    //Rendering Parameters
    private int BLOCK_SIZE;
    private Vector2i translation;
    private int WIDTH ,HEIGHT;
    private int WINDOW_WIDTH, WINDOW_HEIGHT;
    private int modifierX, modifierY;

    //Ready-made Sprites
    private List<Sprite> staticSprite = new ArrayList<>();
    private BufferedImage grassSprite = SpriteBuilder.getSpriteImage("Data/Sprite/grass.png");
    private List<Sprite> powerUpSprite = new ArrayList<>();

    public Renderer(GameEntities gameEntities, Camera camera, GameSetting gameSetting, ApplicationSetting applicationSetting) {
        this.translation = new Vector2i();
        this.gameEntities = gameEntities;
        this.camera = camera;
        this.gameSetting = gameSetting;

        if (applicationSetting == null) {
            this.applicationSetting = new ApplicationSetting();
        } else {
            this.applicationSetting = applicationSetting;
        }


        this.WINDOW_WIDTH = applicationSetting.WINDOW_WIDTH;
        this.WINDOW_HEIGHT = applicationSetting.WINDOW_HEIGHT;

        this.WIDTH = gameSetting.BLOCK_WIDTH;
        this.HEIGHT = gameSetting.BLOCK_HEIGHT;
        this.BLOCK_SIZE = gameSetting.BLOCK_SIZE;

        initializeSprites();
    }

    public void renderGame(Graphics g) {
        if (camera != null) {
            this.translation = camera.getTranslation();
        } else {
            this.translation.set(0,0);
        }
        g.clearRect(0,0, WIDTH, HEIGHT);

        modifierX = translation.getX() + gameSetting.GAME_SCREEN_OFFSET_X;
        modifierY = translation.getY() + gameSetting.GAME_SCREEN_OFFSET_Y;

        drawBackground(g);
        renderStatics(g);
        renderBombs(g);
        renderExplosion(g);
        renderEnemies(g);
        renderPortal(g);
        renderPowerUps(g);

        g.drawImage(gameEntities.player.getSprite(),gameEntities.player.getX() + modifierX,gameEntities.player.getY() + modifierY,BLOCK_SIZE,BLOCK_SIZE,null);
    }

    private void initializeSprites() {
        staticSprite.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/wall.png")));
        staticSprite.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/brick.png")));

        powerUpSprite.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/powerup_speed.png")));
        powerUpSprite.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/powerup_flames.png")));
        powerUpSprite.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/powerup_bombs.png")));
    }

    private void drawBackground(Graphics g) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int k = 0; k < WIDTH; k++) {
                g.drawImage(grassSprite, k * BLOCK_SIZE + modifierX, i * BLOCK_SIZE + modifierY, BLOCK_SIZE, BLOCK_SIZE, null);
            }
        }
    }

    private void renderStatics(Graphics g) {
        int n = gameEntities.staticEntities.size();

        for (int i = 0; i < n; i++) {
            StaticEntity entity = gameEntities.staticEntities.get(i);
            if (entity instanceof Wall) {
                g.drawImage(staticSprite.get(0).getSprite(),entity.getX() * BLOCK_SIZE + modifierX, entity.getY() * BLOCK_SIZE + modifierY, BLOCK_SIZE,BLOCK_SIZE,null);
            } else if (entity instanceof Brick) {
                g.drawImage(staticSprite.get(1).getSprite(),entity.getX() * BLOCK_SIZE + modifierX, entity.getY() * BLOCK_SIZE + modifierY, BLOCK_SIZE,BLOCK_SIZE,null);
            }
        }
    }

    private void renderBombs(Graphics g) {
        int n = gameEntities.bombs.size();

        for (int i = 0; i < n; i++) {
            Bomb bomb = gameEntities.bombs.get(i);
            g.drawImage(bomb.getSprite(),bomb.getX() * BLOCK_SIZE + modifierX, bomb.getY() * BLOCK_SIZE + modifierY, BLOCK_SIZE,BLOCK_SIZE, null);
        }
    }

    private void renderExplosion(Graphics g) {
        int n = gameEntities.explosions.size();

        for (int i = 0; i < n; i++) {
            Explosion explosion = gameEntities.explosions.get(i);
            explosion.drawExplosion(g, modifierX, modifierY);
        }
    }

    private void renderEnemies(Graphics g) {
        for (Enemy enemy : gameEntities.enemies) {
            g.drawImage(enemy.getSprite(), enemy.getX() + modifierX, enemy.getY() + modifierY, BLOCK_SIZE, BLOCK_SIZE,null);
        }
    }

    private void renderPortal(Graphics g) {
        Portal entity = gameEntities.portal;

        if (entity == null) {
            return;
        }

        g.drawImage(entity.getSprite(), entity.getX() * BLOCK_SIZE + modifierX, entity.getY() * BLOCK_SIZE + modifierY, BLOCK_SIZE, BLOCK_SIZE, null);
    }

    private void renderPowerUps(Graphics g) {
        for (PowerUp entity : gameEntities.powerUps) {
            if (entity.isDead()) {
                if (entity.getType() == "BOMB_COUNT") {
                    g.drawImage(powerUpSprite.get(2).getSprite(), entity.getX() * BLOCK_SIZE + modifierX, entity.getY() * BLOCK_SIZE + modifierY, BLOCK_SIZE, BLOCK_SIZE, null);
                } else if (entity.getType() == "BOMB_SIZE") {
                    g.drawImage(powerUpSprite.get(1).getSprite(), entity.getX() * BLOCK_SIZE + modifierX, entity.getY() * BLOCK_SIZE + modifierY, BLOCK_SIZE, BLOCK_SIZE, null);
                } else if (entity.getType() == "SPEED") {
                    g.drawImage(powerUpSprite.get(0).getSprite(), entity.getX() * BLOCK_SIZE + modifierX, entity.getY() * BLOCK_SIZE + modifierY, BLOCK_SIZE, BLOCK_SIZE, null);
                }
            } else {
                g.drawImage(staticSprite.get(1).getSprite(), entity.getX() * BLOCK_SIZE + modifierX, entity.getY() * BLOCK_SIZE + modifierY, BLOCK_SIZE, BLOCK_SIZE, null);
            }
        }
    }

    public void gameOver(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g2d.fillRect(0,235, 1000, 80);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g2d.setColor(Color.red);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        g2d.drawString("You Died", 380,300);
    }

    public void endScreen(Graphics g) {
        g.clearRect(0,0, 1000, 1000);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000, 1000);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        g2d.drawString("You Have Completed the Game!", 55,300);
        g2d.dispose();
    }

    public void drawScoreBar(Graphics g, int score, int lives) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, WINDOW_WIDTH, 50);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g2d.drawString("Score: " + score, 40, 30);
        g2d.drawString("Lives: " + lives, 640, 30);
    }

    private void checkOutOfBounds() {

    }

    public void pauseScreen() {

    }
}
