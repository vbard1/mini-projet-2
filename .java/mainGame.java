public class MainGame {
    public static void main(String[] args) {
        launch();
    }

    // lance la partie en creant la fenetre associee
    public static void launch() {
        Game newGame = new Game(new UI('m'));
    }
}
