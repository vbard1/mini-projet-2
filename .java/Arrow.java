import java.awt.Color;

public class Arrow {

    double weight;          //poids de la flèche
    int length;             //longueur de la flèche
    int posX;               // position en pixels axe x DU CENTRE DE GRAVITE
    int posY;               // position en pixels axe y DU CENTRE DE GRAVITE
    double angle;           // Angle du vecteur vitesse avec l'axe x
    double speed;           // vitesse de la flèche au départ
    boolean reachedTarget;  //boolean qui renvoie si la flèche a atteint la cible
    Trajectoire traj;       //trajectoire de la flèche
    Color arrowColor;       //couleur de la flèche
    int positionNumber;     //Stocke l'indice de la trajectoire 
    int trajSize;           //taille des ArrayList x,y et angle dans la trajectoire
    int windSpeed;          //vitesse du vent
    

    
    /**
     * constructeur pour la flèche
     * @method Arrow, constructeur de l'objet Arrow, permet d'initialiser la flèche avec sa trajectoire et ses paramètres liés à l'affichage de la flèche
     * 
     * @param weight        poids de la flèche
     * @param posX          position en pixels axe x DU CENTRE DE GRAVITE
     * @param posY          position en pixels axe y DU CENTRE DE GRAVITE
     * @param angleInit     angle initial de la flèche
     * @param speedInit     vitesse initial de la flèche
     * @param windSpeed     vitesse du vent
     * @param c             couleur de la flèche
     */
    public Arrow(int weight, int posX, int posY, double angleInit, double speedInit, int windSpeed, Color c)  {
        this.posX = posX;
        this.posY = posY;
        this.angle = angleInit;
        this.weight = weight;
        this.speed = speedInit;
        this.arrowColor = Color.BLACK;
        this.windSpeed=windSpeed;
        this.traj = new Trajectoire(angleInit, speedInit, windSpeed, posY, posX);
        this.reachedTarget = false;
        length=50;
        positionNumber=0;
        trajSize=traj.paramTraj[2].size();
        
    }

    /**
     * @method Arrow, constructeur de Arrow, iniitialise la flèche avec ses paramètres liés à son affichage sans trajectoire
     */

    public Arrow(){
        this.arrowColor = Color.BLACK;
        this.reachedTarget = false;
        length=50;
        positionNumber=0;
    }

    /**
     * @method nextPos permet d'affecter à posX, posY et angle leur valeur à la position positionNumber dans la trajectoire traj. 
     *          Cette affectation n'a lieu que si la flèche entre en contact avec la cible ou sort de l'écran.
     *          
     * 
     * @param target    cible que le joueur doit atteindre
     * @param width     largeur de la zone où on tire la flèche
     * @param height    longueur de la zone où on tire la flèche
     * @return          Renvoie un booléen qui est vrai si la position suivante existe.
     */

    public boolean nextPos(Target target,int width,int height) {
        boolean exist=false;
        collision(target);
        if (positionNumber<trajSize && !reachedTarget && inScreen(width, height)) {
            this.posX = (int) traj.paramTraj[0].get(positionNumber);
            this.posY = (int) traj.paramTraj[1].get(positionNumber);
            this.angle = (double) traj.paramTraj[2].get(positionNumber);
            exist=true;
            positionNumber++;
        } 
        return exist;
    }
    /**
     * @method inScreen  vérifie si le bout de la flèche est à l'intérieur de la zone où on tire la flèche 
     * 
     * @param width     largeur de la zone où on tire la flèche
     * @param height    longueur de la zone où on tire la flèche
     * @return          Renvoie un booléen qui est vrai si le bout de la flèche est dans la zone où on tire la flèche.
     */
    public boolean inScreen(int width, int height){
        boolean b=false;
        if(positionNumber<trajSize){
            b=((int) ((int) traj.paramTraj[0].get(positionNumber)+ (length / 2) * Math.cos((double) (traj.paramTraj[2].get(positionNumber)))) <= width
                && height - ((int) ((int)traj.paramTraj[1].get(positionNumber)+ (length / 2) * Math.sin((double) (traj.paramTraj[2].get(positionNumber))))) <= height);
        }
        return b;
    }

    /**
     * @method collision  vérifie si la flèche entre en collision avec la cible et modifie en conséquence l'attibut reachedTarget
     * @param target  cible que le joueur doit atteindre  
     *
     */

    public void collision(Target target) {

         if ( this.posX  == target.posX && this.posY  <= target.posY && this.posY >= target.posY-target.side ) {
            speed = 0;
            reachedTarget = true;
        }
    }
}
