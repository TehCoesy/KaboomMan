package Entities;

import Core.Game;
import Graphics.*;

import java.awt.image.BufferedImage;

public class Player extends LocomotiveEntity {
    public boolean movingDown = true, movingUp = true, movingLeft = true, movingRight = true;

    public Player(Game game) {
        setGame(game);
        this.setStandingSprite(SpriteBuilder.getPlayerSprite0());
        this.setUpSprite(SpriteBuilder.getPlayerSprite1());
        this.setLeftSprite(SpriteBuilder.getPlayerSprite2());
        this.setRightSprite(SpriteBuilder.getPlayerSprite3());
        this.setDeadSprite(SpriteBuilder.getPlayerSprite4());
    }

    @Override
    public void update() {

    }

    @Override
    public BufferedImage getSprite() {
        switch (ORIENTATION) {
            case 0:
                if (movingDown) {
                    return standingSprite.get(ANIMATION_STEP).getSprite();
                }
                //resetAnimation();
                return standingSprite.get(0).getSprite();
            case 1:
                if (movingUp) {
                    return UpSprite.get(ANIMATION_STEP).getSprite();
                }
                //resetAnimation();
                return UpSprite.get(0).getSprite();
            case 2:
                if (movingLeft) {
                    return leftSprite.get(ANIMATION_STEP).getSprite();
                }
                //resetAnimation();
                return leftSprite.get(0).getSprite();
            case 3:
                if (movingRight) {
                    return rightSprite.get(ANIMATION_STEP).getSprite();
                }
                //resetAnimation();
                return rightSprite.get(0).getSprite();
            default:
                return null;
        }
    }
}
