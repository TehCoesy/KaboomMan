package Graphics;

//TODO: Transfer rendering from Game.java

import Core.Game;
import Core.Vector2i;
import Entities.Bomb;
import Entities.Enemies.Enemy;
import Entities.Statics.*;
import Container.GameEntities;
import States.ApplicationSetting;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Renderer {
    int BLOCK_SIZE;
    private GameEntities gameEntities;
    private Camera camera;

    private Vector2i translation;

    private int WIDTH ,HEIGHT;
    //Sprites
    private List<Sprite> staticSprite = new ArrayList<>();
    private BufferedImage grassSprite = SpriteBuilder.getSpriteImage("Data/Sprite/grass.png");

    public Renderer(GameEntities gameEntities, Camera camera, ApplicationSetting setting) {
        this.translation = new Vector2i();
        this.gameEntities = gameEntities;
        this.camera = camera;

        this.WIDTH = setting.BLOCK_WIDTH;
        this.HEIGHT = setting.BLOCK_HEIGHT;

        BLOCK_SIZE = ApplicationSetting.BLOCK_SIZE;
        initializeStatics();
    }

    public void renderGame(Graphics g) {
        if (camera != null) {
            this.translation = camera.getTranslation();
        } else {
            this.translation.set(0,0);
        }
        g.clearRect(0,0, WIDTH, HEIGHT);

        drawBackground(g);
        renderStatics(g);
        renderAnimated(g);
        renderBombs(g);
        renderExplosion(g);
        renderEnemies(g);
        renderPortal(g);
        g.drawImage(gameEntities.player.getSprite(),gameEntities.player.getX() + translation.getX(),gameEntities.player.getY() + translation.getY(),BLOCK_SIZE,BLOCK_SIZE,null);
    }

    private void initializeStatics() {
        staticSprite.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/wall.png")));
        staticSprite.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/brick.png")));
    }

    private void drawBackground(Graphics g) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int k = 0; k < WIDTH; k++) {
                g.drawImage(grassSprite, k * BLOCK_SIZE + translation.getX(), i * BLOCK_SIZE + translation.getY(), BLOCK_SIZE, BLOCK_SIZE, null);
            }
        }
    }

    private void renderStatics(Graphics g) {
        int n = gameEntities.staticEntities.size();

        for (int i = 0; i < n; i++) {
            StaticEntity entity = gameEntities.staticEntities.get(i);
            if (entity instanceof Wall) {
                g.drawImage(staticSprite.get(0).getSprite(),entity.getX() * BLOCK_SIZE + translation.getX(), entity.getY() * BLOCK_SIZE + translation.getY(), BLOCK_SIZE,BLOCK_SIZE,null);
            } else if (entity instanceof Brick) {
                g.drawImage(staticSprite.get(1).getSprite(),entity.getX() * BLOCK_SIZE + translation.getX(), entity.getY() * BLOCK_SIZE + translation.getY(), BLOCK_SIZE,BLOCK_SIZE,null);
            }
        }
    }

    private void renderAnimated(Graphics g) {
        int n = gameEntities.enemies.size();

        for (int i = 0; i < n; i++) {
            g.drawImage(gameEntities.enemies.get(i).getSprite(), gameEntities.enemies.get(i).getX() + translation.getX(),gameEntities.enemies.get(i).getY() + translation.getY(), BLOCK_SIZE, BLOCK_SIZE, null);
        }
    }

    private void renderBombs(Graphics g) {
        int n = gameEntities.bombs.size();

        for (int i = 0; i < n; i++) {
            Bomb bomb = gameEntities.bombs.get(i);
            g.drawImage(bomb.getSprite(),bomb.getX() * BLOCK_SIZE + translation.getX(), bomb.getY() * BLOCK_SIZE + translation.getY(), BLOCK_SIZE,BLOCK_SIZE, null);
        }
    }

    private void renderExplosion(Graphics g) {
        int n = gameEntities.explosions.size();

        for (int i = 0; i < n; i++) {
            Explosion explosion = gameEntities.explosions.get(i);
            explosion.drawExplosion(g, translation);
        }
    }

    private void renderEnemies(Graphics g) {
        for (Enemy enemy : gameEntities.enemies) {
            g.drawImage(enemy.getSprite(), enemy.getX() + translation.getX(), enemy.getY() + translation.getY(), BLOCK_SIZE, BLOCK_SIZE,null);
        }
    }

    private void renderPortal(Graphics g) {
        Portal entity = gameEntities.portal;

        if (entity == null) {
            return;
        }

        g.drawImage(entity.getSprite(), entity.getX() * BLOCK_SIZE + translation.getX(), entity.getY() * BLOCK_SIZE + translation.getY(), BLOCK_SIZE, BLOCK_SIZE, null);
    }

    private void renderPowerUps(Graphics g) {
        for (PowerUp entity : gameEntities.powerUps) {
            g.drawImage(entity.getSprite(), entity.getX() * BLOCK_SIZE + translation.getX(), entity.getY() * BLOCK_SIZE + translation.getY(), BLOCK_SIZE, BLOCK_SIZE, null);
        }
    }

    private void checkOutOfBounds() {

    }

    public void pauseScreen() {

    }
}
