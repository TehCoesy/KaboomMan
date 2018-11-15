package Graphics;

import Entities.FlameSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Explosion {
    List<FlameSegment> flameSegment = new ArrayList<>();

    public boolean done = false;
    int posX, posY, BLOCK_SIZE, explosionSize;

    public Explosion(int x, int y, int BLOCK_SIZE, int explosionSize) {
        this.posX = x; this.posY = y;
        this.BLOCK_SIZE = BLOCK_SIZE;
        this.explosionSize = explosionSize;
        createExplosion();
    }

    private void createExplosion() {
        FlameSegment center = new FlameSegment(posX, posY);
        center.setDeadSprite(SpriteBuilder.getExplosionCenter());
        flameSegment.add(center);

        for (int i = 1; i < explosionSize; i++) {
            FlameSegment top = new FlameSegment(posX, posY - i);
            top.setDeadSprite(SpriteBuilder.getExplosionVertical());
            flameSegment.add(top);

            FlameSegment down = new FlameSegment(posX, posY + i);
            down.setDeadSprite(SpriteBuilder.getExplosionVertical());
            flameSegment.add(down);

            FlameSegment left = new FlameSegment(posX - i, posY);
            left.setDeadSprite(SpriteBuilder.getExplosionHorizontal());
            flameSegment.add(left);

            FlameSegment right = new FlameSegment(posX + i, posY);
            right.setDeadSprite(SpriteBuilder.getExplosionHorizontal());
            flameSegment.add(right);
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

        for (FlameSegment flame : flameSegment) {
            flame.setAnimationTime(4);
        }
    }

    public void tick() {
        for (FlameSegment flame : flameSegment) {
            flame.tick();
        }
        update();
    }

    private void update() {
        int counter = 0, n = flameSegment.size();
        for (FlameSegment flame : flameSegment) {
            if (flame.isDone()) {
                counter++;
            }
        }
        if (counter >= n) {
            done = true;
        }
    }

    public void drawExplosion(Graphics g) {
        for (FlameSegment s : flameSegment) {
            g.drawImage(s.getSprite(), s.getX() * BLOCK_SIZE, s.getY() * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, null);
        }
    }

    public List<FlameSegment> getSegments() {
        return this.flameSegment;
    }
}