
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
        /*
        int[] xGrass = { 0,(int) width/4,(int) width/4 ,0  };
        int[] yGrass = { height, height, height- 250 , height-200  };
        g.fillPolygon(xGrass, yGrass, xGrass.length);
        
        int[] xGrass1 = { (int)width/4,(int) width/2,(int) width/2 ,(int)width/4  };
        int[] yGrass1 = { height, height, height- 300 , height-250  };
        g.fillPolygon(xGrass1, yGrass1, xGrass1.length);
        
        int[] xGrass2 = {(int) width/2,(int) 3*width/4,(int) 3*width/4 ,(int) width/2  };
        int[] yGrass2 = { height, height, height- 350 , height-300  };
        g.fillPolygon(xGrass2, yGrass2, xGrass2.length);
        
        int[] xGrass3 = {(int) 3*width/4, width, width ,(int) 3*width/4  };
        int[] yGrass3 = { height, height, height- 400 , height-350  };
        g.fillPolygon(xGrass3, yGrass3, xGrass3.length);
        */
        //Personnage
        g.setColor(Color.BLACK);
        int[] xPoints = { 10, 30, 50, 30 };
        int[] yPoints = { height, height, height - 60, height - 60 };
        g.fillPolygon(xPoints, yPoints, xPoints.length);
        g.fillOval(200, 200, 50, 50);
        if (dessin == 1) {

            g.setColor(Color.black);
            for (int i = 0; i < traj.paramTraj[0].size() - 1; i += 3) {
                g.fillOval((int) (traj.paramTraj[0].get(i)), this.height - (int) (traj.paramTraj[1].get(i)), 3, 3);
            }
        } else if (dessin == 2) {

            g.setColor(arrow.arrowColor);
            g.drawLine(
                    (int) ((int) arrow.traj.paramTraj[0].get(i)
                            - (arrow.length / 2) * Math.cos((double) (arrow.traj.paramTraj[2].get(i)))),
                    this.height - ((int) ((int) arrow.traj.paramTraj[1].get(i)
                            - (arrow.length / 2) * Math.sin((double) (arrow.traj.paramTraj[2].get(i))))),
                    (int) ((int) arrow.traj.paramTraj[0].get(i)
                            + (arrow.length / 2) * Math.cos((double) (arrow.traj.paramTraj[2].get(i)))),
                    this.height - ((int) ((int) arrow.traj.paramTraj[1].get(i)
                            + (arrow.length / 2) * Math.sin((double) (arrow.traj.paramTraj[2].get(i))))));
            g.drawString("" + i, 10, 10);
            g.drawString("" + (double) (arrow.traj.paramTraj[2].get(i)), 20, 20);

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
        arrowTimer.start();
        arrow = a;

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == arrowTimer && i < arrow.trajSize && !arrow.collision(target, i+1)) {
            i++;
            repaint();
        } else if (e.getSource() == arrowTimer && (i >= arrow.trajSize || !arrow.collision(target, i+1))) {
            arrowTimer.stop();
            i = 0;
        }
    }
}
