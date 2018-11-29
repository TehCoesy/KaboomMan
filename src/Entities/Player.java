package Entities;

import Graphics.*;

public class Player extends LocomotiveEntity {
    public Player() {
        this.setDownSprite(SpriteBuilder.getPlayerSprite0());
        this.setUpSprite(SpriteBuilder.getPlayerSprite1());
        this.setLeftSprite(SpriteBuilder.getPlayerSprite2());
        this.setRightSprite(SpriteBuilder.getPlayerSprite3());
        this.setDeadSprite(SpriteBuilder.getPlayerSprite4());
    }

    @Override
    public void update() {

    }

    @Override
    public void kill() {
        resetAnimation();
        dead = true;
    }
}
