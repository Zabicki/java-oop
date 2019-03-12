import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;

public class DrawPanel extends JPanel {
    private List<XmasShape> shapes = new ArrayList<>();

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D)g;
        super.paintComponent(g2d);
        for(XmasShape s:shapes){
            s.draw(g2d);
        }
    }
    DrawPanel(){
        setBackground(new Color(0,0,50));
    }
    void addElement(XmasShape newElement) {
        shapes.add(newElement);
    }
}
