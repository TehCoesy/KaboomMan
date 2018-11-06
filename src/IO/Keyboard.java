package IO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    //Predefined Keys
    private int K_UP = KeyEvent.VK_UP;
    private int K_DOWN = KeyEvent.VK_DOWN;
    private int K_LEFT = KeyEvent.VK_LEFT;
    private int K_RIGHT = KeyEvent.VK_RIGHT;
    private int K_SPACE = KeyEvent.VK_SPACE;

    //Key States
    private boolean pressUp = false;
    private boolean pressDown = false;
    private boolean pressLeft = false;
    private boolean pressRight = false;
    private boolean pressSpace = false;

    private boolean UP, DOWN, LEFT, RIGHT, SPACE;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == K_UP) {
            if (!pressUp) {
                UP = true;
            }
            pressUp = true;
        }
        if (e.getKeyCode() == K_DOWN) {
            if (!pressDown) {
                DOWN = true;
            }
            pressDown = true;
        }
        if (e.getKeyCode() == K_LEFT) {
            if (!pressLeft) {
                LEFT = true;
            }
            pressLeft = true;
        }
        if (e.getKeyCode() == K_RIGHT) {
            if (!pressRight) {
                RIGHT = true;
            }
            pressRight = true;
        }
        if (e.getKeyCode() == K_SPACE) {
            if (!pressSpace) {
                SPACE = true;
            }
            pressSpace = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == K_UP) {
            pressUp = false;
        }
        if (e.getKeyCode() == K_DOWN) {
            pressDown = false;
        }
        if (e.getKeyCode() == K_LEFT) {
            pressLeft = false;
        }
        if (e.getKeyCode() == K_RIGHT) {
            pressRight = false;
        }
        if (e.getKeyCode() == K_SPACE) {
            pressSpace = false;
        }
    }

    public boolean getUp() {
        if (UP) {
            UP = false;
            return true;
        } else {
            return false;
        }
    }
    public boolean getDown() {
        if (DOWN) {
            DOWN = false;
            return true;
        } else {
            return false;
        }
    }
    public boolean getLeft() {
        if (LEFT) {
            LEFT = false;
            return true;
        } else {
            return false;
        }
    }
    public boolean getRight() {
        if (RIGHT) {
            RIGHT = false;
            return true;
        } else {
            return false;
        }
    }
    public boolean getSpace() {
        if (SPACE) {
            SPACE = false;
            return true;
        } else {
            return false;
        }
    }
}
