
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class GameZone extends JPanel implements ActionListener {

    int width;
    int height;
    Graphics graph;
    int drawingType;
    Arrow arrow;
    Target target;
    Timer arrowTimer;
    Player player;
    boolean shooting;
    int roundNb;
    UI associatedUI;
    Timer playerAnimation;
    long animationStart;
    int maxRound;
    boolean randomTarget;

    public GameZone() {

        target = new Target(width - 100, 100, 50);
        roundNb = 0;
    }

    public GameZone(int w, int h) {
        width = w;
        height = h;
        repaint();
        arrowTimer = new Timer(10, this);
        playerAnimation = new Timer(50, this);
        System.out.println(randomTarget);
        if (randomTarget) {
            target = new Target((int) ((width - 100) - ((width - 100) / 2) * Math.random()),
                    (int) (-Math.random() * (height - 100) + (height - 100)), (int) (Math.random() * (90) + 10));
        } else {
            target = new Target(width - 100, 100, 50);
        }
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
            g2.rotate(-(double) arrow.traj.paramTraj[2].get(0) * player.playerPosition / 10, 40, height - 130);
            g2.setPaint(Color.black);
            g2.fill(armL);
            g2.draw(bow);
            g2.rotate((double) arrow.traj.paramTraj[2].get(0) * player.playerPosition / 10, 40, height - 130);

            Shape armR = new Rectangle.Double(0, height - 130, 20, 10);
            g2.rotate(-(double) arrow.traj.paramTraj[2].get(0) * player.playerPosition / 10, 20, height - 130);
            g2.fill(armR);
            g2.rotate((double) arrow.traj.paramTraj[2].get(0) * player.playerPosition / 10, 20, height - 130);

            Shape head = new Ellipse2D.Float(20, height - 160, 25, 30);
            g2.rotate(-(double) arrow.traj.paramTraj[2].get(0) * player.playerPosition / 10, 20 + 25 / 2,
                    height - 160 + (30 / 2));
            g2.fill(head);
            g2.rotate((double) arrow.traj.paramTraj[2].get(0) * player.playerPosition / 10, 20 + 25 / 2,
                    height - 155 + 25 / 2);

        }
        g.setColor(Color.BLACK);

        // Cible
        g.fillRect(target.posX, height - target.posY, target.side, target.side);
        g.drawString("Nombre de tir: " + roundNb + "/" + maxRound, 10, 20);

        if (drawingType == -1) {
            g.drawString("Bravo! Tu as touché la cible", 100, 200);
        } else if (drawingType == 1) {

            g.setColor(Color.black);
            for (int i = 0; i < arrow.traj.paramTraj[0].size(); i += 2) {
                g.fillOval((int) arrow.traj.paramTraj[0].get(i), this.height - (int) arrow.traj.paramTraj[1].get(i), 2,
                        2);

            }

        } else if (drawingType == 2) {

        } else if (drawingType == 3) {
            g.drawString("Vitesse de la flèche: " + (int) (double) arrow.traj.paramTraj[3].get(arrow.positionNumber)
                    + " m/s", 10, 30);
            g.setColor(arrow.arrowColor);
            g2.rotate(-arrow.angle, (double) arrow.posX, height - (double) arrow.posY);
            g.drawLine(arrow.posX - arrow.length / 2, this.height - arrow.posY, arrow.posX + arrow.length / 2,
                    this.height - arrow.posY);
            g2.rotate(arrow.angle, (double) arrow.posX, height - (double) arrow.posY);

        }

    }

    public void preview(Arrow a, Player p) {
        drawingType = 1;
        arrow = a;
        arrow.positionNumber = 0;
        player = p;
        repaint();
    }

    public void shoot(Arrow a, Player p, int m) {
        roundNb++;
        drawingType = 2;
        arrow = a;
        player = p;
        shooting = true;
        playerAnimation.start();
        animationStart = System.currentTimeMillis();
        maxRound = m;

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playerAnimation) {
            repaint();
            player.playerPosition++;
            if ((System.currentTimeMillis() - animationStart) > 500) {
                playerAnimation.stop();
                drawingType = 3;
                arrowTimer.start();
            }

        } else if (e.getSource() == arrowTimer && arrow.nextPos(target, width, height)) {
            repaint();
        } else if (e.getSource() == arrowTimer && arrow.reachedTarget == true) {
            arrowTimer.stop();
            arrow.positionNumber = 0;
            shooting = false;
            arrow.reachedTarget = false;
            player.playerPosition = 0;
            player.score++;
            if (randomTarget) {
                this.target = new Target((int) ((width - 100) - ((width - 100) / 2) * Math.random()),
                        (int) (-Math.random() * (height - 100) + (height - 100)), (int) (Math.random() * (90) + 10));
            }
            drawingType = -1;
            repaint();

        } else if (e.getSource() == arrowTimer) {
            arrowTimer.stop();
            arrow.positionNumber = 0;
            player.playerPosition = 0;
            shooting = false;
            arrow.reachedTarget = false;
            repaint();
        }

    }
}
