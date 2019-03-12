import javax.swing.*;

import java.awt.*;

import static java.awt.Color.yellow;

public class Main {
    public static void main(String[] args) {
        DrawPanel panel = new DrawPanel();
        panel.addElement(new Ground(Color.white));
        panel.addElement(new Stump(450,500,2));
        panel.addElement(new Branch(275,302,2));
        panel.addElement(new Branch(330,200,1.4));
        panel.addElement(new Branch(365,120,1));
        panel.addElement(new Bubble(490,160,1,Color.yellow,Color.red));
        panel.addElement(new Bubble(450,180,1,Color.yellow,Color.blue));
        panel.addElement(new Bubble(430,220,1,Color.yellow,Color.pink));
        panel.addElement(new Bubble(470,280,1,Color.yellow,Color.yellow));
        panel.addElement(new Bubble(435,360,1,Color.yellow,Color.red));
        panel.addElement(new Bubble(350,310,1,Color.yellow,Color.orange));
        panel.addElement(new Bubble(550,310,1,Color.yellow,Color.magenta));
        panel.addElement(new Bubble(490,400,1,Color.yellow,Color.pink));
        panel.addElement(new Bubble(400,410,1,Color.yellow,Color.red));
        panel.addElement(new Bubble(430,440,1,Color.yellow,Color.magenta));
        panel.addElement(new Bubble(530,480,1,Color.yellow,Color.orange));
        panel.addElement(new Bubble(300,480,1,Color.yellow,Color.yellow));
        panel.addElement(new Bubble(650,480,1,Color.yellow,Color.pink));
        panel.addElement(new Star(465,105,3.5));
        panel.addElement(new Snow());

        JFrame frame = new JFrame("Choinka");
        frame.setContentPane(panel);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
