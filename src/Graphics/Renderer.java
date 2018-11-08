package Graphics;

//TODO: Transfer rendering from Game.java

import Core.Game;
import Entities.Bomb;
import Entities.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Renderer {
    int BLOCK_SIZE;
    private Game _game;

    //Sprites
    private List<Sprite> staticSprite = new ArrayList<>();

    public Renderer(Game game) {
        this._game = game;
        BLOCK_SIZE = _game.getBlockSize();
        initializeStatics();
    }

    public void renderGame(Graphics g) {
        g.clearRect(0,0, _game.WIDTH, _game.HEIGHT);

        drawBackground(g);
        renderStatics(g);
        renderAnimated(g);
        renderBombs(g);

        g.drawImage(_game.player.getSprite(),_game.player.getX(),_game.player.getY(),BLOCK_SIZE,BLOCK_SIZE,null);
    }

    private void initializeStatics() {
        staticSprite.add(new Sprite(SpriteSheet.getSpriteImage("Data/Sprite/wall.png")));
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0,0,_game.WIDTH,_game.HEIGHT);
    }

    private void renderStatics(Graphics g) {
        int n = _game.staticEntities.size();

        for (int i = 0; i < n; i++) {
            if (_game.staticEntities.get(i) instanceof Wall) {
                g.drawImage(staticSprite.get(0).getSprite(),_game.staticEntities.get(i).getX() * BLOCK_SIZE, _game.staticEntities.get(i).getY() * BLOCK_SIZE, BLOCK_SIZE,BLOCK_SIZE,null);
            }
        }
    }

    private void renderAnimated(Graphics g) {

    }

    private void renderBombs(Graphics g) {
        int n = _game.bombs.size();

        for (int i = 0; i < n; i++) {
            Bomb bomb = _game.bombs.get(i);
            g.drawImage(bomb.getStandingSprite(),bomb.getX() * BLOCK_SIZE, bomb.getY() * BLOCK_SIZE, BLOCK_SIZE,BLOCK_SIZE, null);
        }
    }

    public void pauseScreen() {

    }
}
