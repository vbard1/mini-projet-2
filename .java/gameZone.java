
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class GameZone extends JPanel implements ActionListener {
    int width;
    int height;
    Graphics graph;
    int dessin;
    Arrow arrow;
    Target target;
    Timer arrowTimer;
    Player player;
    boolean shooting;
    int roundNb;
    UI associatedUI;

    public GameZone(UI ui) {
        associatedUI = ui;
        target = new Target((int) (Math.random() * (associatedUI.getWidth() - 100)), (int) (Math.random() * (100)),
                (int) (Math.random() * (50)));
        roundNb = 0;
    }

    public GameZone(int w, int h, UI ui) {
        associatedUI = ui;
        width = w;
        height = h;
        // arrow=new Arrow();
        repaint();
        arrowTimer = new Timer(1, this);
        target = new Target((int) (Math.random() * (associatedUI.getWidth() - 100)), (int) (Math.random() * (100)),
                (int) (Math.random() * (50) + 10));
        setVisible(true);
        roundNb = 0;

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
        g.fillRect(target.posX, height - target.posY, target.side, target.side);
        g.drawString("Nombre de tir: " + roundNb, width - 100, 10);
        if (dessin == 1) {

            g.setColor(Color.black);
            for (int i = 0; i < arrow.traj.paramTraj[0].size(); i += 2) {
                g.fillOval((int) arrow.traj.paramTraj[0].get(i), this.height - (int) arrow.traj.paramTraj[1].get(i), 2,
                        2);

            }

        } else if (dessin == 2) {

            g.setColor(arrow.arrowColor);
            g2.rotate(-arrow.angle, (double) arrow.posX, height - (double) arrow.posY);
            g.drawLine(arrow.posX - arrow.length / 2, this.height - arrow.posY, arrow.posX + arrow.length / 2,
                    this.height - arrow.posY);
            g2.rotate(arrow.angle, (double) arrow.posX, height - (double) arrow.posY);
            g.drawString("" + arrow.positionNumber, 10, 10);
            g.drawString("" + arrow.angle, 20, 20);
            g.drawString("" + arrow.posX + " " + arrow.posY, 10, 30);

        }

    }

    public void preview(Arrow a, Player p) {
        dessin = 1;
        arrow = a;
        arrow.positionNumber = 0;
        player = p;
        repaint();
    }

    public void shoot(Arrow a, Player p) {
        roundNb++;
        dessin = 2;
        arrow = a;
        player = p;
        shooting = true;
        arrowTimer.start();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == arrowTimer && arrow.nextPos(target, width, height)) {
            repaint();
        } else if (e.getSource() == arrowTimer && arrow.reachedTarget == true) {
            arrowTimer.stop();
            arrow.positionNumber = 0;
            shooting = false;
            arrow.reachedTarget = false;
            player.score++;
            this.target = new Target((int) (Math.random() * (associatedUI.getWidth() - 100)),
                    (int) (Math.random() * (100)),
                    (int) (Math.random() * (50) + 10));
            dessin = 0;
            repaint();

        } else if (e.getSource() == arrowTimer) {
            arrowTimer.stop();
            arrow.positionNumber = 0;
            shooting = false;
            arrow.reachedTarget = false;
        }
    }
}
