package Entities;

import java.util.List;

//TODO: Animation
//TODO: collision detection and moving;

/*
AnimatedEntity.java, a base class for all Enemies, Players and other non-static Objects
 */
public abstract class AnimatedEntity extends Entity{
    protected int VEL = 5;
    protected final int TOLERANCE = 20; // Collision tolerances

    protected int ORIENTATION = 0; // 0 = DOWN, 1 = UP, 2 = LEFT, 3 = RIGHT


    protected boolean canMove(List<StaticEntity> staticEntities) {
        switch (this.ORIENTATION) {
            case 0: break;
            case 1: break;
            case 2: break;
            case 3: break;
        }
        return true;
    }

    //Movement
    public void move(int orientation, List<StaticEntity> staticEntities) {
        this.ORIENTATION = orientation;
        if (canMove(staticEntities)) {
            moveEntity();
        }
    }

    protected void moveEntity() {
        switch(ORIENTATION) {
            case 0: this.posY -= VEL; break;
            case 1: this.posY += VEL; break;
            case 2: this.posX -= VEL; break;
            case 3: this.posX += VEL; break;
        }
    }
}
