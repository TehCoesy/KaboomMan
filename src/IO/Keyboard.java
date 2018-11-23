package IO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard{
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

    public void keyPressed(int e) {
        if (e == K_UP) {
            if (!pressUp) {
                UP = true;
            }
            pressUp = true;
        }
        if (e == K_DOWN) {
            if (!pressDown) {
                DOWN = true;
            }
            pressDown = true;
        }
        if (e == K_LEFT) {
            if (!pressLeft) {
                LEFT = true;
            }
            pressLeft = true;
        }
        if (e == K_RIGHT) {
            if (!pressRight) {
                RIGHT = true;
            }
            pressRight = true;
        }
        if (e == K_SPACE) {
            if (!pressSpace) {
                SPACE = true;
            }
            pressSpace = true;
        }
    }

    public void keyReleased(int e) {
        if (e == K_UP) {
            pressUp = false;
            UP = false;
        }
        if (e == K_DOWN) {
            pressDown = false;
            DOWN = false;
        }
        if (e == K_LEFT) {
            pressLeft = false;
            LEFT = false;
        }
        if (e == K_RIGHT) {
            pressRight = false;
            RIGHT = false;
        }
        if (e == K_SPACE) {
            pressSpace = false;
            SPACE = false;
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

    public boolean C_UP() {
        return UP;
    }
    public boolean C_DOWN() {
        return DOWN;
    }
    public boolean C_LEFT() {
        return LEFT;
    }
    public boolean C_RIGHT() {
        return RIGHT;
    }
}
