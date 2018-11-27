package Entities.Enemies;

import Entities.LocomotiveEntity;
import java.util.Random;

public class Enemy extends LocomotiveEntity {
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
    //AI
    public void moveEnemy() {
        Random rand = new Random();
        int orient = getRandom(rand, 0, 3);

        switch (orient) {
            case 0: if (!collide_down) {
                moveDown();
            }; break;

            case 1: if (!collide_top) {
                moveUp();
            }; break;
            case 2: if (!collide_left) {
                moveLeft();
            }; break;
            case 3: if (!collide_right) {
                moveRight();
            }; break;
        }
    }
}
