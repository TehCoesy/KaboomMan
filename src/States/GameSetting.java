package States;

public class GameSetting {
    public static int GAME_SCREEN_OFFSET_X = 0;
    public static int GAME_SCREEN_OFFSET_Y = 0;

    public static int SCALE = 3;
    public static String TITLE = "KaboomMan";
    public static int BLOCK_SIZE = 65;
    public static int BLOCK_WIDTH = 15;
    public static int BLOCK_HEIGHT = 15;
    public static int WIDTH = 975;
    public static int HEIGHT = 975;

    public static int CURRENT_LEVEL = 0;
    public static int CURRENT_SCORE = 0;
    public static int CURRENT_LIVES = 3;

    public static final int BLOCK_SIZE_DEFAULT = 15;

    public void setScale(int scale) {
        this.SCALE = scale;
        this.BLOCK_SIZE = this.SCALE * this.BLOCK_SIZE_DEFAULT;
    }
}
