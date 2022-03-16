package tests;

import java.util.ArrayList;

public class testTrajectoire {
    public static void main(String[] args) {
        ArrayList[] paramTraj = new ArrayList[3];
        paramTraj[0] = new ArrayList<Integer>(); // param x
        double angleInit = 45 * 3.1415 / 180;
        double speedInit = 100;
        double gravity = 9.81;
        double yInit = 0;

        int maxX = (int) ((speedInit / (gravity)) * Math.cos(angleInit) * (speedInit * Math.sin(angleInit)
                + (Math.sqrt(Math.pow(speedInit * Math.sin(angleInit), 2) + 2 * gravity * yInit))));
        paramTraj[1] = new ArrayList<Integer>(maxX); // param y
        paramTraj[2] = new ArrayList<Double>(maxX);
        int y;
        double angle = angleInit;
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
                double tan = 0;
                double X1 = 0;
                double X2 = 0;
                double Y1 = 0;
                double Y2 = 0;
                paramTraj[2].add(0);
                for (int x = 1; x < maxX - 2; x++) {
                    X1 = (double) (int) (paramTraj[0].get(x));
                    X2 = (double) (int) (paramTraj[0].get(x + 1));
                    Y1 = (double) (int) (paramTraj[1].get(x));
                    Y2 = (double) (int) (paramTraj[1].get(x + 1));
                    angle = Math.acos((X2 - X1) / (Y2 - Y1));
                    paramTraj[2].add(angle);
                }
                paramTraj[2].add(0);
            }

        }
        for (int i = 0; i < maxX; i++) {
            System.out.print(paramTraj[0].get(i) + "," + paramTraj[1].get(i) + "/");
        }

    }
}
