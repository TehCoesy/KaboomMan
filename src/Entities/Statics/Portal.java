package Entities.Statics;

import Graphics.SpriteBuilder;

import java.awt.image.BufferedImage;

public class Portal extends StaticEntity {
    private BufferedImage portal, brick;

    public Portal() {
        portal = SpriteBuilder.getSpriteImage("Data/Sprite/portal.png");
        brick = SpriteBuilder.getSpriteImage("Data/Sprite/brick.png");
    }

    @Override
    public void kill() {
        this.dead = true;
    }

    @Override
    public BufferedImage getSprite() {
        if (isDead()) {
            return portal;
        } else {
            return brick;
        }
    }

    public void enter() {
        this.done = true;
    }
}
