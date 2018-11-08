package Entities;

import Graphics.SpriteBuilder;

import java.awt.image.BufferedImage;

public class Bomb extends AnimatedEntity {
    BufferedImage bombSprite;

    public Bomb() {
        bombSprite = SpriteBuilder.getSpriteImage("Data/Sprite/bomb.png");
    }
    @Override
    public void update() {

    }

    @Override
    public BufferedImage getSprite() {
        return bombSprite;
    }
}
