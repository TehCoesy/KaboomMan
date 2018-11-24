package Entities.Enemies;

import Container.GameEntities;
import Core.Game;
import Graphics.SpriteBuilder;

public class Ballom extends Enemy {
    public Ballom(int posX, int posY, GameEntities gameEntities) {
        this.setGame(gameEntities);
        this.posX = posX; this.posY = posY;
        this.setUpSprite(SpriteBuilder.getBalloomSprite0());
        this.setDownSprite(SpriteBuilder.getBalloomSprite1());
        this.setLeftSprite(SpriteBuilder.getBalloomSprite2());
        this.setRightSprite(SpriteBuilder.getBalloomSprite3());
        this.setDeadSprite(SpriteBuilder.getMobDeadSprite());
    }

}
