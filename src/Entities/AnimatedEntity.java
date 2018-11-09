package Entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import Graphics.Sprite;

//TODO: Animation

/*
AnimatedEntity.java, a base class for all Enemies, Players and other non-static Objects
 */
public abstract class AnimatedEntity extends Entity{
    //ANIMATION
    private int ticks = 0;
    protected int ANIMATION_STEP = 0;
    private int STEP_SIZE = 0;
    protected boolean ANIMATED_S = true, ANIMATED_0, ANIMATED_1, ANIMATED_2, ANIMATED_3; //S = Standing, 0 = Down, 1 = Up, 2 = Left, 3 = Right

    List<Sprite> standingSprite = new ArrayList<>();
    List<Sprite> leftSprite = null;
    List<Sprite> rightSprite = null;
    List<Sprite> UpSprite = null;
    List<Sprite> deadSprite = null;

    public BufferedImage getStandingSprite() {
        return standingSprite.get(ANIMATION_STEP).getSprite();
    }

    public void setStandingSprite(List<Sprite> standingSprite) {
        this.standingSprite = standingSprite;
        STEP_SIZE = standingSprite.size();
    }

    public void setLeftSprite(List<Sprite> leftSprite) {
        this.leftSprite = leftSprite;
    }

    public void setRightSprite(List<Sprite> rightSprite) {
        this.rightSprite = rightSprite;
    }

    public void setUpSprite(List<Sprite> upSprite) {
        this.UpSprite = upSprite;
    }

    public void setDeadSprite(List<Sprite> deadSprite) {
        this.deadSprite = deadSprite;
    }

    protected void stopAnimation() {
        this.ANIMATED_S = true;
        this.ANIMATED_0 = false;
        this.ANIMATED_1 = false;
        this.ANIMATED_2 = false;
        this.ANIMATED_3 = false;
    }
    protected void resetAnimation() {
        ticks = 0;
        ANIMATION_STEP = 0;
    }

    public void tick() {
        ticks++;
        if (ticks == 10) {
            if (ANIMATION_STEP == STEP_SIZE - 1) {
                ANIMATION_STEP = 0;
            } else {
                ANIMATION_STEP++;
            }
            ticks = 0;
        }
    }
}
