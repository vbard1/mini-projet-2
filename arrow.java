public class arrow {
    double weight;
    int length;
    int posX; // position en pixels axe x DU CENTRE DE GRAVITE
    int posY; // position en pixels axe y DU CENTRE DE GRAVITE
    double angle; // Angle du vecteur vitesse avec l'axe x
    double windX; // force du vent en N
    double speed; // vitesse

    public void arrow(int weight, int posX, int posY, double angle, double speedInit) {
        this.posX = posX;
        this.posY = posY;
        this.angle = angle;
        this.weight = weight;
        this.speed = speedInit;
    }

    public void arrow(int weight, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.weight = weight;
    }

    public void move() {

    }

    public boolean collision(Cible cible) {
        boolean collision = false;
        if (this.posX + length / 2 == cible.posX && (this.posY >= cible.posLowY && this.posY <= cible.posHighY)) {
            collision = true;
            speed = 0;
        }

        return collision;
    }
}
