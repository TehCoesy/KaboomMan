package States;

public class PlayerState {
    public int BOMB_COUNT = 1;
    public int PLAYER_SPEED = 4;
    public int BOMB_POWER = 1;

    public void getDefault() {
        this.BOMB_COUNT = 1;
        this.BOMB_POWER = 1;
        this.PLAYER_SPEED = 4;
    }
}
