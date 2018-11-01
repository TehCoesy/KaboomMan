package Core;

import Container.MyFrame;

import java.awt.*;

//Governs and runs the Game
//Contain the gameLoop;

public class Game extends Canvas {
    Frame _frame;

    public static final String TITLE = "KaBoomMan";

    public static int WIDTH = 400;
    public static int HEIGHT = 400;

    public Game(MyFrame frame) {
        this._frame = frame;
        _frame.setTitle(TITLE);


    }
}
