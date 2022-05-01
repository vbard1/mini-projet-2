import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class Game implements ActionListener {

    Player player;
    int weight;
    int difficulty; // distance de la cible et et force du vent // de 1 à 3
    UI window;
    int roundNb;
    Arrow arrow;
    Target target;
    int arrowType;
    int windSpeed;
<<<<<<< HEAD
    boolean victory;
    Timer updateScore;
=======
>>>>>>> 0ca3e8f5e65e221b12f85e917959dd1d2079a5a0

    public Game(UI menu) {
        window = menu;
        player = new Player();
        window.startGame.addActionListener(this);
        roundNb = 0;
        target = new Target(window.getWidth() - 100,  100,50);
        updateScore=new Timer(100,this);

        if (roundNb >= 5) {
            gameEnd();

        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == window.startGame) { // l'utilisateur appuie sur le bouton Jouer
            window.resizeTimer.stop();
            // Affecte à player le nom mis dans la case username du menu
            player = new Player(window.username.getText());
            // Affecte à difficulty le numéro de la case chosi(De 0 à 2, 2 étant le plus
            // compliqué)
            difficulty = window.difficulty.getSelectedIndex(); // récupère le niveau de difficulté choisi
            window.setVisible(false);
            window.dispose();
            window = new UI('g'); // crée une fenètre "jeu"
            window.menu.addActionListener(this);
            window.shoot.addActionListener(this);
            window.preview.addActionListener(this);
            updateScore.start();
        } else if(e.getSource()==updateScore){
            String scoreText = "Score : "+ player.score;
            window.score.setText(scoreText);
            gameEnd();
        }else if (e.getSource() == window.menu) { // l'utilisateur appuie sur le bouton menu
            window.setVisible(false);
            window.dispose();
            window = new UI('m'); // crée une fenêtre "menu"
            window.startGame.addActionListener(this);

        } else if (e.getSource() == window.shoot) {

            if (roundNb < 5) {

                roundNb++; // décompte du nombre de tours restants

                double angleInit = window.angle.getValue();
                double speedInit=0;
                arrowType = window.arrowType.getSelectedIndex();

                // réglage des paramètres en fn de la difficulté et type de flèche sélectionnés

                if (arrowType == 0)
                    speedInit = window.speed.getValue() * 2;
                if (arrowType == 1)
                    speedInit = window.speed.getValue() * 1.5;
                if (arrowType == 2)
<<<<<<< HEAD
                    speedInit = window.speed.getValue() * 0.7;
                
=======
                    speedInit = window.speed.getValue() * 1.2;

>>>>>>> 0ca3e8f5e65e221b12f85e917959dd1d2079a5a0
                windSpeed = difficulty;

                int x =(int)(40+(40)*Math.cos((double)angleInit));
                int y =(int)(120+(40)*Math.sin((double)angleInit));

                Arrow arrow = new Arrow(weight, x, y, angleInit, speedInit, windSpeed, Color.BLACK); // création d'une
                                                                                                     // flèche
<<<<<<< HEAD
                window.gameZone.shoot(arrow,player);
            }
        } else if (e.getSource() == window.preview) { // bouton preview appuyé
                double angleInit = window.angle.getValue();
                double speedInit=0;
                arrowType = window.arrowType.getSelectedIndex();

                // réglage des paramètres en fn de la difficulté et type de flèche sélectionnés

                if (arrowType == 0)
                    speedInit = window.speed.getValue() * 1.5;
                if (arrowType == 1)
                    speedInit = window.speed.getValue() * 0.8;
                if (arrowType == 2)
                    speedInit = window.speed.getValue() * 0.7;
                
                windSpeed = difficulty;

                int x =(int)(40+(40)*Math.cos((double)angleInit));
                int y =(int)(120+(40)*Math.sin((double)angleInit));
                //  création d'uneflèche
                Arrow arrow = new Arrow(weight, x, y, angleInit, speedInit, windSpeed, Color.BLACK); //
                window.gameZone.preview(arrow,player);

        } else if (e.getSource() == window.restart) { // bouton restart sélectionné à la fin d'une partie : création d'un 
=======
                window.gameZone.shoot(arrow);
                if (arrow.reachedTarget) {
                    player.score++;
                }
                String scoreText = "Score : " + player.score;
                window.score.setText(scoreText);
            }
        } else if (e.getSource() == window.preview) { // bouton preview appuyé
            int x = 70;
            int y = 125;
            Trajectoire t = new Trajectoire(window.angle.getValue(), window.speed.getValue(), 0, y, x); // création de
                                                                                                        // la
                                                                                                        // trajectoire
                                                                                                        // correspondante
            window.gameZone.preview(t);
        } else if (e.getSource() == window.restart) { // bouton restart sélectionné à la fin d'une partie : création
                                                      // d'un
>>>>>>> 0ca3e8f5e65e221b12f85e917959dd1d2079a5a0
            window = new UI('g'); // création d'une nouvelle fenêtre de jeu sans modification des paramètres
        } // else if (e.getSource() == window.quit){ // bouton quitter le jeu : ferme la
          // fenêtre, arrête le programme

        // }
    }

    // TODO fin de partie
    // récupérer infos et créer flèche quand "shoot" cliqué (fait)
    // Ajouter au score du joueur si la cible est touchée (fait)
    // remettre la fenètre à zéro à chaque tour

    public void onGoingGame() {
        while (roundNb > 0) {
            if (arrow.reachedTarget) {
                player.score++;
            }
        }
    }

    public void gameEnd() {
        if (player.score > 2 && roundNb==5) {
            window = new UI('v');
        } else if(roundNb==5) {
            window = new UI('d');
        }
    }

}