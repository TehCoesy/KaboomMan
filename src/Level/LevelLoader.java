package Level;

import Entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
    private List<StaticEntity> staticEntities = new ArrayList<>();
    private Player player;
    private List<Entity> enemies = new ArrayList<>();

    public void loadLevel(String file, int BLOCK_SIZE) {
        FileReader fileReader = null;
        BufferedReader reader = null;
        try {
            fileReader = new FileReader(file);

            reader = new BufferedReader(fileReader);
        } catch (IOException e) {
            return;
        }

        String line = null;

        try {
            for (int i = 0; i < BLOCK_SIZE; i++) {
                line = reader.readLine();
                for (int k = 0; k < BLOCK_SIZE; k++) {
                    char input = line.charAt(k);
                    Entity newEntity = getEntity(input);
                }
            }
        } catch (Exception e){
            System.out.println("Something wrong with parsing level (LevelLoader.java)");
        }

    }

    public List<StaticEntity> getStatics() {
        return this.staticEntities;
    }

    private Entity getEntity(char input) {
        switch (input) {
            case '#': return new Wall();
            default: return null;
        }
    }
}
