package Container;

import Audio.AudioPlayer;
import Core.Game;
import States.ApplicationSetting;

import javax.swing.*;
import java.awt.*;

//Creates and maintain the Window of the Game

public class MyFrame extends JFrame {
    private int currentState = 0; //0 = Welcome Screen, 1 = Game;

    //Elements
    private JPanel _containerPane;
    private Game _game;
    private WelcomePane welcomePane;
    private GamePanel _gamePanel;

    //Parameters
    private boolean _running;
    private static final int TARGET_FRAME = 60;

    public MyFrame() {
        _containerPane = new JPanel(new CardLayout());

        add(_containerPane);
        _gamePanel = new GamePanel(this);
        //_containerPane.add(_gamePanel);

        welcomePane = new WelcomePane(ApplicationSetting.WIDTH,ApplicationSetting.HEIGHT);
        _containerPane.add(welcomePane);

        _game = _gamePanel.getGame();

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        runLoop();
    }

    private void runLoop() {
        _running = true;

        long lastTime = System.nanoTime();
        final double TARGET = 1000000000 / TARGET_FRAME;
        final double TARGET_TICK = 1000000000 / 60; //1 Tick is 1/60 second;
        double delta = 0, delta_2 = 0;

        while (_running) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / TARGET;
            delta_2 += (currentTime - lastTime) / TARGET_TICK;
            lastTime = currentTime;

            if (delta_2 >= 1) {
                tick();
                delta_2--;
            }

            if (delta >= 1) {
                updateElements();
                delta--;
            }
        }
    }

    private void tick() {
        if (currentState == 0) {
            welcomePane.tick();
        }

        if (currentState == 1) {
            _game.tick();
        }
    }

    private void updateElements() {
        if (currentState == 0) {
            welcomePane.update();
        }
        if (currentState == 0 && welcomePane.isDone()) {
            currentState = 1;
            _containerPane.removeAll();
            _containerPane.repaint();
            _containerPane.revalidate();

            _containerPane.add(_gamePanel);
            _gamePanel.requestFocus();
            //_game.requestFocus();
        }
        if (currentState == 1) {
            _game.updateGame();
            _game.renderScreen();
        }
    }
}
