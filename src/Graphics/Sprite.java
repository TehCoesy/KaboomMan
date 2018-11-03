package Graphics;

import java.awt.image.BufferedImage;

public class Sprite {
    private BufferedImage spriteImage;

    public void setSprite(BufferedImage image) {
        this.spriteImage = image;
    }

    public BufferedImage getSprite() { return this.spriteImage; }
}
