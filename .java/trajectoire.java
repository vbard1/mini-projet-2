import java.util.ArrayList;

/**
 * @author Victor BARDIN
 */
public class trajectoire {

    int arrowType;
    int windSpeed;
    double angleRad;
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
     * @param arrowType    le type de fleche utilisee (aluminium, bois ou carbone)
     * @param angleInitDeg angle de tir initial en degres
     * @param speedInit    vitesse de tir initiale
     * @param windSpeed    vitesse du vent
     */
    // TODO prender en compte le vent
    // TODO trajectoires interdites
    double degToRad = Math.PI / 180.0;

    public trajectoire(double angleInitDeg, double speedInit, int windSpeed, int yInit) {
        gravity = 9.81;

        this.angleRad = angleInitDeg * degToRad;
        this.speed = speedInit;
        this.windSpeed = windSpeed; // ?
        paramTraj = new ArrayList[3];

        System.out.println("\narray of ArrayList DONE\n");

        maxX = (int) ((speedInit / (gravity)) * Math.cos(angleInitDeg * degToRad) * (speedInit
                * Math.sin(angleInitDeg * degToRad)
                + (Math.sqrt(Math.pow(speedInit * Math.sin(angleInitDeg * degToRad), 2) + 2 * gravity * yInit)))) + 1;
        System.out.println("max X = " + maxX);

        // taille de tableaux = distance horizontale max
        paramTraj[0] = new ArrayList<Integer>(maxX); // param x
        System.out.println("\nx DONE");
        paramTraj[1] = new ArrayList<Integer>(maxX); // param y pour chaque x
        System.out.println("\ny DONE");
        paramTraj[2] = new ArrayList<Double>(maxX); // param angle pour chaque x
        System.out.println("\nangle DONE");
        recalculate(angleInitDeg, speedInit);

    }

    public void recalculate(double angleInitDeg, double speedInit) {
        long avant = System.currentTimeMillis();
        for (int absciss = 0; absciss < maxX; absciss++) {
            paramTraj[0].add(absciss);
            // remplissage de y en fonction de x
            paramTraj[1].add(
                    (int) (-0.5 * gravity / (speedInit * speedInit) * absciss * absciss
                            * (1 + Math.pow(Math.tan(angleInitDeg * degToRad), 2))
                            + absciss * Math.tan(angleInitDeg * degToRad)));
        }

        System.out.println("\nx and y DONE");
        double X1 = 0;
        double X2 = 0;
        double Y1 = 0;
        double Y2 = 0;

        for (int x = 0; x < maxX - 1; x++) {
            paramTraj[2].add(angleInitDeg * degToRad);
            X1 = (double) (int) (paramTraj[0].get(x));
            X2 = (double) (int) (paramTraj[0].get(x + 1));
            Y1 = (double) (int) (paramTraj[1].get(x));
            Y2 = (double) (int) (paramTraj[1].get(x + 1));
            angleRad = Math.acos((X2 - X1) / (Y2 - Y1));
            paramTraj[2].add(angleRad);
        }

        System.out.println("\nall DONE \n[recalculation complete] : execution time = "
                + (System.currentTimeMillis() - avant) + " ms");

    }

    public String toString() {
        System.out.println("EXCEL VERSION :");
        String Stringed = "";
        for (int i = 0; i < maxX; i++) {

            Stringed += (paramTraj[1].get(i) + " ");
        }
        return Stringed;
    }
}
