package Container;

import Audio.AudioPlayer;
import Core.Game;

import javax.swing.*;
import java.awt.*;

//Creates and maintain the Window of the Game

public class MyFrame extends JFrame {

    //Elements
    private JPanel _containerPane;
    private Game _game;

    //Parameters
    private boolean _running;
    private static final int TARGET_FRAME = 60;

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

        //_game.start();

        _game.requestFocus();
        runLoop();
    }

    private void runLoop() {
        _running = true;

        long lastTime = System.nanoTime();
        final double TARGET = 1000000000 / TARGET_FRAME;
        double delta = 0;

        while (_running) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / TARGET;
            lastTime = currentTime;

            if (delta >= 1) {
                tick();
                updateElements();
                delta--;
            }
        }
    }

    private void tick() {
        _game.tick();
    }

    private void updateElements() {
        _game.updateGame();
        _game.renderScreen();
    }
}
