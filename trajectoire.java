import java.util.ArrayList;

/**
 * @author Victor BARDIN
 */
public class trajectoire {

    int arrowType;
    int windSpeed;
    double angle;
    double speed;
    double gravity;
    int yInit;
    int maxX;
    ArrayList[] paramTraj;

    /**
     * constructeur de l'objet trajectoire, permet de tracer les
     * trajectoires pour les prévisualiser, ou de faire decrire a la fleche
     * sa trajectoire.
     * 
     * @param arrowType le type de fleche utilisee (aluminium, bois ou carbone)
     * @param angleInit angle de tir initial
     * @param speedInit vitesse de tir initiale
     * @param windSpeed vitesse du vent
     */
    public trajectoire(int arrowType, double angleInit, double speedInit, int windSpeed) {
        this.arrowType = arrowType;
        this.angle = angleInit;
        this.speed = speedInit;
        this.windSpeed = windSpeed;
        paramTraj = new ArrayList[3];
        paramTraj[0] = new ArrayList<Integer>(); // param x
        maxX = (int) ((speedInit / (gravity)) * Math.cos(angleInit) * (speedInit * Math.sin(angleInit)
                + (Math.sqrt(Math.pow(speedInit * Math.sin(angleInit), 2) + 2 * gravity * yInit))));
        paramTraj[1] = new ArrayList<Integer>(maxX); // param y
        paramTraj[2] = new ArrayList<Double>(maxX);
        int y;

        for (int i = 0; i < paramTraj.length; i++) {

            // taille de tableaux = distance horizontale max
            if (i == 0) {
                for (double absciss = 0; absciss < maxX; absciss++) {
                    paramTraj[0].add((int) absciss, (int) absciss);
                    y = (int) (-0.5 * gravity / (speedInit * speedInit) * absciss * absciss
                            * (1 + Math.pow(Math.tan(angleInit), 2)) + absciss * Math.tan(angleInit));
                    paramTraj[1].add(y);
                }
            } else if (i == 2) {
                double tan = 0;
                double X1 = 0;
                double X2 = 0;
                double Y1 = 0;
                double Y2 = 0;
                paramTraj[2].add(0);
                for (int x = 0; x < maxX - 1; x++) {
                    X1 = (double) (paramTraj[0].get(x));
                    X2 = (double) (paramTraj[0].get(x + 1));
                    Y1 = (double) (paramTraj[1].get(x));
                    Y2 = (double) (paramTraj[1].get(x + 1));
                    angle = Math.acos((X2 - X1) / (Y2 - Y1));
                    paramTraj[2].add(angle);
                }
                paramTraj[2].add(0);
            }
        }
    }
}
