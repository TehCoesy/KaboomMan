package Entities;

import Core.Game;
import Graphics.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends AnimatedEntity {
    private int velocity = 10;

    private Sprite playerSprite[] = new Sprite[4];

    public Player(Game game) {
        playerSprite[0] = new Sprite(SpriteSheet.getSpriteImage("Data/Sprite/player_down.png"));
        playerSprite[1] = new Sprite(SpriteSheet.getSpriteImage("Data/Sprite/player_up.png"));
        playerSprite[2] = new Sprite(SpriteSheet.getSpriteImage("Data/Sprite/player_left.png"));
        playerSprite[3] = new Sprite(SpriteSheet.getSpriteImage("Data/Sprite/player_right.png"));
        setGame(game);
    }

    @Override
    public void update() {

    }

    @Override
    public BufferedImage getSprite() {
        switch (ORIENTATION) {
            case 0:
                return playerSprite[0].getSprite();
            case 1:
                return playerSprite[1].getSprite();
            case 2:
                return playerSprite[2].getSprite();
            case 3:
                return playerSprite[3].getSprite();
            default:
                return null;
        }
    }
}
