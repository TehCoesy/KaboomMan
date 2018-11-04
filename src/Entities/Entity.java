package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected int posX, posY;

    public abstract void update();

    public abstract BufferedImage getSprite();
}
