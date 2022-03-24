import javax.swing.JFrame;

public class mainGame extends JFrame {
    // TODO ici on met les TODO (bugs, trucs à faire encore)

    public static void main(String[] args) {
        // launch(/* param? */);
        Trajectoire e = new Trajectoire(30, 150, 2, 0);
        System.out.println("printing trajectory...");
        System.out.println(e.toString());

        e.recalculate(30, 150, 0, 0);
        System.out.println("printing trajectory...");
        System.out.println(e.toString());

        e.recalculate(30, 150, 1, 0);
        System.out.println("printing trajectory...");
        System.out.println(e.toString());

    }

    public static void launch(/* param? */) {
        game newGame = new game(new UI('m'));
    }
}
