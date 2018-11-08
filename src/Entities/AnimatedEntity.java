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
    private int ANIMATION_STEP = 0;

    List<Sprite> standingSprite = new ArrayList<>();

    public BufferedImage getStandingSprite() {
        return standingSprite.get(ANIMATION_STEP).getSprite();
    }

    public void setStandingSprite(List<Sprite> standingSprite) {
        this.standingSprite = standingSprite;
    }

    public void tick() {
        ticks++;
        if (ticks == 15) {
            if (ANIMATION_STEP == 2) {
                ANIMATION_STEP = 0;
            } else {
                ANIMATION_STEP++;
            }
            ticks = 0;
        }
    }
}
