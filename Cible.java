public class Cible {

    public int posX;
    public int posLowY;
    public int posHighY;

    public void cible(int posX, int height) { // placer une cible sur le sol
        this.posX = posX;
        this.posLowY = 0;
        this.posHighY = height;
    }

    public void cible(int posX, int posLowY, int posHighY) { // placer une cible en hauteur
        this.posX = posX;
        this.posLowY = posLowY;
        this.posHighY = posHighY;
    }

    public void cible(int posX, int posLowY, double height) { // placer une cible en hauteur
        this.posX = posX;
        this.posLowY = posLowY;
        this.posHighY = posLowY + (int) height;
    }
}
