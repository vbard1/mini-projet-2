public class Player {

    // TODO highscore;
    public String name;
    public int score;
    int playerPosition;

    public Player(String nm) {
        name = nm;
        score = 0;
        playerPosition=0;
    }
    // possible amelioration : reprendre la derniere partie : fichier sauvegarde +
    // score = prevScore (pas forcément dur)

    public Player() {
        name = "Anonymous unicorn";
        score = 0;
        playerPosition=0;
    }
}
