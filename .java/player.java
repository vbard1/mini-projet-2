public class player {

    // TODO highscore;
    public String name;
    public int score;

    public player(String nm) {
        name = nm;
        score = 0;
    }
    // possible amelioration : reprendre la derniere partie : fichier sauvegarde +
    // score = prevScore (pas forcément dur)

    public player() {
        name = "Anonymous unicorn";
        score = 0;
    }
}
