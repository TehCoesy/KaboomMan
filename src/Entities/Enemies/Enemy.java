package Entities.Enemies;

import Entities.LocomotiveEntity;
import java.util.Random;

public class Enemy extends LocomotiveEntity {
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
        if (!collide_down) {
            ORIENTATION = getRandom(rand, 0, 3, 0);
            move(ORIENTATION);
        }
        if (!collide_top) {
            ORIENTATION = getRandom(rand, 0, 3, 1);
            move(ORIENTATION);
        }
        if (!collide_left) {
            ORIENTATION = getRandom(rand, 0, 3, 2);
            move(ORIENTATION);
        }
        if (!collide_right) {
            ORIENTATION = getRandom(rand, 0, 3, 3);
            move(ORIENTATION);
        }
    }
}
