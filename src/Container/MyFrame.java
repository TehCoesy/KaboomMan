package Container;

import States.GameSetting;

import javax.swing.*;

//Creates and maintain the Window of the Game

public class MyFrame extends JFrame {
    //Elements
    private ContainerPanel _containerPanel;

    //Parameters
    private boolean _running;
    private static final int TARGET_FRAME = 60;

    public MyFrame() {
        _containerPanel = new ContainerPanel();

        add(_containerPanel);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle(GameSetting.TITLE);

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
                delta_2 = 0;
            }

            if (delta >= 1) {
                updateElements();
                delta = 0;
            }
        }
    }

    private void tick() {
        _containerPanel.tick();
    }

    private void updateElements() {
        _containerPanel.updateElements();
    }
}
