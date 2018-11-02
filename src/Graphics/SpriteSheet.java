package Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {
    private static BufferedImage spriteSheet;
    private static final int TILE_SIZE = 32;

    public static void loadSpriteSheet(String file) {
        try {
            spriteSheet = ImageIO.read(new File(file));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static BufferedImage getSprite(int xGrid, int yGrid) {
        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public static BufferedImage getSpriteImage(String file) {
        BufferedImage spriteImage = null;
        try {
            spriteImage = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return spriteImage;
    }
}
