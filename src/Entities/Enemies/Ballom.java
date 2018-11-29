package Entities.Enemies;

import Container.GameEntities;
import Core.GameOverseer;
import Graphics.SpriteBuilder;
import States.ApplicationSetting;

public class Ballom extends Enemy {
    public Ballom() {
        this.setTolerance(0);
        this.setUpSprite(SpriteBuilder.getBalloomSprite0());
        this.setDownSprite(SpriteBuilder.getBalloomSprite1());
        this.setLeftSprite(SpriteBuilder.getBalloomSprite2());
        this.setRightSprite(SpriteBuilder.getBalloomSprite3());
        this.setDeadSprite(SpriteBuilder.getMobDeadSprite());
    }

}
