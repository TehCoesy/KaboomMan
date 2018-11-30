package Graphics;

public class GameTransition {
    private String currentState = "StageTransition";
    private int currentTick;

    public void tick() { // 1/60 Second
        this.currentTick++;
    }

    public void update() {
        if (this.currentTick == 60) {
            this.currentState = "Done";
        }
    }

    public String getState() {
        return this.currentState;
    }
}
