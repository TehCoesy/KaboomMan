package Graphics;


import Container.GameEntities;
import Core.Vector2i;
import Entities.Player;
import States.ApplicationSetting;

public class Camera {
    private Vector2i translation = new Vector2i();
    private GameEntities gameEntities;
    private ApplicationSetting setting;
    private Player player;

    //PARAMETERS
    private Vector2i WINDOW_SIZE;
    private Vector2i CAMERA_LOCATION;

    public Camera(GameEntities gameEntities, ApplicationSetting setting) {
        this.setting = setting;
        this.gameEntities = gameEntities;
        this.player = gameEntities.player;
        this.WINDOW_SIZE = new Vector2i(setting.WIDTH, setting.HEIGHT);
        this.CAMERA_LOCATION = gameEntities.player.getPosition();
    }

    public void moveDown() {
        int boundary = 65 * 20;

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
        int boundary = 65 * 20;

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
