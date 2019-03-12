import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBallsPanel extends JPanel {

    Random rand = new Random();
    AnimationThread animationThread = new AnimationThread();
    List<Ball> balls = new ArrayList<>();

    static class Ball{
        int x;
        int y;
        double vx;
        double vy;
        Color color;

        public Ball() {
            Random rand = new Random();
            x = rand.nextInt(500) + 30;
            y = rand.nextInt(500) + 30;
            vx = rand.nextInt(3) + 2;
            vy = rand.nextInt(3) + 2;
            color = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat()); //rgb
        }
    }

    class AnimationThread extends Thread{
        synchronized void wakeup(){
            suspend=false;
            notify();
        }
        void safeSuspend() {
            suspend = true;
        }
        boolean suspend = true;
        public void run(){
            for(;;){
                for (Ball b : balls) {
                    b.x += b.vx;
                    b.y += b.vy;

                    if (b.x > 660 || b.x < 0) {
                        b.vx = -b.vx;
                    }
                    if (b.y > 600 || b.y < 0) {
                        b.vy = -b.vy;
                    }
                }
                repaint();
                try {
                    sleep(10);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(this){
                    try{
                        if(suspend){
                            System.out.println("suspending");
                            wait();
                        }
                    }
                    catch(InterruptedException e){}
                }
            }
        }
    }

    BouncingBallsPanel(){
        animationThread.start();
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        setOpaque(false);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d= (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Ball b : balls) {
            g2d.setPaint(b.color);
            g2d.fillOval(b.x,b.y,30,30);
            g2d.setPaint(Color.black);
            g2d.drawOval(b.x,b.y,30,30);
        }
    }

    void onStart(){
        animationThread.wakeup();
        System.out.println("Start or resume animation thread");
    }

    void onStop(){
        animationThread.safeSuspend();
        System.out.println("Suspend animation thread");
    }

    void onPlus(){
        System.out.println("Add a ball");
        if (balls.isEmpty()) {
            animationThread.wakeup();
        }
        balls.add(new Ball());
    }

    void onMinus(){
        System.out.println("Remove a ball");
        if (balls.size() > 1) {
            balls.remove(rand.nextInt(balls.size()-1));
        }
        else if (balls.size() == 1){
            balls.remove(0);
            repaint();
            animationThread.safeSuspend();
        }
    }
}