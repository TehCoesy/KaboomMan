package Entities.Statics;

import Entities.Statics.StaticEntity;

import java.awt.image.BufferedImage;

public class Wall extends StaticEntity {

    public Wall() {
        this.type = "Wall";
    }

    public void update() {

    }

    @Override
    public void kill() {

    }

    @Override
    public BufferedImage getSprite() {
        return null;
    }
}
