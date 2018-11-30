package Entities.Enemies;

import Container.GameEntities;
import Core.GameOverseer;
import Graphics.SpriteBuilder;
import Entities.LocomotiveEntity;
import Entities.Enemies.AI;

public class Oneal extends Enemy {
    public AI ai = new AI();

    public Oneal() {
        this.setTolerance(0);
        this.setUpSprite(SpriteBuilder.getOnealSprite0());
        this.setDownSprite(SpriteBuilder.getOnealSprite1());
        this.setLeftSprite(SpriteBuilder.getOnealSprite2());
        this.setRightSprite(SpriteBuilder.getOnealSprite3());
        this.setDeadSprite(SpriteBuilder.getMobDeadSprite());
    }

    @Override
    public void update() {
        updateAI();
    }
    @Override
    public void updateAI() {
        switch (ai.PlayerFound()) {
            case 0: moveDown();break;
            case 1: moveUp();break;
            case 2: moveLeft();break;
            case 3: moveRight();break;
        };
    }
}
