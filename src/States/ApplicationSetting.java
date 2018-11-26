package States;

public class ApplicationSetting {
    public static int SCALE = 3;
    public static String TITLE = "KaboomMan";
    public static int BLOCK_SIZE = 65;
    public static int BLOCK_WIDTH = 15;
    public static int BLOCK_HEIGHT = 15;
    public static int WIDTH = 975;
    public static int HEIGHT = 975;

    public static final int BLOCK_SIZE_DEFAULT = 15;

    public void setScale(int scale) {
        this.SCALE = scale;
        this.BLOCK_SIZE = this.SCALE * this.BLOCK_SIZE_DEFAULT;
    }
}
