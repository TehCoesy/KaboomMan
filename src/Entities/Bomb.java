package Entities;

import Graphics.Explosion;
import Graphics.SpriteBuilder;

public class Bomb extends AnimatedEntity {
    public Bomb() {
        this.setStandingSprite(SpriteBuilder.getBombSprite());
    }
    @Override
    public void update() {
        if (this.GLOBAL_TICKS >= 240) {
            this.kill();
        }
    }

    @Override
    public void kill() {
        dead = true;
        done = true;
    }
}
