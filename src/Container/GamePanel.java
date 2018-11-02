package Container;

import Core.Game;

import javax.swing.JPanel;
import java.awt.*;

//Create a JPanel on which the Game is rendered;

public class GamePanel extends JPanel {
    private Game _game;

    public GamePanel(MyFrame frame) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Game.WIDTH,Game.HEIGHT));

        _game = new Game(frame);

        add(_game);

        _game.setVisible(true);

        setVisible(true);
        setFocusable(true);
    }

    public Game getGame() {
        return this._game;
    }
}
