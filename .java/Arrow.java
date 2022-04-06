import java.awt.Color;

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

    /**
     * constructeur pour la flèche
     * 
     * @param weight    poids de la flèche
     * @param posX      position du centre de gravité en X
     * @param posY      position du centre de gravité en Y
     * @param angleInit angle initial de lancer
     * @param speedInit vitesse de lancer
     * @param c         couleur de la flèche en fonction du type de flèche
     * 
     */
    public Arrow(int weight, int posX, int posY, double angleInit, double speedInit, int windSpeed, Color c) {
        this.posX = posX;
        this.posY = posY;
        this.angle = angleInit;
        this.weight = weight;
        this.speed = speedInit;
        this.arrowColor = Color.BLACK;
        this.traj = new Trajectoire(angleInit, speedInit, windSpeed, posY, posX);
        this.reachedTarget = false;
    }

    /*
     * public Arrow(int weight, int posX, int posY, double angle) {
     * this.posX = posX;
     * this.posY = posY;
     * this.weight = weight;
     * this.angle = angle;
     * }
     */

    public void move(Target target, int positionNumber) {
        if (!collision(target, positionNumber)) {
            this.posX = (int) traj.paramTraj[0].get(positionNumber);
            this.posY = (int) traj.paramTraj[1].get(positionNumber);
            this.angle = (double) traj.paramTraj[2].get(positionNumber);
            // TODO repaint
        } else {
            speed = 0;
        }
    }

    public boolean collision(Target target, int positionNumber) {
        boolean collision = false;
        if (((this.posX + length / 2) * Math.acos((double) traj.paramTraj[2].get(positionNumber)) == target.posX)
                && ((this.posY * Math.asin((double) traj.paramTraj[2].get(positionNumber))) >= target.posLowY
                        && this.posY <= target.posHighY)) {
            collision = true;
            speed = 0;
            reachedTarget = true;
        }

        return collision;
    }
}