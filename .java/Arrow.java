import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Arrow {

    double weight;
    int length;
    int posX; // position en pixels axe x DU CENTRE DE GRAVITE
    int posY; // position en pixels axe y DU CENTRE DE GRAVITE
    double angle; // Angle du vecteur vitesse avec l'axe x
    double windX; // force du vent en N
    double speed; // vitesse
    boolean reachedTarget;
    Trajectoire traj;
    Color arrowColor;
    JLabel arrowImage;
    int positionNumber;
    int trajSize;
    int windSpeed;
    

    /**
     * constructeur pour la flèche
     * 
     * @param weight    poids de la flèche
     * @param posX      position du centre de gravité en X
     * @param posY      position du centre de gravité en Y
     * @param angleInit angle initial de lancer
     * @param speedInit vitesse de lancer
     * @param c         couleur de la flèche en fonction du type de flèche
     * @param windSpeed
     */
    public Arrow(int weight, int posX, int posY, double angleInit, double speedInit, int windSpeed, Color c)  {
        this.posX = posX;
        this.posY = posY;
        this.angle = angleInit;
        this.weight = weight;
        this.speed = speedInit;
        this.arrowColor = Color.BLACK;
        this.windSpeed=windSpeed;
        trajectory(posX,posY);
        this.traj = new Trajectoire(angleInit, speedInit, windSpeed, posY, posX);
        this.reachedTarget = false;
        length=50;
        positionNumber=0;
        trajSize=traj.paramTraj[2].size();
        
        //ImageIcon icon =new ImageIcon("arrow.png");
        //arrowImage.setIcon(icon);
    }
    public Arrow(int weight, double angleInit, double speedInit, int windSpeed, Color c)  {
        this.angle = angleInit;
        this.weight = weight;
        this.speed = speedInit;
        this.arrowColor = Color.BLACK;
        this.reachedTarget = false;

        length=50;
        positionNumber=0;
        trajSize=traj.paramTraj[2].size();
        
        //ImageIcon icon =new ImageIcon("arrow.png");
        //arrowImage.setIcon(icon);
    }
    public Arrow(){
        this.arrowColor = Color.BLACK;
        this.reachedTarget = false;
        length=50;
        positionNumber=0;
        //trajSize=traj.paramTraj[2].size();
    }
    

    /*
     * public Arrow(int weight, int posX, int posY, double angle) {
     * this.posX = posX;
     * this.posY = posY;
     * this.weight = weight;
     * this.angle = angle;
     * }
     */
    public void trajectory(int x,int y){
        this.traj = new Trajectoire(this.angle, this.speed, windSpeed, y, x);
    }
    public boolean nextPos(Target target,int width,int height) {
        boolean exist=false;
        if (positionNumber<trajSize && !collision(target) && inScreen(width, height)) {
            this.posX = (int) traj.paramTraj[0].get(positionNumber);
            this.posY = (int) traj.paramTraj[1].get(positionNumber);
            this.angle = (double) traj.paramTraj[2].get(positionNumber);
            exist=true;
            positionNumber++;
        } else {

        }
        return exist;
    }
    public boolean inScreen(int width, int height){
        boolean b=false;
        if(positionNumber<trajSize){
            b=((int) ((int) traj.paramTraj[0].get(positionNumber)+ (length / 2) * Math.cos((double) (traj.paramTraj[2].get(positionNumber)))) <= width
                && height - ((int) ((int)traj.paramTraj[1].get(positionNumber)+ (length / 2) * Math.sin((double) (traj.paramTraj[2].get(positionNumber))))) <= height);
        }
        return b;
    }

    public boolean collision(Target target) {
        boolean collision = false;
         if ( this.posX  == target.posX && this.posY  <= target.posY
                && this.posY >= target.posY-target.side ) {
            collision = true;
            speed = 0;
            reachedTarget = true;
        }

        return collision;
    }
}
