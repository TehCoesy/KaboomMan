package IO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class Keyboard implements KeyListener {
    //Predefined Keys
    private int UP = KeyEvent.VK_UP;
    private int DOWN = KeyEvent.VK_DOWN;
    private int LEFT = KeyEvent.VK_LEFT;
    private int RIGHT = KeyEvent.VK_RIGHT;
    private int SPACE = KeyEvent.VK_SPACE;

    public boolean up, down, left, right, space;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == UP) {
            up = true;
        }
        if (e.getKeyCode() == DOWN) {
            down = true;
        }
        if (e.getKeyCode() == LEFT) {
            left = true;
        }
        if (e.getKeyCode() == RIGHT) {
            right = true;
        }
        if (e.getKeyCode() == SPACE) {
            space = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == UP) {
            up = false;
            System.out.println("UP");
        }
        if (e.getKeyCode() == DOWN) {
            down = false;
            System.out.println("DOWN");
        }
        if (e.getKeyCode() == LEFT) {
            left = false;
            System.out.println("LEFT");
        }
        if (e.getKeyCode() == RIGHT) {
            right = false;
            System.out.println("RIGHT");
        }
        if (e.getKeyCode() == SPACE) {
            space = false;
            System.out.println("SPACE");
        }
    }
}
