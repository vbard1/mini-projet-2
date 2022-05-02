import javax.swing.JFrame;

public class MainGame extends JFrame {
    public static void main(String[] args) {
        launch();
    }

    public static void launch() {
        Game newGame = new Game(new UI('m'));
    }
}
