package Entities;

import java.util.List;
import Core.Game;
import Core.Vector2i;

//TODO: Animation
//TODO: collision detection and moving;

/*
AnimatedEntity.java, a base class for all Enemies, Players and other non-static Objects
 */
public abstract class AnimatedEntity extends Entity{
    protected Game _game;

    //ENTITY PARAMETER
    protected int VEL = 8;
    protected final int TOLERANCE = 20; // Collision tolerances
    protected int ORIENTATION = 0; // 0 = DOWN, 1 = UP, 2 = LEFT, 3 = RIGHT

    //GAME PARAMETER
    private int BLOCK_SIZE;
    private boolean collideDown, collideUp, collideLeft, collideRight;

    protected void setGame(Game game) {
        this._game = game;
        this.BLOCK_SIZE = game.getBlockSize();
    }

    //Collision

    private void moveEntity(List<StaticEntity> staticEntities) {
        switch (ORIENTATION) {
            case 0: {
                if (canMove(posX, posY + VEL,staticEntities)) {
                    posY += VEL;
                }
                break;
            }
            case 1: {
                if (canMove(posX, posY - VEL,staticEntities)) {
                    posY -= VEL;
                }
                break;
            }
            case 2: {
                if (canMove(posX - VEL, posY,staticEntities)) {
                    posX -= VEL;
                }
                break;
            }
            case 3: {
                if (canMove(posX + VEL, posY,staticEntities)) {
                    posX += VEL;
                }
                break;
            }
        }
    }

    private boolean canMove(int posX, int posY, List<StaticEntity> staticEntities) {
        int n = staticEntities.size();
        for (int i = 0; i < n; i++) {
            StaticEntity element = staticEntities.get(i);
            if (posX + BLOCK_SIZE - TOLERANCE <= element.posX * 65 || posX >= element.posX * 65 + BLOCK_SIZE - TOLERANCE) {
                continue;
            }
            if (posY + BLOCK_SIZE - TOLERANCE <= element.posY * 65 || posY >= element.posY * 65 + BLOCK_SIZE - TOLERANCE) {
                continue;
            }
            return false;
        }
        return true;
    }

    //Movement
    public void move(int orientation) {
        this.ORIENTATION = orientation;
        moveEntity(_game.staticEntities);
    }
}
