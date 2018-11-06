package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
This game render game Entity by drawing a BufferedImage of the Entity
calling Graphics.drawImage(Entity.BufferedImage, posX, posY, BLOCK_SIZE, BLOCK_SIZE, null); inside the game loop in Game.java;

For static Objects (Walls, Bricks, Powerups, ...) please use staticSprite[] in Game.java for BufferedImage,
initialize more elements in staticSprite[] as needed.
For non-static Objects (Players and Enemies) load the BufferedImages of the Object in the constructors of the Object (See Player.java for example)
 */

public abstract class Entity {
    protected int posX, posY;

    public abstract void update();

    public abstract BufferedImage getSprite();

    public int getX() { return this.posX; }
    public int getY() { return this.posY; }
}
