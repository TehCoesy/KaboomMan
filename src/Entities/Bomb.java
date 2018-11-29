package Entities;

import Core.Vector2i;
import Graphics.Explosion;
import Graphics.SpriteBuilder;

public class Bomb extends AnimatedEntity {
    private Vector2i positionMemory = new Vector2i();
    public Bomb() {
        this.setStandingSprite(SpriteBuilder.getBombSprite());
    }

    @Override
    public void setPosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        positionMemory.set(posX, posY);
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
