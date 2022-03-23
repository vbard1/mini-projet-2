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
    // TODO prender en compte le vent :
    // https://media.eduscol.education.fr/file/Formation_continue_enseignants/15/8/StFlour2007_Etienne_110158.pdf
    // page 27
    // https://www.solumaths.com/en/calculator/calculate/solve_equations pour la
    // spatialiser'
    // TODO trajectoires interdites
    double degToRad = Math.PI / 180.0;

    public trajectoire(double angleInitDeg, double speedInit, int windSpeed, int yInit) {
        gravity = 9.81;

        this.angleRad = angleInitDeg * degToRad;
        this.speed = speedInit;
        this.windSpeed = windSpeed; // ?
        this.yInit = yInit;

        paramTraj = new ArrayList[3];
        recalculate(angleInitDeg, speedInit, windSpeed, yInit);

    }

    public void recalculate(double angleInitDeg, double speedInit, int windSpeed, int yInit) {
        long executionTime = System.currentTimeMillis();
        // System.out.println("\narray of ArrayList DONE\n");

        maxX = (int) ((speedInit / (gravity)) * Math.cos(angleInitDeg * degToRad) *
                (speedInit
                        * Math.sin(angleInitDeg * degToRad)
                        * +(Math.sqrt(Math.pow(speedInit * Math.sin(angleInitDeg * degToRad), 2) + 2 *
                                gravity * yInit))))
                + 1;
        System.out.println("max X = " + maxX);

        // taille de tableaux = distance horizontale max DANS LE VIDE
        // il va falloir le timer en fonction de t pour avoir une vitesse
        // réelle
        paramTraj[0] = new ArrayList<Integer>(maxX); // param x // on a x(t)=v°*cos(angleInit)*t dans le vide
        System.out.println("\nx DONE");
        paramTraj[1] = new ArrayList<Integer>(maxX); // param y pour chaque x
        System.out.println("\ny DONE");
        paramTraj[2] = new ArrayList<Double>(maxX); // param angle pour chaque x
        System.out.println("\nangle DONE");
        //paramTraj[3] = new ArrayList<Double>(maxX); // vitesse pour chaque x

        // base de temps de 0.1 sec?
        int y = 0;
        for (int absciss = 0; absciss < maxX; absciss++) {
            // trajectoire de y en fonction de x dans le vide
            
              y = (int) (-0.5 * gravity / (speedInit * speedInit) * absciss * absciss*
              (1 + Math.pow(Math.tan(angleInitDeg * degToRad), 2))
              + absciss * Math.tan(angleInitDeg * degToRad) + yInit);
             
            
            if (y > -1) {
                paramTraj[0].add(absciss);
                // remplissage de y en fonction de x
                paramTraj[1].add(y);
            }
        }

        System.out.println("\nx and y DONE");
        double X1 = 0;
        double X2 = 0;
        double Y1 = 0;
        double Y2 = 0;

        for (int x = 0; x < paramTraj[0].size() - 1; x++) {
            paramTraj[2].add(angleInitDeg * degToRad);
            X1 = (double) (int) (paramTraj[0].get(x));
            X2 = (double) (int) (paramTraj[0].get(x + 1));
            Y1 = (double) (int) (paramTraj[1].get(x));
            Y2 = (double) (int) (paramTraj[1].get(x + 1));
            angleRad = Math.acos((X2 - X1) / (Y2 - Y1));
            paramTraj[2].add(angleRad);
        }
        executionTime = System.currentTimeMillis() - executionTime;
        System.out.println("\nall DONE \n[recalculation complete] : execution time = "
                + (executionTime) + " ms");

    }

    public String toString() {
        System.out.println("EXCEL VERSION :");
        String Stringed = "";
        for (int i = 0; i < paramTraj[0].size() - 1; i++) {

            Stringed += (paramTraj[1].get(i) + " ");
        }
        return Stringed;
    }
}
