import javax.swing.JFrame;

public class mainGame extends JFrame {
    // TODO ici on met les TODO (bugs, trucs à faire encore)

    public static void main(String[] args) {
        launch(/* param? */);

        // TESTS trajectoire
        /*
         * Trajectoire e = new Trajectoire(30, 500, 2, 0);
         * System.out.println("printing trajectory...");
         * System.out.println(e.toString());
         * 
         * e.recalculate(30, 500, 0, 0);
         * System.out.println("printing trajectory...");
         * System.out.println(e.toString());
         * 
         * e.recalculate(30, 500, 1, 0);
         * System.out.println("printing trajectory...");
         * System.out.println(e.toString());
         */
        // Test de performance
        long avant = System.currentTimeMillis();
        int nCalc = 6;
        Trajectoire e = new Trajectoire(45, 150, 1, 0);
        for (int i = 0; i < nCalc - 1; i++) {
            e.recalculate(45, 150, 1, 0);
        }
        int size = e.paramTraj[0].size();
        System.out.println("temps de calcul de trajectoire = " + (System.currentTimeMillis() - avant) / (size * nCalc)
                + " ms/point, or " + (System.currentTimeMillis() - avant) / (nCalc) + "ms per trajectory => maxX = "
                + e.paramTraj[0].size());

    }

    public static void launch(/* param? */) {
        game newGame = new game(new UI('m'));
    }
}
