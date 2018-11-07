package Level;

import Entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
    private List<StaticEntity> statics = new ArrayList<>();
    private Player player;
    private List<Entity> enemies = new ArrayList<>();

    public void loadLevel(String file, int GAME_SIZE) {
        FileReader fileReader = null;
        BufferedReader reader = null;
        try {
            fileReader = new FileReader(file);

            reader = new BufferedReader(fileReader);
        } catch (IOException e) {
            System.out.println("Cannot resolve file. (LevelLoader.java)");
            return;
        }

        String line = null;

        try {
            for (int i = 0; i < GAME_SIZE; i++) {
                line = reader.readLine();
                for (int k = 0; k < GAME_SIZE; k++) {
                    char input = line.charAt(k);
                    getEntity(input, i, k);
                }
            }
        } catch (Exception e){
            System.out.println("Something wrong with parsing level (LevelLoader.java)");
            e.printStackTrace();
        }

    }

    public List<StaticEntity> getStatics() {
        return this.statics;
    }

    private void getEntity(char input, int posY, int posX) {
        switch (input) {
            case '#': {
                Wall wall = new Wall();
                wall.setPosition(posX,posY);
                statics.add(wall);
                return;
            }
            default: return;
        }
    }
}
