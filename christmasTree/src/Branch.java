import java.awt.*;
import java.awt.geom.AffineTransform;

public class Branch implements XmasShape {
    //coordinates of the center of a branch
    int x;
    int y;
    double scale;
    Color color;

    public Branch() {
        this(0,0,1);
    }

    public Branch(int x, int y, double scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        color = new Color(34, 139, 34);
    }

    @Override
    public void render(Graphics2D g2d) {
        int[] branch_x = {0, 100, 200};
        int[] branch_y = {100, 0, 100};

        g2d.setPaint(color);
        g2d.fillPolygon(branch_x, branch_y, branch_x.length);
        g2d.setPaint(Color.black);
        g2d.drawPolygon(branch_x, branch_y, branch_x.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}