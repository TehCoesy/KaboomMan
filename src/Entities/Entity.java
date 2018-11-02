package Entities;

import java.awt.*;

public abstract class Entity {
    protected int posX, posY;

    public abstract void update();
    public abstract void render(Graphics g);
}
