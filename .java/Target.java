public class Target {

    public int posX; // position sur x
    public int posLowY; // bas de la cible
    public int height; // haut de la cible

    public Target(int posX, int height) { // placer une cible sur le sol
        this.posX = posX;
        this.posLowY = 0;
        this.height = height;
    }

    public Target(int posX, int posLowY, int height) { // placer une cible en hauteur
        this.posX = posX;
        this.posLowY = posLowY;
        this.height = height;
    }

}
