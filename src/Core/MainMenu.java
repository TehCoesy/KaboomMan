package Core;

import IO.Keyboard;
import IO.MyButton;
import Graphics.SpriteBuilder;
import Graphics.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Container.GameEntities;
import Level.LevelLoader;
import States.GameSetting;

public class MainMenu extends MyCanvas {
    //BACKGROUND
    private GameEntities backgroundEntities;
    private Renderer backgroundRenderer;
    private GameSetting settings;

    private int currentTick = 0;
    private BufferedImage pusheen;
    private int currentState = 1;
    List<MyButton> menuButtons = new ArrayList<>();
    private Keyboard keyboard;

    public MainMenu(Keyboard key) {
        setFocusable(true);
        this.keyboard = key;

        MyButton startButton = new MyButton(new Vector2i(100,100), new Vector2i(150,80));
        startButton.text = "Start";
        menuButtons.add(startButton);

        MyButton exitButton = new MyButton(new Vector2i(100,100), new Vector2i(150,80));
        exitButton.text = "Exit";
        //menuButtons.add(exitButton);

        initializeBackground();
    }

    @Override
    public void renderElement(Graphics g) {
        backgroundRenderer.renderGame(g);


        //PlaceHolder Render
        pusheen = SpriteBuilder.getSpriteImage("Data/Sprite/pusheen.png");
        g.setColor(Color.GREEN);
        g.fillRect(280,220,300,300);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(pusheen, 400,400, null);

        for (MyButton element : menuButtons) {
            element.render(g);
        }
    }

    public void initializeBackground() {
        LevelLoader _loader = new LevelLoader();
        try {
            _loader.loadLevel("mainMenu.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        backgroundEntities = _loader.getEntities();
        settings = _loader.getSettings();

        backgroundRenderer = new Renderer(backgroundEntities,null, settings, null);
    }
    public void tick() {
        currentTick++;
        if (keyboard.getSpace()) {
            currentState = 2;
        }
    }

    public int getStateRequest() {
        return currentState;
    }
}
