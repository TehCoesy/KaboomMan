package Core;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class MyCanvas extends Canvas {
    protected Vector2i size = new Vector2i();

    public void renderCanvas() {
        BufferStrategy _strategy = this.getBufferStrategy();

        if (_strategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = _strategy.getDrawGraphics();

        clearScreen(g);
        renderElement(g);

        g.dispose();
        _strategy.show();
    }

    //Override this method to draw stuff.
    protected void renderElement(Graphics g) {

    }

    private void clearScreen(Graphics g) {
        g.clearRect(0,0,this.size.getX(),this.size.getY());
    }
}
