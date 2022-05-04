import java.util.ArrayList;

/**
 * @author Victor BARDIN
 */
public class Trajectoire {

    int arrowType;
    int windSpeed;
    double angleRad;
    double speed;
    double gravity;
    int yInit;
    int maxX;
    ArrayList[] paramTraj;

    /**
     * @method Trajectoire constructeur de l'objet Trajectoire, permet de tracer les
     *         trajectoires pour les prévisualiser, ou de faire decrire a la fleche
     *         sa Trajectoire.
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

    public Trajectoire(double angleInitDeg, double speedInit, int windSpeed, int yInit, int xInit) {
        gravity = 9.81;

        this.angleRad = angleInitDeg * degToRad;
        this.speed = speedInit;
        this.windSpeed = windSpeed; // ?
        this.yInit = yInit;

        paramTraj = new ArrayList[4];
        recalculate(angleInitDeg, speedInit, windSpeed, yInit, xInit);

    }

    public void recalculate(double angleInitDeg, double speedInit, int windSpeed, int yInit, int xInit) {
        long executionTime = System.currentTimeMillis();
        // System.out.println("\narray of ArrayList DONE\n");

        maxX = (int) ((speedInit / (gravity)) * Math.cos(angleInitDeg * degToRad) *
                (speedInit * Math.sin(angleInitDeg * degToRad)
                        * +(Math.sqrt(
                                Math.pow(speedInit * Math.sin(angleInitDeg * degToRad), 2) + 2 * gravity * yInit))));
        // System.out.print(" [max X = " + maxX + "]");

        // taille de tableaux = distance horizontale max DANS LE VIDE
        // il va falloir le timer en fonction de t pour avoir une vitesse
        // réelle
        paramTraj[0] = new ArrayList<Integer>(maxX); // param x // on a x(t)=v°*cos(angleInit)*t dans le vide
        paramTraj[1] = new ArrayList<Integer>(maxX); // param y pour chaque x
        paramTraj[2] = new ArrayList<Double>(maxX); // param angle pour chaque x
        paramTraj[3] = new ArrayList<Double>(maxX); // vitesse pour chaque x

        // base de temps de 0.1 sec?
        int y = 0;
        double vitesse;
        int absciss = 0;
        speedInit /= (windSpeed / 2 + 1);
        while (absciss < maxX) {
            // Trajectoire de y en fonction de x dans le vide
            vitesse = Math.sqrt(speedInit * speedInit - 2 * gravity * absciss * Math.tan(angleInitDeg * degToRad)
                    + Math.pow((absciss * gravity / (Math.cos(angleInitDeg * degToRad) * speedInit)), 2));

            y = (int) (-0.5 * gravity / (speedInit * speedInit) * absciss * absciss *
                    (1 + Math.pow(Math.tan(angleInitDeg * degToRad), 2))
                    + absciss * Math.tan(angleInitDeg * degToRad) + yInit);
            // System.out.print(y + ";");

            if (y > -1) {
                // System.out.print( "\n Windspeed : " + windSpeed + " => I retrieve to y " +
                // (int) (windSpeed * vitesse)+ " px");

                // System.out.print(" => added the valid point");
                paramTraj[0].add(absciss + xInit);
                // remplissage de y en fonction de x
                paramTraj[1].add(y);
                paramTraj[3].add(vitesse);

            }
            absciss++;
        }
        maxX = absciss; // réafectation à maxX de la distance max horizontale réelle

        double X1 = 0;
        double X2 = 0;
        double Y1 = 0;
        double Y2 = 0;
        double nextAngle = 0;
        angleRad = angleInitDeg * degToRad;
        paramTraj[2].add(angleRad);
        for (int x = 0; x < paramTraj[0].size() - 4; x++) {

            X1 = (double) (int) (paramTraj[0].get(x));
            X2 = (double) (int) (paramTraj[0].get(x + 4));
            Y1 = (double) (int) (paramTraj[1].get(x));
            Y2 = (double) (int) (paramTraj[1].get(x + 4));

            nextAngle = Math.atan((Y2 - Y1) / (X2 - X1));
            // System.out.println(nextAngle);
            if (nextAngle < angleRad) {

                angleRad = nextAngle;
            }
            paramTraj[2].add(angleRad);

        }
        smoothen();
        executionTime = System.currentTimeMillis() - executionTime;
        System.out.println("[recalculation complete] : execution time = "
                + (executionTime) + " ms");

    }

    private void smoothen() {
        double change0 = (Double) paramTraj[2].get(0);
        double change1 = 0;
        int index = 0;
        int space = 0;
        ArrayList<Integer> changes = new ArrayList<Integer>();
        for (Object angle : paramTraj[2]) {
            change1 = (Double) angle;

            if (change1 != change0) {
                for (int i = index; i < index + space; i++) {
                    changes.add(index);
                }
                change0 = change1;
                space = 0;
            }
            space++;
            index++;
        }
        int temp = 0;
        for (Integer indexChanges : changes) {
            space = indexChanges - temp;
            for (int i = temp; i < temp + space; i++) {
                double angle2 = (double) paramTraj[2].get(indexChanges);
                double angle1 = (double) paramTraj[2].get(temp);
                paramTraj[2].set(i, angle1 + (angle2 - angle1) * (i - temp) / space);
            }

            temp = indexChanges;

        }

    }

    public String toString() {
        // System.out.println("EXCEL VERSION : nValues = " + paramTraj[0].size());
        String Stringed = "";
        for (int i = 0; i < paramTraj[0].size() - 1; i++) {

            Stringed += (paramTraj[1].get(i) + " ");
        }
        return Stringed;
    }
}
