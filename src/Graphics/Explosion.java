package Graphics;

import Entities.FlameSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Explosion {
    List<FlameSegment> flameSegment = new ArrayList<>();

    public boolean done = false;
    int posX, posY, BLOCK_SIZE, explosionSize;

    Explosion(int x, int y, int BLOCK_SIZE, int explosionSize) {
        this.posX = x; this.posY = y;
        this.BLOCK_SIZE = BLOCK_SIZE;
        createExplosion();
    }

    private void createExplosion() {
        FlameSegment center = new FlameSegment(posX, posY);
        center.setDeadSprite(SpriteBuilder.getExplosionCenter());
        flameSegment.add(center);

        for (int i = 1; i < explosionSize; i++) {

        }

        FlameSegment topLast = new FlameSegment(posX, posY - explosionSize);
        topLast.setDeadSprite(SpriteBuilder.getExplosionTop());
        flameSegment.add(topLast);

        FlameSegment topDown = new FlameSegment(posX, posY + explosionSize);
        topDown.setDeadSprite(SpriteBuilder.getExplosionDown());
        flameSegment.add(topDown);

        FlameSegment topLeft = new FlameSegment(posX - explosionSize, posY);
        topLeft.setDeadSprite(SpriteBuilder.getExplosionLeft());
        flameSegment.add(topLeft);

        FlameSegment topRight = new FlameSegment(posX + explosionSize, posY);
        topRight.setDeadSprite(SpriteBuilder.getExplosionRight());
        flameSegment.add(topRight);
    }


    public void drawExplosion(Graphics g) {
        for (FlameSegment s : flameSegment) {
            g.drawImage(s.getSprite(), s.getX(), s.getY(), BLOCK_SIZE, BLOCK_SIZE, null);
        }
    }
}
