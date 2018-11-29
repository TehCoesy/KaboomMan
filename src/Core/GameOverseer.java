package Core;

import Container.GameEntities;
import Entities.*;
import Entities.Statics.*;
import Entities.Enemies.*;
import Graphics.Explosion;
import States.ApplicationSetting;

import java.util.List;

public class GameOverseer {
    private Game game;
    private GameEntities gameEntities;
    private ApplicationSetting settings;

    private int BLOCK_SIZE;

    public void set(GameEntities gameEntities, ApplicationSetting setting, Game game) {
        this.gameEntities = gameEntities;
        this.settings = setting;
        this.game = game;

        this.BLOCK_SIZE = setting.BLOCK_SIZE;
    }

    public void update() {
        getExplosionKills();
        if (gameEntities.player.isDone()) {
            game.gameOver();
        }
    }

    public void getExplosionKills() {
        for (Explosion explosion : gameEntities.explosions) {
            for (FlameSegment flame : explosion.getSegments()) {
                for (StaticEntity entity : gameEntities.staticEntities) {
                    if (flame.getX() == entity.getX() && flame.getY() == entity.getY()) {
                        entity.kill();
                    }
                }

                Vector2i playerPOS = gameEntities.player.getRelativePosition();
                if (flame.getX() == playerPOS.getX() && flame.getY() == playerPOS.getY()) {
                    gameEntities.player.kill();
                }

                for (Enemy enemy : gameEntities.enemies) {
                    Vector2i enemyPOS = enemy.getRelativePosition();
                    if (flame.getX() == enemyPOS.getX() && flame.getY() == enemyPOS.getY()) {
                        enemy.kill();
                    }
                }

                for (Bomb bomb : gameEntities.bombs) {
                    if (flame.getX() == bomb.getX() && flame.getY() == bomb.getY()) {
                        bomb.kill();
                    }
                }
            }
        }
    }

    public boolean requestMove(LocomotiveEntity requestEntity) {
        int VEL = requestEntity.getVelocity();
        int VELX = 0, VELY = 0;
        switch (requestEntity.getOrientation()) {
            case 0: VELY = VEL; break;
            case 1: VELY = -VEL; break;
            case 2: VELX = -VEL; break;
            case 3: VELX = VEL; break;
        }
        if (checkCollision(requestEntity.getX() + VELX, requestEntity.getY() + VELY, requestEntity.getTolenrance(), gameEntities.staticEntities)) {
            return false;
        }
        if (checkCollision(requestEntity.getX() + VELX, requestEntity.getY() + VELY, requestEntity.getTolenrance(), gameEntities.bombs)) {
            return false;
        }
        return true;
    }

    private <T extends Entity> boolean checkCollision(int posX, int posY, int TOL, List<T> collidableEntities) {
        for (T entity : collidableEntities) {
            if (!entity.isCollidable()) {
                continue;
            }

            if (posX + BLOCK_SIZE - TOL <= entity.getX() * BLOCK_SIZE || posX >= entity.getX() * BLOCK_SIZE + BLOCK_SIZE - TOL) {
                continue;
            }

            if (posY + BLOCK_SIZE - TOL <= entity.getY() * BLOCK_SIZE || posY >= entity.getY() * BLOCK_SIZE + BLOCK_SIZE - TOL) {
                continue;
            }

            return true;
        }
        return false;
    }

    public void playerDown() {
        handleBombCollision();
    }

    public void playerUp() {
        handleBombCollision();
    }

    public void playerLeft() {
        handleBombCollision();
    }

    public void playerRight() {
        handleBombCollision();
    }

    private void handleBombCollision() {
        Vector2i playerPOS = gameEntities.player.getPosition();
        int posX = playerPOS.getX(), posY = playerPOS.getY(), TOL = gameEntities.player.getTolenrance();

        for (Bomb entity : gameEntities.bombs) {
            boolean playerMoved = false;

            if (posX > entity.positionMemory.getX() * BLOCK_SIZE + BLOCK_SIZE) {
                playerMoved = true;
            }

            if (posX + BLOCK_SIZE < entity.positionMemory.getX() * BLOCK_SIZE) {
                playerMoved = true;
            }

            if (posY > entity.positionMemory.getY() * BLOCK_SIZE + BLOCK_SIZE) {
                playerMoved = true;
            }

            if (posY + BLOCK_SIZE < entity.positionMemory.getY() * BLOCK_SIZE) {
                playerMoved = true;
            }

            if (playerMoved) {
                entity.playerMove();
            }
        }
    }
}
