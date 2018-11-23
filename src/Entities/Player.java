package Entities;

import Container.GameEntities;
import Core.Game;
import Graphics.*;

import java.awt.image.BufferedImage;

public class Player extends LocomotiveEntity {
    public Player(GameEntities gameEntities) {
        setGame(gameEntities);
        //this.setStandingSprite(SpriteBuilder.getPlayerSprite0());
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

    }
}
