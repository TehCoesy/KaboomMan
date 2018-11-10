package Entities;

import Graphics.SpriteBuilder;

public class Bomb extends AnimatedEntity {
    public Bomb() {
        this.setStandingSprite(SpriteBuilder.getBombSprite());
    }
    @Override
    public void update() {
        if (this.GLOBAL_TICKS >= 300) {
            this.kill();
        }
    }

    @Override
    public void kill() {
        dead = true;

    }

    public void explode() {

    }
}
