package Entities;

import Container.GameEntities;
import Core.GameOverseer;
import Core.Vector2i;
import States.ApplicationSetting;

public abstract class LocomotiveEntity extends AnimatedEntity {
    protected ApplicationSetting setting;
    protected GameOverseer overseer;

    //ENTITY PARAMETER
    protected int VEL = 1;
    protected int TOLERANCE = 10; // Collision tolerances
    protected boolean collide_top, collide_down, collide_left, collide_right;

    //GAME PARAMETER
    private int BLOCK_SIZE;

    public void setGame(GameOverseer gameOverseer, ApplicationSetting setting) {
        this.overseer = gameOverseer;
        this.setting = setting;
        this.BLOCK_SIZE = setting.BLOCK_SIZE;
        this.VEL = this.VEL * setting.SCALE;
    }

    //Collision

    private void moveEntity() {
        switch (ORIENTATION) {
            case 0: {
                if (!collide_down) {
                    posY += VEL;
                    if (collide_top) {
                        collide_top = false;
                    }
                }
                break;
            }
            case 1: {
                if (!collide_top) {
                    posY -= VEL;
                    if (collide_down) {
                        collide_down = false;
                    }
                }
                break;
            }
            case 2: {
                if (!collide_left) {
                    posX -= VEL;
                    if (collide_right) {
                        collide_right = false;
                    }
                }
                break;
            }
            case 3: {
                if (!collide_right) {
                    posX += VEL;
                    if (collide_left) {
                        collide_left = false;
                    }
                }
                break;
            }
        }
    }

    //Movement
    private void move(int orientation) {
        this.ORIENTATION = orientation;
        if (!isDead()) {
            if (overseer.requestMove(this)) {
                moveEntity();
            }
        }
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

    public void collide(int SIDE, boolean modifier) {
        switch (SIDE) {
            case 0: collide_down = modifier; break;
            case 1: collide_top = modifier; break;
            case 2: collide_left = modifier; break;
            case 3: collide_right = modifier; break;
        }
    }

    public boolean moving() {
        if (MOVING_0 || MOVING_1 || MOVING_2 || MOVING_3) {
            return true;
        }
        return false;
    }

    public void setTolerance(int tolerance) {
        this.TOLERANCE = tolerance;
    }
    public int getTolenrance() { return this.TOLERANCE; }

    public int getOrientation() { return this.ORIENTATION; }

    public int getVelocity() { return this.VEL; }

    public void setVelocity(int VEL) {
        this.VEL = VEL * setting.SCALE;
    }
}
