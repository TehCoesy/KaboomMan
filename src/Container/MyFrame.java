package Container;

import Core.Game;

import javax.swing.*;
import java.awt.*;

//Creates and maintain the Window of the Game

public class MyFrame extends JFrame {

    private JPanel _containerPane;
    private Game _game;

    public MyFrame() {
        _containerPane = new JPanel(new BorderLayout());

        GamePanel _gamePanel = new GamePanel(this);

        add(_gamePanel);

        _game = _gamePanel.getGame();

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        _game.start();
    }
}
