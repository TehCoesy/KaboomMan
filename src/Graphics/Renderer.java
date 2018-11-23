package Graphics;

//TODO: Transfer rendering from Game.java

import Core.Game;
import Entities.Bomb;
import Entities.Enemies.Enemy;
import Entities.Statics.Brick;
import Entities.Statics.Wall;
import Container.GameEntities;
import States.ApplicationSetting;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Renderer {
    int BLOCK_SIZE;
    private GameEntities gameEntities;

    private int WIDTH = ApplicationSetting.WIDTH, HEIGHT = ApplicationSetting.HEIGHT;
    //Sprites
    private List<Sprite> staticSprite = new ArrayList<>();

    public Renderer(GameEntities gameEntities) {
        this.gameEntities = gameEntities;
        BLOCK_SIZE = ApplicationSetting.BLOCK_SIZE;
        initializeStatics();
    }

    public void renderGame(Graphics g) {
        g.clearRect(0,0, WIDTH, HEIGHT);

        drawBackground(g);
        renderStatics(g);
        renderAnimated(g);
        renderBombs(g);
        renderExplosion(g);
        renderEnemies(g);
        g.drawImage(gameEntities.player.getSprite(),gameEntities.player.getX(),gameEntities.player.getY(),BLOCK_SIZE,BLOCK_SIZE,null);
    }

    private void initializeStatics() {
        staticSprite.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/wall.png")));
        staticSprite.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/brick.png")));
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0,0,WIDTH,HEIGHT);
    }

    private void renderStatics(Graphics g) {
        int n = gameEntities.staticEntities.size();

        for (int i = 0; i < n; i++) {
            if (gameEntities.staticEntities.get(i) instanceof Wall) {
                g.drawImage(staticSprite.get(0).getSprite(),gameEntities.staticEntities.get(i).getX() * BLOCK_SIZE, gameEntities.staticEntities.get(i).getY() * BLOCK_SIZE, BLOCK_SIZE,BLOCK_SIZE,null);
            } else if (gameEntities.staticEntities.get(i) instanceof Brick) {
                g.drawImage(staticSprite.get(1).getSprite(),gameEntities.staticEntities.get(i).getX() * BLOCK_SIZE, gameEntities.staticEntities.get(i).getY() * BLOCK_SIZE, BLOCK_SIZE,BLOCK_SIZE,null);
            }
        }
    }

    private void renderAnimated(Graphics g) {
        int n = gameEntities.enemies.size();

        for (int i = 0; i < n; i++) {
            g.drawImage(gameEntities.enemies.get(i).getSprite(), gameEntities.enemies.get(i).getX(),gameEntities.enemies.get(i).getY(), BLOCK_SIZE, BLOCK_SIZE, null);
        }
    }

    private void renderBombs(Graphics g) {
        int n = gameEntities.bombs.size();

        for (int i = 0; i < n; i++) {
            Bomb bomb = gameEntities.bombs.get(i);
            g.drawImage(bomb.getSprite(),bomb.getX() * BLOCK_SIZE, bomb.getY() * BLOCK_SIZE, BLOCK_SIZE,BLOCK_SIZE, null);
        }
    }

    private void renderExplosion(Graphics g) {
        int n = gameEntities.explosions.size();

        for (int i = 0; i < n; i++) {
            Explosion explosion = gameEntities.explosions.get(i);
            explosion.drawExplosion(g);
        }
    }

    private void renderEnemies(Graphics g) {
        for (Enemy enemy : gameEntities.enemies) {
            g.drawImage(enemy.getSprite(), enemy.getX(), enemy.getY(), BLOCK_SIZE, BLOCK_SIZE,null);
        }
    }
    public void pauseScreen() {

    }
}
