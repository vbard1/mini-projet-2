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
        paramTraj[1] = new ArrayList<Integer>(maxX); // param y
        paramTraj[2] = new ArrayList<Double>(maxX);
        int y;

        for (int i = 0; i < paramTraj.length; i++) {

            // taille de tableaux = distance horizontale max
            if (i == 0) {
                for (Integer absciss = 0; absciss < maxX; absciss++) {
                    paramTraj[0].add(absciss, absciss);
                    y = (int) (-0.5 * gravity / (speedInit * speedInit) * absciss * absciss
                            * (1 + Math.pow(Math.tan(angleInit), 2)) + absciss * Math.tan(angleInit));
                    paramTraj[1].add(y);
                }
            } else if (i == 2) {

            }
        }
    }
}
