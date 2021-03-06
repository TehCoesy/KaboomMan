package Graphics;


import Container.GameEntities;
import Core.Vector2i;
import Entities.Player;
import States.GameSetting;

public class Camera {
    private Vector2i translation = new Vector2i();
    private GameEntities gameEntities;
    private GameSetting setting;
    private Player player;

    //PARAMETERS
    private Vector2i GAME_SIZE;
    private Vector2i WINDOW_SIZE;
    private Vector2i CAMERA_LOCATION;

    public Camera(GameEntities gameEntities, GameSetting setting) {
        this.setting = setting;
        this.gameEntities = gameEntities;
        this.player = gameEntities.player;
        this.GAME_SIZE = new Vector2i(setting.BLOCK_WIDTH, setting.BLOCK_HEIGHT);
        this.WINDOW_SIZE = new Vector2i(setting.WIDTH, setting.HEIGHT);
        this.CAMERA_LOCATION = gameEntities.player.getPosition();

        System.out.println(GAME_SIZE.getX() + " " + GAME_SIZE.getY());
    }

    public void moveDown() {
        int boundary = setting.BLOCK_SIZE * GAME_SIZE.getY();

        if (0 - translation.getY() < boundary - WINDOW_SIZE.getY()) {
            if (getCenterDown()) {
                translation.translateY(-player.getVelocity());
            }
        }
    }

    public void moveUp() {
        if (translation.getY() < 0) {
            if (getCenterUp()) {
                translation.translateY(player.getVelocity());
            }
        }
    }

    public void moveLeft() {
        if (translation.getX() < 0) {
            if (getCenterLeft()) {
                translation.translateX(player.getVelocity());
            }
        }
    }

    public void moveRight() {
        int boundary = setting.BLOCK_SIZE * GAME_SIZE.getX();

        if (0 - translation.getX() < boundary - WINDOW_SIZE.getX()) {
            if (getCenterRight()) {
                translation.translateX(-player.getVelocity());
            }
        }
    }

    private boolean getCenterDown() {
        if (player.getPosition().getY() > WINDOW_SIZE.getY() / 2) {
            return true;
        } else {
            return false;
        }
    }

    private boolean getCenterUp() {
        if (player.getPosition().getY() < WINDOW_SIZE.getX() / 2) {
            return true;
        } else {
            return false;
        }
    }

    private boolean getCenterLeft() {
        if (player.getPosition().getX() < WINDOW_SIZE.getX() / 2) {
            return true;
        } else {
            return false;
        }
    }

    private boolean getCenterRight() {
        if (player.getPosition().getX() > WINDOW_SIZE.getX() / 2) {
            return true;
        } else {
            return false;
        }
    }
    public Vector2i getTranslation() { return this.translation; }
}
