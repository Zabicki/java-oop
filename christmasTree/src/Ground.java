import java.awt.*;

public class Ground implements XmasShape{
    int x;
    int y;
    double scale;
    Color color;

    public Ground(Color color){
        x = 0;
        y = 0;
        scale = 1;
        this.color = color;
    }

    @Override
    public void render(Graphics2D g2d) {
        int[] branch_x = {0, 1000, 1000, 0};
        int[] branch_y = {450, 470, 700, 700};

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
