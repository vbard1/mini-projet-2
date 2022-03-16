import java.util.ArrayList;

public class trajectoire {
    int arrowType;
    int windSpeed;
    double angle;
    double speed;
    double gravity;
    int yInit;
    int maxX;
    ArrayList[] paramTraj;

    public trajectoire(int arrowType, double angleInit, double speedInit, int windSpeed) {
        this.arrowType = arrowType;
        this.angle = angleInit;
        this.speed = speedInit;
        this.windSpeed = windSpeed;
        paramTraj = new ArrayList[3];
        paramTraj[0] = new ArrayList<Integer>(); // param x

        maxX = (int) ((speedInit / (gravity)) * Math.cos(angleInit) * (speedInit * Math.sin(angleInit)
                + (Math.sqrt(Math.pow(speedInit * Math.sin(angleInit), 2) + 2 * gravity * yInit))));
        for (int i = 0; i < paramTraj.length; i++) {
            // taille de tableaux = distance horizontale max
            if (i == 0) {
                for (Integer absciss = 0; absciss < maxX; absciss++) {
                    paramTraj[0].add(absciss, absciss);
                }
            } else if (i == 1) {
                paramTraj[1] = new ArrayList<Integer>(maxX);
                
            } else if (i == 2) {
                paramTraj[2] = new ArrayList<Double>(maxX);
            }
        }
    }
}
