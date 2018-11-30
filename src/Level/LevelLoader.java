package Level;

import Container.GameEntities;
import Entities.*;
import Entities.Enemies.Ballom;
import Entities.Enemies.Enemy;
import Entities.Enemies.Oneal;
import Entities.Statics.*;
import States.GameSetting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class LevelLoader {
    private String _filePath = "Data/Levels/";
    private String _fileName;

    private GameEntities gameEntities;
    private GameSetting settings;

    private List<StaticEntity> statics;
    private List<Enemy> enemies;
    private List<PowerUp> powerUps;

    public void loadLevel(String level) throws Exception {
        this._fileName = level;

        gameEntities = new GameEntities();
        settings = new GameSetting();

        statics = gameEntities.staticEntities;
        enemies = gameEntities.enemies;
        powerUps = gameEntities.powerUps;

        FileReader fileReader = null;
        BufferedReader reader = null;

        fileReader = new FileReader(_filePath + _fileName);
        reader = new BufferedReader(fileReader);

        String line = null;

        line = reader.readLine();
        String[] token = line.split(" ");


        if (token.length != 3) throw new Exception("FAULT (LevelLoader) (" + level + ") GameSize parameter is incorrect");

        int GAME_HEIGHT = Integer.parseInt(token[1]);
        int GAME_WIDTH = Integer.parseInt(token[2]);
        int SCALE = Integer.parseInt(token[0]);

        this.settings.setScale(SCALE);
        this.settings.BLOCK_HEIGHT = GAME_HEIGHT;
        this.settings.BLOCK_WIDTH = GAME_WIDTH;

        for (int i = 0; i < GAME_HEIGHT; i++) {
            line = reader.readLine();
            for (int k = 0; k < GAME_WIDTH; k++) {
                char input = line.charAt(k);
                getEntity(input, i, k);
            }
        }

        validateMap();

        gameEntities.staticEntities.addAll(gameEntities.powerUps);
    }

    public GameEntities getEntities() { return this.gameEntities; }
    public GameSetting getSettings() { return this.settings; }

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
                gameEntities.portal = new Portal();
                gameEntities.portal.setPosition(posX, posY);
                gameEntities.staticEntities.add(gameEntities.portal);
                return;
            }
            case 'C': {
                gameEntities.player = new Player();
                gameEntities.player.setPosition(posX * settings.BLOCK_SIZE, posY * settings.BLOCK_SIZE);
                gameEntities.playerStartingPOS.set(posX * settings.BLOCK_SIZE, posY * settings.BLOCK_SIZE);
                return;
            }
            case 'b': {
                PowerUp POWERUP = new PowerUp("BOMB_COUNT");
                POWERUP.setPosition(posX, posY);
                gameEntities.powerUps.add(POWERUP);
                return;
            }
            case 'f': {
                PowerUp POWERUP = new PowerUp("BOMB_SIZE");
                POWERUP.setPosition(posX, posY);
                gameEntities.powerUps.add(POWERUP);
                return;
            }
            case 's': {
                PowerUp POWERUP = new PowerUp("SPEED");
                POWERUP.setPosition(posX, posY);
                gameEntities.powerUps.add(POWERUP);
                return;
            }
            case '1': {
                Ballom ballom = new Ballom();
                ballom.setPosition(posX * settings.BLOCK_SIZE, posY * settings.BLOCK_SIZE);
                gameEntities.enemies.add(ballom);
                return;
            }
            case '2': {
                Oneal oneal = new Oneal();
                oneal.setPosition(posX * settings.BLOCK_SIZE, posY * settings.BLOCK_SIZE);
                gameEntities.enemies.add(oneal);
            }
            default: return;
        }
    }

    private void validateMap() throws Exception {
        if (gameEntities.player == null) {
            throw new Exception("LevelLoader: (" + _fileName + ") Player not found on Map.");
        }

        if (gameEntities.portal == null) {
            throw new Exception("LevelLoader: (" + _fileName + ") Portal not found on Map.");
        }

        if (statics.size() == 0) {
            System.out.println("LevelLoader: (WARNING) (" + _fileName + ") StaticEntities List is empty.");
        }

        if (enemies.size() == 0) {
            System.out.println("LevelLoader: (WARNING) (" + _fileName + ") Enemies List is empty");
        }

        if (powerUps.size() == 0) {
            System.out.println("LevelLoader: (WARNING) (" + _fileName + ") PowerUps List is empty");
        } else {
            System.out.println(powerUps.size());
        }
    }
}
