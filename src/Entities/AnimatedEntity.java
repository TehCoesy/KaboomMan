package Entities;

import java.awt.image.BufferedImage;
import java.util.List;
import Graphics.Sprite;

//TODO: Animation

/*
AnimatedEntity.java, a base class for all Enemies, Players and other non-static Objects
 */
public abstract class AnimatedEntity extends Entity{
    //ANIMATION
    protected int GLOBAL_TICKS = 0;
    private int ANIMATION_STEP, ANIMATION_TIME = 12;
    private int STEP_SIZE_S, STEP_SIZE_0, STEP_SIZE_1, STEP_SIZE_2, STEP_SIZE_3, STEP_SIZE_D;
    private int CURRENT_STEP = 4;

    //General Parameters
    protected int ORIENTATION = 0; // 0 = DOWN, 1 = UP, 2 = LEFT, 3 = RIGHT
    protected boolean MOVING_0, MOVING_1, MOVING_2, MOVING_3;

    List<Sprite> standingSprite = null;
    List<Sprite> downSprite = null;
    List<Sprite> leftSprite = null;
    List<Sprite> rightSprite = null;
    List<Sprite> UpSprite = null;
    List<Sprite> deadSprite = null;

    //Setting up Sprites
    public void setStandingSprite(List<Sprite> standingSprite) {
        this.standingSprite = standingSprite;
        STEP_SIZE_S = standingSprite.size();
    }
    public void setDownSprite(List<Sprite> downSprite) {
        this.downSprite = downSprite;
        this.STEP_SIZE_0 = downSprite.size();
    }
    public void setLeftSprite(List<Sprite> leftSprite) {
        this.leftSprite = leftSprite;
        this.STEP_SIZE_2 = leftSprite.size();
    }
    public void setRightSprite(List<Sprite> rightSprite) {
        this.rightSprite = rightSprite;
        this.STEP_SIZE_3 = rightSprite.size();
    }
    public void setUpSprite(List<Sprite> upSprite) {
        this.UpSprite = upSprite;
        this.STEP_SIZE_1 = upSprite.size();
    }
    public void setDeadSprite(List<Sprite> deadSprite) {
        this.deadSprite = deadSprite;
        this.STEP_SIZE_D = deadSprite.size();
    }

    public void tick() {
        if (done) {
            return;
        }
        GLOBAL_TICKS++;
        updateAnimation();

    }

    private void updateAnimation() {
        if (GLOBAL_TICKS % ANIMATION_TIME == 0) {
            if (ANIMATION_STEP >= CURRENT_STEP - 1) {
                checkDone();
                ANIMATION_STEP = 0;
            } else {
                ANIMATION_STEP++;
            }
        }
        if (GLOBAL_TICKS > 1000) {
            GLOBAL_TICKS = 0;
        }
    }

    private void checkDone() {
        if (ANIMATION_STEP >= CURRENT_STEP - 1 && isDead()) {
            done = true;
        }
    }

    private void resetAnimation() {
        this.ANIMATION_STEP = 0;
    }

    public BufferedImage getSprite() {
        if (done) {
            return null;
        }

        if (isDead()) {
            CURRENT_STEP = STEP_SIZE_D;
            if (!done) {
                return deadSprite.get(ANIMATION_STEP).getSprite();
            } else {
                return null;
            }
        }

        if (!MOVING_0 && !MOVING_1 && !MOVING_2 && !MOVING_3) {
            if (standingSprite != null) {
                CURRENT_STEP = STEP_SIZE_S;
                return standingSprite.get(ANIMATION_STEP).getSprite();
            }
        }

        switch (ORIENTATION) {
            case 0:
                CURRENT_STEP = STEP_SIZE_0;
                if (MOVING_0) {
                    return downSprite.get(ANIMATION_STEP).getSprite();
                }
                return downSprite.get(0).getSprite();
            case 1:
                CURRENT_STEP = STEP_SIZE_1;
                if (MOVING_1) {
                    return UpSprite.get(ANIMATION_STEP).getSprite();
                }
                return UpSprite.get(0).getSprite();
            case 2:
                CURRENT_STEP = STEP_SIZE_2;
                if (MOVING_2) {
                    return leftSprite.get(ANIMATION_STEP).getSprite();
                }
                return leftSprite.get(0).getSprite();
            case 3:
                CURRENT_STEP = STEP_SIZE_3;
                if (MOVING_3) {
                    return rightSprite.get(ANIMATION_STEP).getSprite();
                }
                return rightSprite.get(0).getSprite();
            default:
                return null;
        }
    }

    public void setAnimationTime(int ANIMATION_TIME) {
        this.ANIMATION_TIME = ANIMATION_TIME;
    }
}
