package Core;

import IO.MyButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends MyCanvas {
    List<MyButton> menuButtons = new ArrayList<>();

    public MainMenu() {
        MyButton startButton = new MyButton(new Vector2i(100,100), new Vector2i(150,150));
        startButton.text = "Start";
        menuButtons.add(startButton);

        MyButton exitButton = new MyButton(new Vector2i(100,100), new Vector2i(150,150));
        exitButton.text = "Exit";
        menuButtons.add(exitButton);
    }

    @Override
    public void renderElement(Graphics g) {
        for (MyButton element : menuButtons) {
            element.render(g);
        }
    }

    public int getStateRequest() {
        return 1;
    }
}
