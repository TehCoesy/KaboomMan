package IO;


import Container.ContainerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class KeyBinding {
    //Predefined Keys
    private int K_UP = KeyEvent.VK_UP;
    private int K_DOWN = KeyEvent.VK_DOWN;
    private int K_LEFT = KeyEvent.VK_LEFT;
    private int K_RIGHT = KeyEvent.VK_RIGHT;
    private int K_SPACE = KeyEvent.VK_SPACE;

    Keyboard keys;
    ContainerPanel myPanel;

    public KeyBinding(ContainerPanel panel, Keyboard keys) {
        this.keys = keys;
        this.myPanel = panel;
        setKeys();
    }

    private void setKeys() {
        InputMap input = myPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actions = myPanel.getActionMap();

        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "pressUp");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "releaseUp");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "pressDown");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "releaseDown");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "pressLeft");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "releaseLeft");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "pressRight");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "releaseRight");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "pressSpace");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "releaseSpace");

        actions.put("releaseUp", new ReleaseButton(K_UP, keys));
        actions.put("pressUp", new PressButton(K_UP, keys));
        actions.put("releaseDown", new ReleaseButton(K_DOWN, keys));
        actions.put("pressDown", new PressButton(K_DOWN, keys));
        actions.put("releaseLeft", new ReleaseButton(K_LEFT, keys));
        actions.put("pressLeft", new PressButton(K_LEFT, keys));
        actions.put("releaseRight", new ReleaseButton(K_RIGHT, keys));
        actions.put("pressRight", new PressButton(K_RIGHT, keys));
        actions.put("releaseSpace", new ReleaseButton(K_SPACE, keys));
        actions.put("pressSpace", new PressButton(K_SPACE, keys));
    }
}

class PressButton extends AbstractAction {
    Keyboard key;
    int identifier;

    public PressButton(int identifier,Keyboard key) {
        this.key = key;
        this.identifier = identifier;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        key.keyPressed(identifier);
    }
}

class ReleaseButton extends AbstractAction {
    Keyboard key;
    int identifier;

    public ReleaseButton(int identifier, Keyboard key) {
        this.key = key;
        this.identifier = identifier;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        key.keyReleased(identifier);
    }
}
