package Entities;

import Graphics.SpriteBuilder;

public class Bomb extends AnimatedEntity {
    public Bomb() {
        this.setStandingSprite(SpriteBuilder.getBombSprite());
    }
    @Override
    public void update() {

    }
}
