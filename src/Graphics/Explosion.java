package Graphics;

import Entities.FlameSegment;
import Entities.Statics.Brick;
import Entities.Statics.StaticEntity;
import Entities.Statics.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Explosion {
    List<FlameSegment> flameSegment = new ArrayList<>();
    List<StaticEntity> staticEntities = null;

    public boolean done = false;
    protected int posX, posY, BLOCK_SIZE, explosionSize;
    private int space_top, space_down, space_left, space_right;

    public Explosion(int x, int y, int BLOCK_SIZE, int explosionSize, List<StaticEntity> staticEntities) {
        this.posX = x; this.posY = y;
        this.BLOCK_SIZE = BLOCK_SIZE;
        this.explosionSize = explosionSize;
        this.staticEntities = staticEntities;
        this.space_down = explosionSize; this.space_top = explosionSize; this.space_left = explosionSize; this.space_right = explosionSize;
        createExplosion();
    }

    private void createExplosion() {
        findSpace();
        FlameSegment center = new FlameSegment(posX, posY);
        center.setDeadSprite(SpriteBuilder.getExplosionCenter());
        flameSegment.add(center);

        System.out.println(space_down + " " + space_top + " " + space_left + " " + space_right);

        for (int i = 1; i < explosionSize; i++) {

            if (i < space_top) {
                FlameSegment top = new FlameSegment(posX, posY - i);
                top.setDeadSprite(SpriteBuilder.getExplosionVertical());
                flameSegment.add(top);
            }

            if (i < space_down) {
                FlameSegment down = new FlameSegment(posX, posY + i);
                down.setDeadSprite(SpriteBuilder.getExplosionVertical());
                flameSegment.add(down);
            }

            if (i < space_left) {
                FlameSegment left = new FlameSegment(posX - i, posY);
                left.setDeadSprite(SpriteBuilder.getExplosionHorizontal());
                flameSegment.add(left);
            }

            if (i < space_right) {
                FlameSegment right = new FlameSegment(posX + i, posY);
                right.setDeadSprite(SpriteBuilder.getExplosionHorizontal());
                flameSegment.add(right);
            }
        }

        if (space_top > 0) {
            FlameSegment topLast = new FlameSegment(posX, posY - space_top);
            topLast.setDeadSprite(SpriteBuilder.getExplosionTop());
            flameSegment.add(topLast);
        }

        if (space_down > 0) {
            FlameSegment topDown = new FlameSegment(posX, posY + space_down);
            topDown.setDeadSprite(SpriteBuilder.getExplosionDown());
            flameSegment.add(topDown);
        }

        if (space_left > 0) {
            FlameSegment topLeft = new FlameSegment(posX - space_left, posY);
            topLeft.setDeadSprite(SpriteBuilder.getExplosionLeft());
            flameSegment.add(topLeft);
        }

        if (space_right > 0) {
            FlameSegment topRight = new FlameSegment(posX + space_right, posY);
            topRight.setDeadSprite(SpriteBuilder.getExplosionRight());
            flameSegment.add(topRight);
        }


        for (FlameSegment flame : flameSegment) {
            flame.setAnimationTime(4);
        }
    }

    public void tick() {
        for (FlameSegment flame : flameSegment) {
            flame.tick();
        }
        update();
    }

    private void update() {
        int counter = 0, n = flameSegment.size();
        for (FlameSegment flame : flameSegment) {
            if (flame.isDone()) {
                counter++;
            }
        }
        if (counter >= n) {
            done = true;
        }
    }

    public void drawExplosion(Graphics g) {
        for (FlameSegment s : flameSegment) {
            g.drawImage(s.getSprite(), s.getX() * BLOCK_SIZE, s.getY() * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, null);
        }
    }

    public List<FlameSegment> getSegments() {
        return this.flameSegment;
    }

    private void findSpace() {
        StaticEntity checkStatic;

        for (int i = 1; i <= explosionSize; i++) {
            if (space_top == explosionSize) {
                checkStatic = findStatic(posX, posY - i);
                if (checkStatic != null) {
                    if (checkStatic instanceof Wall) {
                        space_top = i - 1;
                    } else {
                        space_top = i;
                    }
                }
            }

            if (space_down == explosionSize) {
                checkStatic = findStatic(posX, posY + i);
                if (checkStatic != null) {
                    if (checkStatic instanceof Wall) {
                        space_down = i - 1;
                    } else {
                        space_down = i;
                    }
                }
            }

            if (space_left == explosionSize) {
                checkStatic = findStatic(posX - i, posY);
                if (checkStatic != null) {
                    if (checkStatic instanceof Wall) {
                        space_left = i - 1;
                    } else {
                        space_left = i;
                    }
                }
            }

            if (space_right == explosionSize) {
                checkStatic = findStatic(posX + i, posY);
                if (checkStatic != null) {
                    if (checkStatic instanceof Wall) {
                        space_right = i - 1;
                    } else {
                        space_right = i;
                    }
                }
            }
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
}
