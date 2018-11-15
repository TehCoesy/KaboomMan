package Entities;

import Core.Game;
import Core.Vector2i;
import Entities.Statics.StaticEntity;

import java.util.List;

public abstract class LocomotiveEntity extends AnimatedEntity {
    protected Game _game;

    //ENTITY PARAMETER
    protected int VEL = 4;
    protected final int TOLERANCE = 10; // Collision tolerances
    protected boolean collide_top, collide_down, collide_left, collide_right;

    //GAME PARAMETER
    private int BLOCK_SIZE;

    protected void setGame(Game game) {
        this._game = game;
        this.BLOCK_SIZE = game.getBlockSize();
    }

    //Collision

    private void moveEntity(List<StaticEntity> staticEntities) {
        switch (ORIENTATION) {
            case 0: {
                if (canMove(posX, posY + VEL,staticEntities)) {
                    posY += VEL;
                    if (collide_top) {
                        collide_top = false;
                    }
                } else {
                    collide_down = true;
                }
                break;
            }
            case 1: {
                if (canMove(posX, posY - VEL,staticEntities)) {
                    posY -= VEL;
                    if (collide_down) {
                        collide_down = false;
                    }
                } else {
                    collide_top = true;
                }
                break;
            }
            case 2: {
                if (canMove(posX - VEL, posY,staticEntities)) {
                    posX -= VEL;
                    if (collide_right) {
                        collide_right = false;
                    }
                } else {
                    collide_left = true;
                }
                break;
            }
            case 3: {
                if (canMove(posX + VEL, posY,staticEntities)) {
                    posX += VEL;
                    if (collide_left) {
                        collide_left = false;
                    }
                } else {
                    collide_right = true;
                }
                break;
            }
        }
    }

    private boolean canMove(int posX, int posY, List<StaticEntity> staticEntities) {
        int n = staticEntities.size();
        for (int i = 0; i < n; i++) {
            StaticEntity element = staticEntities.get(i);
            if (posX + BLOCK_SIZE - TOLERANCE <= element.posX * 65 || posX >= element.posX * 65 + BLOCK_SIZE - TOLERANCE) {
                continue;
            }
            if (posY + BLOCK_SIZE - TOLERANCE <= element.posY * 65 || posY >= element.posY * 65 + BLOCK_SIZE - TOLERANCE) {
                continue;
            }
            return false;
        }
        return true;
    }

    //Movement
    private void move(int orientation) {
        this.ORIENTATION = orientation;
        moveEntity(_game.staticEntities);
    }

    public Vector2i getRelativePosition() {
        int gapX = this.posX % BLOCK_SIZE;
        int gapY = this.posY % BLOCK_SIZE;

        if (gapX > BLOCK_SIZE / 2) {
            gapX = BLOCK_SIZE - gapX;
            gapX = 0 - gapX;

        }

        if (gapY > BLOCK_SIZE / 2) {
            gapY = BLOCK_SIZE - gapY;
            gapY = 0 - gapY;

        }

        return new Vector2i((posX - gapX) / BLOCK_SIZE, (posY - gapY) / BLOCK_SIZE);
    }

    //Controllers for continuous movement and Animation
    public void moveDown() {
        this.MOVING_0 = true;
        move(0);
    }
    public void moveUp() {
        this.MOVING_1 = true;
        move(1);
    }
    public void moveLeft() {
        this.MOVING_2 = true;
        move(2);
    }
    public void moveRight() {
        this.MOVING_3 = true;
        move(3);
    }
    public void stopDown() {
        this.MOVING_0 = false;
    }
    public void stopUp() {
        this.MOVING_1 = false;
    }
    public void stopLeft() {
        this.MOVING_2 = false;
    }
    public void stopRight() {
        this.MOVING_3 = false;
    }
}
