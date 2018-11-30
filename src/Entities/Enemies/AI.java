package Entities.Enemies;

import java.util.Random;
import Entities.Player;

public class AI {
    Player p;
    Enemy e;

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
    public void set(Player player, Enemy orneal){
        p = player;
        e = orneal;
    }

    public int PlayerFound() {
        Random rand = new Random();
        if (p == null)
          getRandom(rand,0, 3);

        int vertical = getRandom(rand, 0,1);
        if (vertical == 1){
            return PlayerChaseCollum();
        } else
                return PlayerChaseRow();
    }
    protected int PlayerChaseCollum() {
        if ( p.getRelativePosition().getY() < e.getRelativePosition().getY())
            return 0;
        else if (p.getRelativePosition().getY() > e.getRelativePosition().getY())
            return 1;

        return -1;
    }
    protected int PlayerChaseRow() {
        if ( p.getRelativePosition().getX() < e.getRelativePosition().getX())
            return 2;
        else if (p.getRelativePosition().getX() > e.getRelativePosition().getX())
            return 3;
        return -1;
    }
}
