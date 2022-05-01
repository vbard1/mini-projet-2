public class Target {

    public int posX; // position sur x
    public int posY; // haut de la cible
    public int side; // logueur côté de la cible

    public Target(int posX, int side) { // placer une cible sur le sol
        this.posX = posX;
        this.posY = 0;
        this.side = side;
    }

    public Target(int posX, int posY, int side) { // placer une cible en hauteur
        this.posX = posX;
        this.posY = posY;
        this.side = side;
    }

}
