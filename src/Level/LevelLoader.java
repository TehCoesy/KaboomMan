package Level;

import Container.GameEntities;
import Entities.*;
import Entities.Enemies.Enemy;
import Entities.Statics.Brick;
import Entities.Statics.Portal;
import Entities.Statics.StaticEntity;
import Entities.Statics.Wall;
import States.ApplicationSetting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
    private GameEntities gameEntities;
    private ApplicationSetting settings;
    private List<StaticEntity> statics = new ArrayList<>();
    private Player player;
    private List<Enemy> enemies = new ArrayList<>();

    public void loadLevel(String path, String level) throws Exception {
        gameEntities = new GameEntities();

        FileReader fileReader = null;
        BufferedReader reader = null;

        fileReader = new FileReader(path + "\\" + level);
        reader = new BufferedReader(fileReader);

        String line = null;

        line = reader.readLine();
        String[] token = line.split(" ");


        if (token.length != 2) throw new Exception("FAULT (LevelLoader) (" + level + ") GameSize parameter is incorrect");

        int GAME_HEIGHT = Integer.parseInt(token[0]);
        int GAME_WIDTH = Integer.parseInt(token[1]);

        gameEntities.gameSize.set(GAME_WIDTH,GAME_HEIGHT);

        for (int i = 0; i < GAME_HEIGHT; i++) {
            line = reader.readLine();
            for (int k = 0; k < GAME_WIDTH; k++) {
                char input = line.charAt(k);
                getEntity(input, i, k);
            }
        }
    }

    public List<StaticEntity> getStatics() {
        return this.statics;
    }
    public List<Enemy> getEnemies() { return this.enemies; }

    private void getEntity(char input, int posY, int posX) {
        switch (input) {
            case '#': {
                Wall wall = new Wall();
                wall.setPosition(posX,posY);
                statics.add(wall);
                return;
            }
            case '*': {
                Brick brick = new Brick();
                brick.setPosition(posX,posY);
                statics.add(brick);
                return;
            }
            case 'P': {
                Portal portal = new Portal();
                portal.setPosition(posX, posY);
                statics.add(portal);
                return;
            }
            default: return;
        }
    }
}
