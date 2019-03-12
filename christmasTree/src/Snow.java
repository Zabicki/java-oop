import java.awt.*;
import java.util.Random;

public class Snow implements XmasShape {

    @Override
    public void render(Graphics2D g2d) {
        Random ran = new Random();
        for (int i = 0; i < 500; i++) {
            int x = ran.nextInt(990) + 5;
            int y = ran.nextInt(690) + 5;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setPaint(Color.white);
            g2d.fillOval(x,y,5,5);
            g2d.setPaint(Color.black);
            g2d.drawOval(x,y,5,5);
        }
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(0, 0);
        g2d.scale(1,1);
    }
}
