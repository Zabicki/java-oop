import java.awt.*;

public class Star implements XmasShape{
    int x;
    int y;
    double scale;
    Color color;

    public Star() {
        this(0,0,1);
    }

    public Star(int x, int y, double scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        color = Color.yellow;
    }

    @Override
    public void render(Graphics2D g2d) {
        int[] starX = new int[12];
        int[] starY = new int[12];

        double alpha = (2 * Math.PI) / 10;
        int radius = 12;
        int[] topLeftCorner = {0, 0};
        for (int i = 11; i != 0; --i) {
            int r = radius * (i % 2 + 1) / 2;
            double omega = alpha * i;
            starX[i] = (int) (r * Math.sin(omega)) + topLeftCorner[0];
            starY[i] = (int) (r * Math.cos(omega)) + topLeftCorner[1];
        }
        starX[0] = starX[11];
        starY[0] = starY[11];
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(color);
        g2d.fillPolygon(starX, starY, starX.length);
        g2d.setPaint(color.darker());
        g2d.drawPolygon(starX, starY, starX.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
