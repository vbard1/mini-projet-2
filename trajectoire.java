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
        paramTraj[1] = new ArrayList<Integer>(); // param x
        paramTraj[2] = new ArrayList<Integer>(); // param y
        paramTraj[3] = new ArrayList<Double>(); // angle de la flèche avec l'horizontale

        // taille
    }
}
