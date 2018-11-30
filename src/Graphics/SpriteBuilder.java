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
        standingSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_down.png")));
        standingSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_down_1.png")));
        standingSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_down_2.png")));
        standingSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_down_1.png")));
        return standingSprites;
    }
    public static List<Sprite> getPlayerSprite1() {
        List<Sprite> upSprites = new ArrayList<>();
        upSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_up.png")));
        upSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_up_1.png")));
        upSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_up_2.png")));
        upSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_up_1.png")));
        return upSprites;
    }
    public static List<Sprite> getPlayerSprite2() {
        List<Sprite> leftSprites = new ArrayList<>();
        leftSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_left.png")));
        leftSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_left_1.png")));
        leftSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_left_2.png")));
        leftSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_left_1.png")));
        return leftSprites;
    }
    public static List<Sprite> getPlayerSprite3() {
        List<Sprite> rightSprites = new ArrayList<>();
        rightSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_right.png")));
        rightSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_right_1.png")));
        rightSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_right_2.png")));
        rightSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_right_1.png")));
        return rightSprites;
    }
    public static List<Sprite> getPlayerSprite4() {
        List<Sprite> deadSprite = new ArrayList<>();
        deadSprite.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_dead1.png")));
        deadSprite.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_dead2.png")));
        deadSprite.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Player/player_dead3.png")));
        return deadSprite;
    }

    //Enemies

    //Explosion
    public static List<Sprite> getExplosionCenter() {
        List<Sprite> explosionCenter = new ArrayList<>();
        explosionCenter.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/bomb_exploded.png")));
        explosionCenter.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/bomb_exploded1.png")));
        explosionCenter.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/bomb_exploded2.png")));
        explosionCenter.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/bomb_exploded1.png")));
        return explosionCenter;
    }

    public static List<Sprite> getExplosionHorizontal() {
        List<Sprite> explosionHorizontal = new ArrayList<>();
        explosionHorizontal.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_horizontal.png")));
        explosionHorizontal.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_horizontal1.png")));
        explosionHorizontal.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_horizontal2.png")));
        explosionHorizontal.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_horizontal1.png")));
        return explosionHorizontal;
    }

    public static List<Sprite> getExplosionVertical() {
        List<Sprite> explosionVertical = new ArrayList<>();
        explosionVertical.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_vertical.png")));
        explosionVertical.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_vertical1.png")));
        explosionVertical.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_vertical2.png")));
        explosionVertical.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_vertical1.png")));
        return explosionVertical;
    }

    public static List<Sprite> getExplosionLeft() {
        List<Sprite> explosionLeft = new ArrayList<>();
        explosionLeft.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_horizontal_left_last.png")));
        explosionLeft.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_horizontal_left_last1.png")));
        explosionLeft.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_horizontal_left_last2.png")));
        explosionLeft.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_horizontal_left_last1.png")));
        return explosionLeft;
    }

    public static List<Sprite> getExplosionRight() {
        List<Sprite> explosionRight = new ArrayList<>();
        explosionRight.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_horizontal_right_last.png")));
        explosionRight.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_horizontal_right_last1.png")));
        explosionRight.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_horizontal_right_last2.png")));
        explosionRight.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_horizontal_right_last1.png")));
        return explosionRight;
    }

    public static List<Sprite> getExplosionTop() {
        List<Sprite> explosionTop = new ArrayList<>();
        explosionTop.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_vertical_top_last.png")));
        explosionTop.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_vertical_top_last1.png")));
        explosionTop.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_vertical_top_last2.png")));
        explosionTop.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_vertical_top_last1.png")));
        return explosionTop;
    }

    public static List<Sprite> getExplosionDown() {
        List<Sprite> explosionDown = new ArrayList<>();
        explosionDown.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_vertical_down_last.png")));
        explosionDown.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_vertical_down_last1.png")));
        explosionDown.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_vertical_down_last2.png")));
        explosionDown.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/Explosion/explosion_vertical_down_last1.png")));
        return explosionDown;
    }
    //Enemies
    public static List<Sprite> getBalloomSprite0() {
        List<Sprite> downBalloomSprites = new ArrayList<>();
        downBalloomSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/balloom_left1.png")));
        downBalloomSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/balloom_left2.png")));
        downBalloomSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/balloom_left3.png")));
        return downBalloomSprites;
    }
    public static List<Sprite> getBalloomSprite1() {
        List<Sprite> upBalloomSprites = new ArrayList<>();
        upBalloomSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/balloom_right1.png")));
        upBalloomSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/balloom_right2.png")));
        upBalloomSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/balloom_right3.png")));
        return upBalloomSprites;
    }
    public static List<Sprite> getBalloomSprite2() {
        List<Sprite> leftBalloomSprites = new ArrayList<>();
        leftBalloomSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/balloom_left1.png")));
        leftBalloomSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/balloom_left2.png")));
        leftBalloomSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/balloom_left3.png")));
        return leftBalloomSprites;
    }
    public static List<Sprite> getBalloomSprite3() {
        List<Sprite> rightBalloomSprites = new ArrayList<>();
        rightBalloomSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/balloom_right1.png")));
        rightBalloomSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/balloom_right2.png")));
        rightBalloomSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/balloom_right3.png")));
        return rightBalloomSprites;
    }
    public static List<Sprite> getMobDeadSprite() {
        List<Sprite> deadSprites = new ArrayList<>();
        deadSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/mob_dead1.png")));
        deadSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/mob_dead2.png")));
        deadSprites.add(new Sprite(SpriteBuilder.getSpriteImage("Data/Sprite/mob_dead3.png")));
        return deadSprites;
    }
}
