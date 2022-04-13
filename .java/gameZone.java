
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class gameZone extends JPanel implements ActionListener {
    int width;
    int height;
    Graphics graph;
    int dessin;
    Trajectoire traj;
    Arrow arrow;
    int i = 0;
    Target target;
    Timer arrowTimer;

    public gameZone() {

    }

    public gameZone(int w, int h) {
        width = w;
        height = h;
        repaint();
        arrowTimer = new Timer(10, this);
        target =new Target(width-100,50);
        setVisible(true);
    }

    public void paint(Graphics g) {
        //fond
        g.setColor(new Color(250, 255, 224));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        //herbe
        g.setColor(new Color(0,225,0));
        g.fillRect(0,height-300 , (int)width/4, 300);
        g.setColor(new Color(0,204,0));
        g.fillRect((int)width/4,height-300 , (int)width/2, 300);
        g.setColor(new Color(0,153,0));
        g.fillRect((int)width/2,height-300 , (int)3*width/4, 300);
        g.setColor(new Color(0,125,0));
        g.fillRect((int)3*width/4,height-300 , width, 300);
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0,0 , width, height-300);
        
        //Personnage
        g.setColor(Color.BLACK);
        int[] xLegL = { 10, 20, 30, 20 };
        int[] yLegL = { height-30, height-30, height - 90, height - 90 };
        g.fillPolygon(xLegL, yLegL, xLegL.length);
        int[] xLegR = { 30, 40, 50, 40 };
        int[] yLegR = { height-90, height-90, height - 30, height - 30 };
        g.fillPolygon(xLegR, yLegR, xLegR.length);
        g.fillOval(25, height-200, 50, 50);
        //Cible
        
        if (dessin == 1) {

            g.setColor(Color.black);
            for (int i = 0; i < traj.paramTraj[0].size() - 1; i += 3) {
                g.fillOval((int) (traj.paramTraj[0].get(i)), this.height - (int) (traj.paramTraj[1].get(i)), 3, 3);
            }
        } else if (dessin == 2) {

            g.setColor(arrow.arrowColor);
            if((int) ((int) arrow.traj.paramTraj[0].get(i)+ (arrow.length / 2) * Math.cos((double) (arrow.traj.paramTraj[2].get(i))))<=this.width  && this.height - ((int) ((int) arrow.traj.paramTraj[1].get(i)+ (arrow.length / 2) * Math.sin((double) (arrow.traj.paramTraj[2].get(i)))))<=this.height){
                g.drawLine((int) ((int) arrow.traj.paramTraj[0].get(i)- (arrow.length / 2) * Math.cos((double) (arrow.traj.paramTraj[2].get(i)))),
                    this.height - ((int) ((int) arrow.traj.paramTraj[1].get(i)- (arrow.length / 2) * Math.sin((double) (arrow.traj.paramTraj[2].get(i))))),
                    (int) ((int) arrow.traj.paramTraj[0].get(i)+ (arrow.length / 2) * Math.cos((double) (arrow.traj.paramTraj[2].get(i)))),
                    this.height - ((int) ((int) arrow.traj.paramTraj[1].get(i)+ (arrow.length / 2) * Math.sin((double) (arrow.traj.paramTraj[2].get(i))))));
            g.drawString("" + i, 10, 10);
            g.drawString("" + (double) (arrow.traj.paramTraj[2].get(i)), 20, 20);
            }
            

        }
        

    }

    public void preview(Trajectoire t) {
        dessin = 1;
        traj = t;
        i = 0;
        repaint();

    }

    public void shoot(Arrow a) {
        dessin = 2;
        arrow = a;
        arrowTimer.start();
        

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == arrowTimer && i < arrow.trajSize && !arrow.collision(target, i+1)) {
            i++;
            repaint();
        } else if (e.getSource() == arrowTimer && (i >= arrow.trajSize || !arrow.collision(target, i+1))) {
            arrowTimer.stop();
            i = 0;
        }else if((int) ((int) arrow.traj.paramTraj[0].get(i)+ (arrow.length / 2) * Math.cos((double) (arrow.traj.paramTraj[2].get(i))))<=this.width  && this.height - ((int) ((int) arrow.traj.paramTraj[1].get(i)+ (arrow.length / 2) * Math.sin((double) (arrow.traj.paramTraj[2].get(i)))))<=this.height){
            arrowTimer.stop();
            i = 0;
        }
    }
}
