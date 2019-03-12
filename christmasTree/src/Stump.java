import java.awt.*;
import java.awt.geom.AffineTransform;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;

public class Stump implements XmasShape{
    int x;
    int y;
    double scale;
    Color fillColor;
    Color lineColor;

    public Stump() {
        this(0,0,1);
    }

    public Stump(int x, int y, double scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        lineColor = Color.black;
        fillColor = new Color(160,82,45);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setPaint(fillColor);
        g2d.fillRect(0, 0, 30, 50);
        g2d.setPaint(lineColor);
        g2d.drawRect(0,0,30,50);

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
