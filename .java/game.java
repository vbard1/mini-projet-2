import java.util.jar.Attributes.Name;
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
    boolean victory ;
    

    public Game(UI menu) {
        window = menu;
        player = new Player();
        window.startGame.addActionListener(this);
        roundNb = 5;
        //target = new Target();
        
        gameEnd();

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == window.startGame) { // l'utilisateur appuie sur le bouton Jouer
            window.resizeTimer.stop();
            // Affecte à player le nom mis dans la case username du menu
            player = new Player(window.username.getText());
            // Affecte à difficulty le numéro de la case chosi(De 0 à 2, 2 étant le plus compliqué)
            difficulty = window.difficulty.getSelectedIndex(); // récupère le niveau de difficulté choisi
            window.setVisible(false);
            window.dispose();
            window = new UI('g'); // crée une fenètre "jeu"
            window.menu.addActionListener(this);
            window.shoot.addActionListener(this);
            window.preview.addActionListener(this);
        }else if(e.getSource()==window.menu){ // l'utilisateur appuie sur le bouton menu
            window.setVisible(false);
            window.dispose();
            window = new UI('m'); // crée une fenêtre "menu"
            window.startGame.addActionListener(this);

        } else if (e.getSource() == window.shoot) {

            if (roundNb >= 0) {

                roundNb--; // décompte du nombre de tours restants

                double angleInit = window.angle.getValue();
                double speedInit = 0;
                arrowType = window.arrowType.getSelectedIndex() ;

                //réglage des paramètres en fn de la difficulté et type de flèche sélectionnés
        
                if (arrowType == 0)
                    speedInit = window.speed.getValue() * 1.5;
                if (arrowType == 1)
                    speedInit = window.speed.getValue() * 0.8;
                if (arrowType == 2)
                    speedInit = window.speed.getValue() * 0.7;

                /*    
                if (difficulty == 0)
                    windSpeed = ;
                if (difficulty == 1)
                    windSpeed = 2;
                if (difficulty == 2)
                    windSpeed = 3; */
                windSpeed = difficulty ;

                int x = 70;
                int y = 125;

                Arrow arrow = new Arrow(weight, x, y, angleInit, speedInit, windSpeed, Color.BLACK); // création d'une flèche
                window.gameZone.shoot(arrow);
                //onGoingGame();
            }
        }else if(e.getSource()==window.preview){ // bouton preview appuyé
            int x = 70;
            int y = 125;
    Trajectoire t=new Trajectoire(window.angle.getValue(),window.speed.getValue()*1.5,0,y,x); // création de la trajectoire correspondante
            window.gameZone.preview(t); 
        }

    }
   //TODO fin de partie
    // récupérer infos et créer flèche quand "shoot" cliqué (fait)
    // Ajouter au score du joueur si la cible est touchée (fait)
    // remettre la fenètre à zéro à chaque tour

    public void onGoingGame() {
        while (roundNb > 0) {
            if (arrow.reachedTarget) {
                player.score ++ ;
            }
        }
    }

    public void gameEnd() {
       if (player.score > 2){
            victory = true ;
       }
       else{
           victory = false ;
       }
       //window = new UI('e');
    }

}