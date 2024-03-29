import java.util.ArrayList;

public class Trajectoire {

    int arrowType; // type de fleche
    int windSpeed; // force du vent (multiplicateur entier)
    double angleRad; // angle de la fleche à un point de la trajectoire
    double speed; // vitesse de la fleche a un point de la trajectoire
    double gravity; // gravitee du milieu
    int yInit; // position initiale en y
    int maxX; // deuxieme racine de la parabole (parametre abscisse x)
    ArrayList[] paramTraj; // le tableau de stockage des points de la trajectoire et autres parametres (cf.
                           // initialisation)
    double degToRad = Math.PI / 180.0; // conversion degrés à radians

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
    public Trajectoire(double angleInitDeg, double speedInit, int windSpeed, int yInit, int xInit) {

        gravity = 9.81;
        this.angleRad = angleInitDeg * degToRad;
        this.speed = speedInit;
        this.windSpeed = windSpeed;
        this.yInit = yInit;

        paramTraj = new ArrayList[4];
        recalculate(angleInitDeg, speedInit, windSpeed, yInit, xInit);

    }

    /**
     * @method actualise la trajectoire en fonction des parametres initiaux
     *         actualises par l'utilisateurs, ne cree pas de nouvel objet
     *         trajectoire
     * 
     * @param angleInitDeg l'angle initial en degres
     * @param speedInit    la vitesse initiale en px/s
     * @param windSpeed    vitesse du vent (coefficient)
     * @param yInit        position y initiale
     * @param xInit        position x initiale
     */
    public void recalculate(double angleInitDeg, double speedInit, int windSpeed, int yInit, int xInit) {

        long executionTime = System.currentTimeMillis(); // test de performance

        // calcul de l'abscisse maximale pour anticiper l'augmentation de taille des
        // ArrayList (optimisation) : taille de tableaux = distance horizontale max
        maxX = (int) ((speedInit / (gravity)) * Math.cos(angleInitDeg * degToRad) *
                (speedInit * Math.sin(angleInitDeg * degToRad)
                        * +(Math.sqrt(
                                Math.pow(speedInit * Math.sin(angleInitDeg * degToRad), 2)
                                        + 2 * gravity * yInit))));

        paramTraj[0] = new ArrayList<Integer>(maxX); // param. x // on a x(t)=v°*cos(angleInit)*t dans le vide
        paramTraj[1] = new ArrayList<Integer>(maxX); // param. y pour chaque x
        paramTraj[2] = new ArrayList<Double>(maxX); // param. angle pour chaque x
        paramTraj[3] = new ArrayList<Double>(maxX); // param. vitesse pour chaque x

        int y = 0;
        double speed;
        int absciss = 0;
        speedInit /= (windSpeed / 2 + 1); // prise en compte de la vitesse du vent
        while (absciss < maxX) {

            // vitesse en fonction de x
            speed = Math.sqrt(speedInit * speedInit - 2 * gravity * absciss * Math.tan(angleInitDeg * degToRad)
                    + Math.pow((absciss * gravity / (Math.cos(angleInitDeg * degToRad) * speedInit)), 2));

            // Trajectoire de y en fonction de x (equation cartesienne
            // spatiale)
            y = (int) (-0.5 * gravity / (speedInit * speedInit) * absciss * absciss *
                    (1 + Math.pow(Math.tan(angleInitDeg * degToRad), 2))
                    + absciss * Math.tan(angleInitDeg * degToRad) + yInit);

            if (y > -1) { // aucun point en dessous de 0

                paramTraj[0].add(absciss + xInit);
                paramTraj[1].add(y);
                paramTraj[3].add(speed);

            }
            absciss++; // point suivant
        }
        maxX = absciss; // réafectation à maxX de la distance max horizontale réelle

        double X1 = 0;
        double X2 = 0;
        double Y1 = 0;
        double Y2 = 0;
        double nextAngle = 0;
        angleRad = angleInitDeg * degToRad;
        paramTraj[2].add(angleRad);
        for (int x = 3; x < paramTraj[0].size() - 3; x++) {

            X1 = (double) (int) (paramTraj[0].get(x - 3));
            X2 = (double) (int) (paramTraj[0].get(x + 3));
            Y1 = (double) (int) (paramTraj[1].get(x - 3));
            Y2 = (double) (int) (paramTraj[1].get(x + 3));

            nextAngle = Math.atan((Y2 - Y1) / (X2 - X1));

            if (nextAngle < angleRad) { // evite l'effet tremblement
                angleRad = nextAngle;
            }
            paramTraj[2].add(angleRad);

        }
        smoothen();
        executionTime = System.currentTimeMillis() - executionTime;
        System.out.println("[recalculation complete] : execution time = "
                + (executionTime) + " ms");

    }

    /**
     * @method lisse les angles de la trajectoire (paramTraj[2]) pour eviter l'effet
     *         "crenelage". Transforme un changement brutal entre les angles en une
     *         rotation progressive
     */
    public void smoothen() {

        double change0 = (Double) paramTraj[2].get(0); // angle temporaire du point le plus proche du point 0 ayant un
                                                       // angle different
        double change1 = 0; // angle temporaire le plus proche du point precedent enregistre par change0
                            // ayant un angle differrent de change0
        int index = 0; // position consideree
        int space = 0; // espacement en nombre de points entre le point change0 et change1

        // enregistrement des positions x de cassure d'angle
        ArrayList<Integer> changes = new ArrayList<Integer>();
        for (Object angle : paramTraj[2]) {
            change1 = (Double) angle;
            if (change1 != change0) {

                changes.add(index);

                change0 = change1;
                space = 0;
            }
            space++;
            index++;
        }

        // lissage des angles
        int temp = 0;
        for (Integer indexChanges : changes) {
            space = indexChanges - temp;
            double point1;
            double point0;
            for (int i = temp; i < temp + space; i++) {
                point1 = (double) paramTraj[2].get(indexChanges);
                point0 = (double) paramTraj[2].get(temp);
                paramTraj[2].set(i, point0 + (point1 - point0) * (i - temp) / space); // formule de lissage
            }

            temp = indexChanges;

        }

    }

    // renvoie sous forme de string les positions y separees d'un espace
    public String toString() {
        String Stringed = "";
        for (int i = 0; i < paramTraj[0].size() - 1; i++) {

            Stringed += (paramTraj[1].get(i) + " ");
        }
        return Stringed;
    }
}
