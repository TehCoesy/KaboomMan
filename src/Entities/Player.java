package Entities;

import Container.GameEntities;
import Core.Game;
import Graphics.*;
import States.ApplicationSetting;

import java.awt.image.BufferedImage;

public class Player extends LocomotiveEntity {
    public Player(GameEntities gameEntities, ApplicationSetting setting) {
        setGame(gameEntities, setting);
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
