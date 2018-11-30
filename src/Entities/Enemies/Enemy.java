package Entities.Enemies;

import Entities.LocomotiveEntity;
import java.util.Random;

public abstract class Enemy extends LocomotiveEntity {
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

    @Override
    public void kill() {
        resetAnimation();
        dead = true;
    }

    public abstract void updateAI();
}
