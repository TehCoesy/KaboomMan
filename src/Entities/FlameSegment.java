package Entities;

public class FlameSegment extends AnimatedEntity{

    public FlameSegment(int x, int y) {
        this.posX = x; this.posY = y;
        this.kill();
    }

    @Override
    public void update() {
    }
}
