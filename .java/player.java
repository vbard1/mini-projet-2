public class Player {

    public String name;
    public int score;
    int playerPosition;

    // constructeur avec un pseudo comme argument
    public Player(String nm) {
        name = nm;
        score = 0;
    }

    // constructeur par defaut
    public Player() {
        name = "Anonymous unicorn";
        score = 0;
        playerPosition = 0;
    }
}
