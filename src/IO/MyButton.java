package IO;

import Core.Vector2i;

import java.awt.*;

public class MyButton {
    public Color bodyColor = Color.RED, textColor = Color.WHITE;
    public Vector2i pos,size;
    public String text;
    public int text_size = 40;
    public int border = 4;

    private Vector2i textOffset;

    public MyButton(Vector2i pos, Vector2i size) {
        this.pos = pos; this.size = size;
    }

    public void highlight() {
        this.textColor = Color.YELLOW;
    }

    public void unhighlight() {
        this.textColor = Color.WHITE;
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(border));
        g2d.drawRect(this.pos.getX(), this.pos.getY(), this.size.getX(), this.size.getY());

        g.setColor(bodyColor);
        g.fillRect(this.pos.getX() + border / 2, this.pos.getY() + border / 2, this.size.getX() - border, this.size.getY() - border);

        g.setColor(textColor);
        g.setFont(new Font("TimesRoman", Font.PLAIN, text_size));
        g.drawString(text, this.pos.getX() + this.size.getX() / 2, this.pos.getY() + this.size.getY() / 2);
    }

    private void getTextPosition() {

    }
}
