import java.util.jar.Attributes.Name;

public class game {

    player player;
    double masse;
    int difficulty; // distance de la cible et et force du vent // de 1 à 3
    UI window;

    public game(UI menu) {
        window = menu;
        player = new player();
        
    }

}