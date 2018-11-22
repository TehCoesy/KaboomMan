package Core;

import IO.Keyboard;
import IO.MyButton;
import Graphics.SpriteBuilder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends MyCanvas {
    private int currentTick = 0;
    private BufferedImage pusheen;
    private int currentState = 1;
    List<MyButton> menuButtons = new ArrayList<>();
    private Keyboard keyboard;

    public MainMenu() {
        setFocusable(true);
        keyboard = new Keyboard();

        addKeyListener(keyboard);

        MyButton startButton = new MyButton(new Vector2i(100,100), new Vector2i(150,150));
        startButton.text = "Start";
        //menuButtons.add(startButton);

        MyButton exitButton = new MyButton(new Vector2i(100,100), new Vector2i(150,150));
        exitButton.text = "Exit";
        //menuButtons.add(exitButton);
    }

    @Override
    public void renderElement(Graphics g) {
        for (MyButton element : menuButtons) {
            element.render(g);
        }

        //PlaceHolder Render
        pusheen = SpriteBuilder.getSpriteImage("Data/Sprite/pusheen.png");
        g.setColor(Color.GREEN);
        g.fillRect(280,220,300,300);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(pusheen, 400,400, null);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g2d.drawString("PlaceHolder Menu", 300,300);
        g2d.drawString("Press Space to continue...", 270,350);



        g2d.dispose();
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
