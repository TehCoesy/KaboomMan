package Entities.Enemies;

import Container.GameEntities;
import Core.Game;
import Graphics.SpriteBuilder;
import States.ApplicationSetting;

public class Ballom extends Enemy {
    private AI ai = new AI(null, null);
    public Ballom(int posX, int posY, GameEntities gameEntities, ApplicationSetting setting) {
        this.setGame(gameEntities, setting);
        this.posX = posX; this.posY = posY;
        this.setTolerance(0);
        this.setUpSprite(SpriteBuilder.getBalloomSprite0());
        this.setDownSprite(SpriteBuilder.getBalloomSprite1());
        this.setLeftSprite(SpriteBuilder.getBalloomSprite2());
        this.setRightSprite(SpriteBuilder.getBalloomSprite3());
        this.setDeadSprite(SpriteBuilder.getMobDeadSprite());
    }

    public void updateAI() {

    }
}
