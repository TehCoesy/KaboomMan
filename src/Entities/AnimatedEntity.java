package Entities;

import java.util.List;

//TODO: Animation
//TODO: collision detection and moving;
public abstract class AnimatedEntity extends Entity{
    protected final int TOLERANCE = 20;

    private boolean canMove(List<StaticEntity> statics, int orientation) {
        return false;
    }

    private boolean moveEntity() {
        return false;
    }
}
