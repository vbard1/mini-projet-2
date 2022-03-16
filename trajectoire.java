import java.util.ArrayList;

public class trajectoire {
    int arrowType;
    int windSpeed;
    int angle;
    int speed;
    ArrayList[] paramTraj;

    public trajectoire(int arrowType, int angleInit, int speedInit, int windSpeed) {
        this.arrowType = arrowType;
        this.angle = angleInit;
        this.speed = speedInit;
        this.windSpeed = windSpeed;
        paramTraj = new ArrayList[3];
        paramTraj[0] = new ArrayList<Integer>(); // param x
        paramTraj[1] = new ArrayList<Integer>(); // param y
        paramTraj[2] = new ArrayList<Double>(); // angle de la flèche avec l'horizontale

        for (int i = 0; paramTraj.length < 3; i++) {
            //taille de tableaux = distance horizontale max
            if (i == 0) {
                paramTraj[0].add()
            } else if (i == 1) {

            } else if (i == 2) {

            }
        }
    }
}
