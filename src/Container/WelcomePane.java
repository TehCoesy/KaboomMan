package Container;

import Core.MyCanvas;
import Graphics.SpriteBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class WelcomePane extends JPanel {
    private boolean done;
    private PanelCanvas panelCanvas;

    public WelcomePane(int sizeX, int sizeY) {
        this.panelCanvas = new PanelCanvas(sizeX, sizeY);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(sizeX, sizeY));

        setBackground(Color.WHITE);
        add(panelCanvas);

        setVisible(true);
        setFocusable(true);
    }

    public void update() {
        panelCanvas.renderCanvas();
        if (panelCanvas.checkDone()) {
            this.done = true;
        }
    }

    public void tick() {
        panelCanvas.tick();
    }

    public boolean isDone() { return this.done; }
}

class PanelCanvas extends MyCanvas {
    private BufferedImage welcomeLogo;
    private int currentTick = 0, duration = 5 * 60; //5 seconds
    private float opacity = 0;
    private int triplet = duration / 3;
    private float step = 1f / triplet;

    public PanelCanvas(int sizeX, int sizeY) {
        this.size.set(sizeX, sizeY);
        this.welcomeLogo = SpriteBuilder.getSpriteImage("Data/Sprite/pusheen.png");
    }

    @Override
    protected void renderElement(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0, this.size.getX(), this.size.getY());

        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2d.drawImage(welcomeLogo, 250, 300, null);
        g2d.dispose();
    }

    public void tick() {
        if (currentTick < duration) {
            currentTick++;
        }
        calculateOpacity();
    }

    private void calculateOpacity() {
        if (currentTick < triplet) {
            if (opacity + step < 1) {
                opacity += step;
            }
        }

        if (currentTick > triplet * 2) {
            if (opacity - step > 0) {
                opacity -= step;
            }
        }
    }

    public boolean checkDone() {
        if (currentTick == duration) {
            return true;
        } else {
            return false;
        }
    }
}
