package Container;

import Core.Game;

import javax.swing.JFrame;

//Creates and maintain the Window of the Game

public class MyFrame extends JFrame {

    private Game _game;

    public MyFrame() {
        GamePanel _gamePanel = new GamePanel(this);

        add(_gamePanel);

        _game = _gamePanel.getGame();

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
