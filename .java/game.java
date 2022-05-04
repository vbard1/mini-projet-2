import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class Game implements ActionListener {

    Player player;
    int weight;
    int difficulty; // distance de la cible et et force du vent // de 1 à 3
    UI window;
    Arrow arrow;
    int arrowType;
    int windSpeed;
    boolean victory;
    Timer updateScore;
    int maxRound;
    int victoryMinScore;

    public Game(UI fenetre) {
        window = fenetre;
        player = new Player();
        window.startGame.addActionListener(this);
        updateScore = new Timer(100, this);
        maxRound = 1;
        victoryMinScore = 1;

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == window.startGame) { // l'utilisateur appuie sur le bouton Jouer
            window.resizeTimer.stop();
            // Affecte à player le nom mis dans la case username du menu
            player = new Player(window.username.getText());
            // Affecte à difficulty le numéro de la case chosi(De 0 à 2, 2 étant le plus
            // compliqué)
            difficulty = window.difficulty.getSelectedIndex(); // récupère le niveau de difficulté choisi
            window.toMemory = "";
            window.toMemory += (window.username.getText() + ",");
            System.out.println("Playing : " + window.toMemory);
            window.setVisible(false);
            window.dispose();
            window = new UI('g'); // crée une fenètre "jeu"
            window.menu.addActionListener(this);
            window.shoot.addActionListener(this);
            window.preview.addActionListener(this);
            updateScore.start();
        } else if (e.getSource() == updateScore) {
            String scoreText = "Score : " + player.score;
            if (window.score != null) {
                window.score.setText(scoreText);
                if (window.gameZone.roundNb > maxRound && !window.gameZone.shooting) {
                    gameEnd();
                }
            }
        } else if (e.getSource() == window.menu) { // l'utilisateur appuie sur le bouton menu
            window.setVisible(false);
            window.dispose();
            window = new UI('m'); // crée une fenêtre "menu"
            window.startGame.addActionListener(this);

        } else if (e.getSource() == window.shoot && !window.gameZone.shooting) {

            if (window.gameZone.roundNb <= maxRound) {
                // décompte du nombre de tours restants

                double angleInit = window.angle.getValue();
                double speedInit = 0;
                arrowType = window.arrowType.getSelectedIndex();

                // réglage des paramètres en fn de la difficulté et type de flèche sélectionnés

                if (arrowType == 0)
                    speedInit = window.speed.getValue() * 2;
                if (arrowType == 1)
                    speedInit = window.speed.getValue() * 1.5;
                if (arrowType == 2)
                    speedInit = window.speed.getValue() * 1.2;

                windSpeed = difficulty;

                int x = (int) (40 + (20) * Math.cos((double) Math.toRadians(angleInit)));
                int y = (int) (130 + (20) * Math.sin((double) Math.toRadians(angleInit)));

                Arrow arrow = new Arrow(weight, x, y, angleInit, speedInit, windSpeed, Color.BLACK); // création d'une
                                                                                                     // flèche
                window.gameZone.shoot(arrow, player);
            }
        } else if (e.getSource() == window.preview && !window.gameZone.shooting) { // bouton preview appuyé
            double angleInit = window.angle.getValue();
            double speedInit = 0;
            arrowType = window.arrowType.getSelectedIndex();

            // réglage des paramètres en fn de la difficulté et type de flèche sélectionnés

            if (arrowType == 0)
                speedInit = window.speed.getValue() * 2;
            if (arrowType == 1)
                speedInit = window.speed.getValue() * 1.5;
            if (arrowType == 2)
                speedInit = window.speed.getValue() * 1.2;

            windSpeed = difficulty;

            int x = (int) (40 + (20) * Math.cos((double) Math.toRadians(angleInit)));
            int y = (int) (130 + (20) * Math.sin((double) Math.toRadians(angleInit)));

            // création d'uneflèche
            Arrow arrow = new Arrow(weight, x, y, angleInit, speedInit, windSpeed, Color.BLACK); //
            window.gameZone.preview(arrow, player);

        } else if (e.getSource() == window.restart) { // bouton restart sélectionné à la fin d'une partie : création
                                                      // d'un
            window.menu.addActionListener(this);
            window.shoot.addActionListener(this);
            window.preview.addActionListener(this); // création d'une nouvelle fenêtre de jeu sans modification des
                                                    // paramètres
        } else if (e.getSource() == window.quit) { // bouton quitter le jeu : ferme la
            // fenêtre, arrête le programme
            window.setVisible(false);
            window.dispose();
            System.exit(0);
        } else if (e.getSource() == window.menuEndGame) {
            window.setVisible(false);
            window.dispose();
            window = new UI('m');

        }
    }

    // TODO fin de partie
    // récupérer infos et créer flèche quand "shoot" cliqué (fait)
    // Ajouter au score du joueur si la cible est touchée (fait)
    // remettre la fenètre à zéro à chaque tour

    public void gameEnd() {
        updateScore.stop();
        if (player.score >= victoryMinScore) {
            window.gameEnd('v', player, maxRound);
        } else {
            window.gameEnd('d', player, maxRound);
        }
        window.restart.addActionListener(this);
        window.menuEndGame.addActionListener(this);
        window.quit.addActionListener(this);

        // stockage du score
        window.toMemory = player.name + ";" + player.score + ";";
        window.storeScore();
    }

}