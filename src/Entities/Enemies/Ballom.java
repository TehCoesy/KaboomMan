package Entities.Enemies;

import Graphics.SpriteBuilder;

import java.util.Random;

public class Ballom extends Enemy {

    public Ballom() {
        this.setTolerance(0);
        this.setUpSprite(SpriteBuilder.getBalloomSprite0());
        this.setDownSprite(SpriteBuilder.getBalloomSprite1());
        this.setLeftSprite(SpriteBuilder.getBalloomSprite2());
        this.setRightSprite(SpriteBuilder.getBalloomSprite3());
        this.setDeadSprite(SpriteBuilder.getMobDeadSprite());
    }

    private boolean moveEnemy;
    private int orient = 0; //0 = Left, 1 = Right
    public int getRandom(Random rand, int start, int end, int... exclude){
        int random = start + rand.nextInt(end - start + 1 - exclude.length);
        for (int ex : exclude){
            if (random < ex){
                break;
            }
            random++;
        }
        return random;
    }
    @Override
    public void update() {
        if (GLOBAL_TICKS % 15 == 0) {
            Random rand = new Random();
            orient = getRandom(rand,0,3);
            moveEnemy = true;
        }

        if (moveEnemy) {
            if (orient == 0) {
                moveDown();
            }
            if (orient == 2) {
                moveLeft();
            }
            if (orient == 3) {
                moveRight();
            }
            if (orient == 1) {
                moveUp();
            }
        }
    }

    @Override
    public void updateAI() {

    }
}
