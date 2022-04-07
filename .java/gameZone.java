
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
    Timer arrowTimer;
    int i = 0;

    public gameZone() {

    }

    public gameZone(int w, int h) {
        width = w;
        height = h;
        repaint();
        arrowTimer = new Timer(10, this);
        setVisible(true);
    }

    public void paint(Graphics g) {

        if (dessin == 1) {
            g.setColor(new Color(250, 255, 224));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(Color.black);
            for (int i = 0; i < traj.paramTraj[0].size() - 1; i += 3) {
                g.fillOval((int) (traj.paramTraj[0].get(i)), this.height - (int) (traj.paramTraj[1].get(i)), 3, 3);
            }
        } else if (dessin == 2) {

            g.setColor(new Color(250, 255, 224));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
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
        int[] xPoints = { 10, 30, 30, 50 };
        int[] yPoints = { height, height, height - 60, height - 60 };
        g.fillPolygon(xPoints, yPoints, xPoints.length);
        g.fillOval(200, 200, 50, 50);

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
        if (e.getSource() == arrowTimer && i < arrow.traj.paramTraj[0].size()) {
            i++;
            repaint();
        } else if (e.getSource() == arrowTimer && i >= arrow.traj.paramTraj[0].size()) {
            arrowTimer.stop();
            i = 0;
        }
    }
}
