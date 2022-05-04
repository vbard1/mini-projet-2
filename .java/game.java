import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class Game implements ActionListener {

    Player player;
    int weight;
    int difficulty; // "force du vent" de 1 à 3
    UI window;
    Arrow arrow;
    int arrowType;
    int windSpeed;
    Timer updateScore;
    int maxRound;
    int victoryMinScore;

    /**
     * @method Game, constructeur de Game, initialise la fenêtre du menu du jeu et
     *         active les différents actionEvent
     *         comme le bouton de début de de jeu et des paramètres de nombre de
     *         manche et de seuil de victoire
     * 
     * @param fenetre
     */

    public Game(UI fenetre) {
        window = fenetre;
        player = new Player();
        window.startGame.addActionListener(this);
        updateScore = new Timer(100, this);
        maxRound = 2;
        victoryMinScore = 1;

    }

    /**
     * @method actionPerformed fait le lien entre les différentes fenêtres (menu,
     *         jeu et fenêtre de fin de jeu) et active les listeners liés au bouton
     *         de chaque fenêtre.
     *         Lors du jeu elle fait appel au méthodes de gameZone pour afficher la
     *         preview et la flèche en mouvement tout en vérifiant la valeur du
     *         score et si la fait de partie n'a pas été atteinte
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == window.startGame) { // l'utilisateur appuie sur le bouton Jouer
            window.resizeTimer.stop();
            // Affecte à player le nom mis dans la case username du menu
            player = new Player(window.username.getText());
            // Affecte à difficulty le numéro de la case chosi(De 0 à 2, 2 étant le plus
            // compliqué)
            difficulty = window.difficulty.getSelectedIndex();
            window.toMemory = "";
            window.toMemory += (window.username.getText() + ",");
            System.out.println("Playing : " + window.toMemory);

            // Supprime la fenêtre menu
            window.setVisible(false);
            window.dispose();

            // crée une fenètre "jeu"
            window = new UI('g');
            window.gameZone.randomTarget = (difficulty == 2 || difficulty == 1); // Défini si la cible doit être déplacé

            // Actuive les actionListenr des différents boutons
            window.menu.addActionListener(this);
            window.shoot.addActionListener(this);
            window.preview.addActionListener(this);
            updateScore.start();
        } else if (e.getSource() == updateScore) {// Met à jour la valeur du score et vérifie si le manches sont finis
                                                  // et dans ce cas lance gameEnd
            String scoreText = "Score : " + player.score;
            if (window.score != null) {
                window.score.setText(scoreText);
                if (window.gameZone.roundNb >= maxRound && !window.gameZone.shooting) {
                    gameEnd();
                }
            }
        } else if (e.getSource() == window.menu) { // l'utilisateur appuie sur le bouton menu
            window.setVisible(false);
            window.dispose(); // Suppression de la fenêtre jeu
            window = new UI('m'); // crée une fenêtre "menu"
            window.startGame.addActionListener(this);

        } else if (e.getSource() == window.shoot && !window.gameZone.shooting) {// l'utilisateur appuie sur le bouton
                                                                                // shoot

            if (window.gameZone.roundNb < maxRound) {
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

                // Définition du point initial de la flèche selon l'angle initial

                int x = (int) (40 + (20) * Math.cos((double) Math.toRadians(angleInit)));
                int y = (int) (130 + (20) * Math.sin((double) Math.toRadians(angleInit)));

                Arrow arrow = new Arrow(weight, x, y, angleInit, speedInit, windSpeed, Color.BLACK); // création d'une
                                                                                                     // flèche
                window.gameZone.shoot(arrow, player, maxRound);
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

            // Définition du point initial de la flèche selon l'angle initial

            int x = (int) (40 + (20) * Math.cos((double) Math.toRadians(angleInit)));
            int y = (int) (130 + (20) * Math.sin((double) Math.toRadians(angleInit)));

            // création d'uneflèche
            Arrow arrow = new Arrow(weight, x, y, angleInit, speedInit, windSpeed, Color.BLACK); //
            window.gameZone.preview(arrow, player);

        } else if (e.getSource() == window.restart) {// bouton restart sélectionné à la fin d'une partie : création
                                                     // d'une fenêtre jeu
            window.setVisible(false);
            window.dispose();
            window = new UI('g');
            window.menu.addActionListener(this);
            window.shoot.addActionListener(this);
            window.preview.addActionListener(this); // création d'une nouvelle fenêtre de jeu sans modification des

            // paramètres
        } else if (e.getSource() == window.quit) { // bouton quitter le jeu : ferme la
            // fenêtre, arrête le programme
            window.setVisible(false);
            window.dispose();
            System.exit(0);
        } else if (e.getSource() == window.menuEndGame) {// bouton de fin de partie ramenant au menu
            window.setVisible(false);
            window.dispose();
            window = new UI('m');

        }
    }

    /**
     * @method methode de fin de partie, prepare la partie suivante en affichant le
     *         score et en appelant les methodes de fin de partie de la fenetre
     *         associee
     * 
     */
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