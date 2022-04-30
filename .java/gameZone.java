
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class GameZone extends JPanel implements ActionListener {
    int width;
    int height;
    Graphics graph;
    int dessin;
    Trajectoire traj;
    Arrow arrow;
    int i = 0;
    Target target;
    Timer arrowTimer;
    int score = 0;

    public GameZone() {
        target = new Target(width - 100, height - 100);
    }

    public GameZone(int w, int h) {
        width = w;
        height = h;
        repaint();
        arrowTimer = new Timer(1, this);
        target = new Target(width - 100, height - 100);
        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        // fond
        g.setColor(new Color(250, 255, 224));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        // herbe
        g.setColor(new Color(0, 225, 0));
        g.fillRect(0, height - 300, (int) width / 4, 300);
        g.setColor(new Color(0, 204, 0));
        g.fillRect((int) width / 4, height - 300, (int) width / 2, 300);
        g.setColor(new Color(0, 153, 0));
        g.fillRect((int) width / 2, height - 300, (int) 3 * width / 4, 300);
        g.setColor(new Color(0, 125, 0));
        g.fillRect((int) 3 * width / 4, height - 300, width, 300);
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, width, height - 300);

        // Personnage
        g.setColor(Color.BLACK);
        int[] xLegL = { 10, 20, 30, 20 };
        int[] yLegL = { height - 30, height - 30, height - 90, height - 90 };
        g.fillPolygon(xLegL, yLegL, xLegL.length);
        int[] xLegR = { 30, 40, 50, 40 };
        int[] yLegR = { height - 90, height - 90, height - 30, height - 30 };
        g.fillPolygon(xLegR, yLegR, xLegR.length);
        // Torse
        g.fillRect(20, height - 130, 20, 40);
        // Tete
        // g.fillOval(20, height - 155, 25, 25);
        // Bras
        // g.fillRect(20, height-130, 20,10 );
        if (arrow == null) {
            g.fillRect(40, height - 130, 40, 10);
            g.fillRect(0, height - 130, 20, 10);
            Shape bow = new Arc2D.Double(80 - 50, height - 125 - 100 / 2, 50, 100, 100, -190, Arc2D.CHORD);
            g2.draw(bow);
        } else {
            /*
             * int
             * newX1=(int)(+40.0*(Math.cos((double)arrow.traj.paramTraj[2].get(0))));//écart
             * entre la nouvelle abscisse de l'éxtrémité du bras et l'ancienne
             * int
             * newY1=(int)(40*(Math.sin((double)arrow.traj.paramTraj[2].get(0))));//écart
             * entre la nouvelle ordonnée de l'éxtrémité du bras et l'ancienne
             * int newX2=(int)(+40.0*(Math.cos((double)arrow.traj.paramTraj[2].get(0))));
             * int newY2=(int)(+40*(Math.sin((double)arrow.traj.paramTraj[2].get(0))));
             * int newX0=(int)(10.0*(Math.sin((double)arrow.traj.paramTraj[2].get(0))));
             * int newY0=(int)(10*(Math.cos((double)arrow.traj.paramTraj[2].get(0))));
             * int[] xArmL = { 40-newX0, 40+newX2-newX0, 40+newX1, 40 };
             * int[] yArmL = { height - 120-newY0, height - 130-newY2, height - 120-newY1,
             * height - 120 };
             * 
             * int
             * newX1R=(int)(20*(Math.cos((double)arrow.traj.paramTraj[2].get(0))));//écart
             * entre la nouvelle abscisse de l'éxtrémité du bras et l'ancienne
             * int
             * newY1R=(int)(20*(Math.sin((double)arrow.traj.paramTraj[2].get(0))));//écart
             * entre la nouvelle ordonnée de l'éxtrémité du bras et l'ancienne
             * int newX2R=(int)(20*(Math.cos((double)arrow.traj.paramTraj[2].get(0))));
             * int newY2R=(int)(20*(Math.sin((double)arrow.traj.paramTraj[2].get(0))));
             * int newX0R=(int)(10.0*(Math.sin((double)arrow.traj.paramTraj[2].get(0))));
             * int newY0R=(int)(10*(Math.cos((double)arrow.traj.paramTraj[2].get(0))));
             * int[] xArmR = {/*HG 20-newX1R, 20, 20+newX0R, 20-newX2R+newX0R };
             * int[] yArmR = { height - 130+newY1R, height - 130, height - 130+newY0R,
             * height - 130+newY2R+newY0R };
             * g.fillPolygon(xArmR, yArmR, xArmR.length);
             * 
             * g.fillPolygon(xArmL, yArmL, xArmL.length);
             * 
             * int
             * newWidth=(int)(25*Math.cos((double)arrow.traj.paramTraj[2].get(0))+100*Math.
             * sin((double)arrow.traj.paramTraj[2].get(0)));
             * int
             * newHeight=(int)(100*Math.cos((double)arrow.traj.paramTraj[2].get(0))+25*Math.
             * sin((double)arrow.traj.paramTraj[2].get(0)));
             * newWidth=75;
             * newHeight=75;
             * g.drawArc((80+newX1+newX2-newX0)/2-newWidth/2, height -
             * 130-newY1-newHeight/2, newWidth, newHeight,
             * (int)(90+Math.toDegrees((double)arrow.traj.paramTraj[2].get(0))),-180);
             * g.drawString("Pos "+((double)arrow.traj.paramTraj[2].get(0))*180/Math.PI, 10,
             * 30);
             * g.drawString("x "+(15+newX1)+" y "+(height - 130-newY1), 10, 40);
             * g.drawString("strt ang "+(int)(90+Math.toDegrees((double)arrow.traj.paramTraj
             * [2].get(0)))+" end ang "+(int)
             * (Math.toDegrees((double)arrow.traj.paramTraj[2].get(0))-180), 10, 50);
             */
            // g.drawString("x "+xArmL[0]+" "+xArmL[1]+" "+xArmL[2]+" "+xArmL[3]+" ", 10,
            // 40);
            // g.drawString("y "+yArmL[0]+" "+yArmL[1]+" "+yArmL[2]+" "+yArmL[3]+" ", 10,
            // 50);

            g.fillRect(26, height - 140, 8, 10);
            Shape armL = new Rectangle.Double(40, height - 130, 40, 10);
            Shape bow = new Arc2D.Double(80 - 50, height - 125 - 100 / 2, 50, 100, 100, -190, Arc2D.CHORD);
            g2.rotate(-(double) arrow.traj.paramTraj[2].get(0), 40, height - 130);
            g2.setPaint(Color.YELLOW);
            g2.fill(armL);
            g2.draw(bow);
            g2.rotate((double) arrow.traj.paramTraj[2].get(0), 40, height - 130);
            Shape armR = new Rectangle.Double(0, height - 130, 20, 10);
            g2.rotate(-(double) arrow.traj.paramTraj[2].get(0), 20, height - 130);
            g2.fill(armR);
            g2.rotate((double) arrow.traj.paramTraj[2].get(0), 20, height - 130);
            Shape head = new Ellipse2D.Float(20, height - 160, 25, 30);
            g2.rotate(-(double) arrow.traj.paramTraj[2].get(0), 20 + 25 / 2, height - 160 + (30 / 2));
            g2.fill(head);
            g2.rotate((double) arrow.traj.paramTraj[2].get(0), 20 + 25 / 2, height - 155 + 25 / 2);

            // g2.rotate((double)arrow.traj.paramTraj[2].get(0),20+25/2, height-155+25/ 2);

            // g2.rotate(-(double)arrow.traj.paramTraj[2].get(0),20+25/2, height-155+25/ 2);

        }
        g.setColor(Color.BLACK);

        // g.drawLine(x1, y1, x2, y2);
        // g.drawLine(x1, y1, x2, y2);
        // Cible
        g.fillRect(target.posX, this.height - 100, 50, 50);

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
        arrow = a;
        arrowTimer.start();

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == arrowTimer && i < arrow.trajSize - 1 && (int) ((int) arrow.traj.paramTraj[0].get(i)
                + (arrow.length / 2) * Math.cos((double) (arrow.traj.paramTraj[2].get(i)))) <= this.width
                && this.height - ((int) ((int) arrow.traj.paramTraj[1].get(i)
                        + (arrow.length / 2) * Math.sin((double) (arrow.traj.paramTraj[2].get(i))))) <= this.height) {
            if (!arrow.collision(target, i)) {
                i++;
                repaint();
            } else {
                score++;
            }
        } else if (e.getSource() == arrowTimer) {
            arrowTimer.stop();
            i = 0;
        }
    }
}
