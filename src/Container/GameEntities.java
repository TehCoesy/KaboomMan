package Container;

import Entities.*;
import Entities.Enemies.*;
import Entities.Statics.*;
import Graphics.Explosion;

import java.util.ArrayList;
import java.util.List;

public class GameEntities {
    public Player player;
    public Portal portal;
    public List<StaticEntity> staticEntities = new ArrayList<>();
    public List<Enemy> enemies = new ArrayList<>();
    public List<Explosion> explosions = new ArrayList<>();
    public List<Bomb> bombs = new ArrayList<>();
    public List<PowerUp> powerUps = new ArrayList<>();

    public void update() {
        garbageCollection(staticEntities);
        garbageCollection(bombs);
        collectExplosions();
        garbageCollection(enemies);
        garbageCollection(powerUps);
    }

    private void collectExplosions() {
        List<Explosion> removal = new ArrayList<>();

        for (Explosion element : explosions) {
            if (element.done) {
                removal.add(element);
            }
        }

        explosions.removeAll(removal);
    }

    private <T extends Entity> void garbageCollection(List<T> entityList) {
        List<T> removal = new ArrayList<>();

        for (T element : entityList) {
            if (element.isDone()) {
                removal.add(element);
            }
        }

        entityList.removeAll(removal);
    }
}
