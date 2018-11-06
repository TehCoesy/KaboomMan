package Entities;

import Graphics.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends AnimatedEntity {
    private int velocity = 10;

    private int orientation = 1; // 0 = DOWN, 1 = UP, 2 = LEFT, 3 = RIGHT
    private Sprite playerSprite[] = new Sprite[4];

    public Player() {
        playerSprite[0] = new Sprite(SpriteSheet.getSpriteImage("Data/Sprite/player_down.png"));
        playerSprite[1] = new Sprite(SpriteSheet.getSpriteImage("Data/Sprite/player_up.png"));
        playerSprite[2] = new Sprite(SpriteSheet.getSpriteImage("Data/Sprite/player_left.png"));
        playerSprite[3] = new Sprite(SpriteSheet.getSpriteImage("Data/Sprite/player_right.png"));
    }

    @Override
    public void update() {

    }

    @Override
    public BufferedImage getSprite() {
        switch (orientation) {
            case 0: return playerSprite[1].getSprite();
            case 1: return playerSprite[0].getSprite();
            case 2: return playerSprite[2].getSprite();
            case 3: return playerSprite[3].getSprite();
            default: return null;
        }
    }

    public void move(int orientation, int bound) {
        this.orientation = orientation;

        switch(orientation) {
            case 0: this.posY -= velocity; break;
            case 1: this.posY += velocity; break;
            case 2: this.posX -= velocity; break;
            case 3: this.posX += velocity; break;
        }
    }

}
