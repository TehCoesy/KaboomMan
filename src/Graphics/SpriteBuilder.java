package Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Handles loading BufferedImage for Sprites and factoring SpriteBuilder

public class SpriteBuilder {
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

    public static List<Sprite> getBombSprite() {
        List<Sprite> bombSprites = new ArrayList<>();
        bombSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/bomb.png")));
        bombSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/bomb_1.png")));
        bombSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/bomb_2.png")));
        bombSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/bomb_1.png")));

        return bombSprites;
    }

    //Player
    public static List<Sprite> getPlayerSprite0() {
        List<Sprite> standingSprites = new ArrayList<>();
        standingSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_down.png")));
        standingSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_down_1.png")));
        standingSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_down_2.png")));
        standingSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_down_1.png")));
        return standingSprites;
    }
    public static List<Sprite> getPlayerSprite1() {
        List<Sprite> upSprites = new ArrayList<>();
        upSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_up.png")));
        upSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_up_1.png")));
        upSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_up_2.png")));
        upSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_up_1.png")));
        return upSprites;
    }
    public static List<Sprite> getPlayerSprite2() {
        List<Sprite> leftSprites = new ArrayList<>();
        leftSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_left.png")));
        leftSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_left_1.png")));
        leftSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_left_2.png")));
        leftSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_left_1.png")));
        return leftSprites;
    }
    public static List<Sprite> getPlayerSprite3() {
        List<Sprite> rightSprites = new ArrayList<>();
        rightSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_right.png")));
        rightSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_right_1.png")));
        rightSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_right_2.png")));
        rightSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_right_1.png")));
        return rightSprites;
    }
    public static List<Sprite> getPlayerSprite4() {
        List<Sprite> deadSprite = new ArrayList<>();
        deadSprite.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_dead1.png")));
        deadSprite.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_dead2.png")));
        deadSprite.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/player_dead3.png")));
        return deadSprite;
    }

    //Enemies
}
