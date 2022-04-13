 import javax.swing.JFrame;

public class mainGame extends JFrame {
    // TODO ici on met les TODO (bugs, trucs à faire encore)

    public static void main(String[] args) {
        //launch(/* param? */);
        launch(/* param? */);
        Trajectoire e = new Trajectoire(45, 30, 1, 10, 10);
        System.out.println(" 1 \n" + e.toString());
        e.recalculate(45, 30, 2, 10, 10);
        System.out.println(" 2\n" + e.toString());
        e.recalculate(45, 30, 0, 10, 10);
        System.out.println(" 0\n" + e.toString());

        // TESTS trajectoire

        // Test de performance
        /*
         * long avant = System.currentTimeMillis();
         * int nCalc = 6;
         * Trajectoire e = new Trajectoire(45, 150, 1, 0);
         * for (int i = 0; i < nCalc - 1; i++) {
         * e.recalculate(45, 150, 1, 0);
         * }
         * int size = e.paramTraj[0].size();
         * System.out.println("temps de calcul de trajectoire = " +
         * (System.currentTimeMillis() - avant) / (size * nCalc)
         * + " ms/point, or " + (System.currentTimeMillis() - avant) / (nCalc) +
         * "ms per trajectory => maxX = "
         * + e.paramTraj[0].size());
         */

    }

    public static void launch(/* param? */) {
        game newGame = new game(new UI('m'));
    }
}
