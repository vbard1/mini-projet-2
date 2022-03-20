import javax.swing.JFrame;

public class mainGame extends JFrame {
    // TODO ici on met les TODO (bugs, trucs à faire encore)

    public static void main(String[] args) {
        launch(/* param? */);
        trajectoire e = new trajectoire(45, 100, 0, 0);
        e.recalculate(45, 20, 2, 0);
        System.out.println(e.toString());
        e.recalculate(45, 20, 0, 20);
        System.out.println(e.toString());
    }

    public static void launch(/* param? */) {
        game newGame = new game(new UI('m'));
    }
}
