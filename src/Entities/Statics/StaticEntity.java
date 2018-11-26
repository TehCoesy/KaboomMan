package Entities.Statics;

import Entities.Entity;

import java.awt.image.BufferedImage;

public class StaticEntity extends Entity {
    String type;

    protected boolean collidable = true;

    @Override
    public void update() {

    }

    @Override
    public BufferedImage getSprite() {
        return null;
    }

    @Override
    public void kill() {

    }

    public boolean isCollidable() { return this.collidable; }
    public void setCollidable(boolean collidable) { this.collidable = collidable; }
}
