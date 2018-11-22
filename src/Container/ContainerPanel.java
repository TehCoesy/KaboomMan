package Container;

import Core.Game;
import Core.MainMenu;
import Core.SplashScreen;
import States.ApplicationSetting;

import javax.swing.*;
import java.awt.*;

public class ContainerPanel extends JPanel {
    private Game _game;
    private SplashScreen _splash;
    private MainMenu _mainMenu;

    private int gameState = 0; //0 = _splash, 1 = _mainMenu, 2 = _game;
    private int requestChangeState = 0;

    public ContainerPanel() {
        setLayout(new BorderLayout());
        setVisible(true);
        setPreferredSize(new Dimension(ApplicationSetting.WIDTH, ApplicationSetting.HEIGHT));
        requestFocus();

        _splash = new SplashScreen(ApplicationSetting.WIDTH, ApplicationSetting.HEIGHT);
        add(_splash);

        //_game = new Game();
        //add(_game);
    }

    public void tick() {
        switch (gameState) {
            case 0: _splash.tick(); break;
            case 1: break;
            case 2: _game.tick(); _game.updateGame(); break;
        }
    }

    public void updateElements() {
        switch (gameState) {
            case 0: _splash.renderCanvas(); break;
            case 1: _mainMenu.renderCanvas(); break;
            case 2:  _game.renderScreen(); break;
        }

        checkState();
    }

    private void checkState() {
        if (_splash != null && gameState == 0) {
            if (_splash.checkDone()) {
                requestChangeState = 2;
            }
        }

        if (gameState == 1) {
            requestChangeState = _mainMenu.getStateRequest();
        }

        if (gameState == 2) {
            requestChangeState = _game.getStateRequest();
        }

        if (requestChangeState != gameState) {
            switch (requestChangeState) {
                case 1: _splash = null; openMenu(); gameState = requestChangeState; break;
                case 2: openGame(); gameState = requestChangeState; break;
            }
        }
    }

    private void openMenu() {
        removeAll();

        _mainMenu = new MainMenu();

        add(_mainMenu);

        repaint();
        revalidate();

        _mainMenu.setVisible(true);
    }

    private void openGame() {
        removeAll();

        _game = new Game();

        add(_game);

        repaint();
        revalidate();

        _game.setVisible(true);
    }

    public Game getGame() { return this._game; }
}
