package Entities.Statics;

import java.awt.image.BufferedImage;

public class Brick extends StaticEntity {
    @Override
    public void update() {

    }

    @Override
    public BufferedImage getSprite() {
        return null;
    }

    @Override
    public void kill() {
        this.dead = true;
        this.done = true;
    }
}
