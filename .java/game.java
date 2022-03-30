import java.util.jar.Attributes.Name;
import java.awt.Color;
import java.awt.event.*;

public class game implements ActionListener {

    player player;
    int weight;
    int difficulty; // distance de la cible et et force du vent // de 1 à 3
    UI window;
    int roundNb;
    Arrow arrow;
    Target target;
    int arrowType;
    int windSpeed; 

    public game(UI menu) {
        window = menu;
        player = new player();
        window.startGame.addActionListener(this);
        roundNb = 5 ;
        target = new Target();
        onGoingGame();
        gameEnd();
        
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==window.startGame){
            //Affecte à player le nom mis dans la case username du menu
            player=new player(window.username.getText()) ;
            //Affecte à difficulty le numéro de la case chosi(De 0 à 2, 2 étant le plus compliqué)
            difficulty=window.difficulty.getSelectedIndex();   // récupère le niveau de difficulté choisi
            window.setVisible(false);
            window.dispose();
            window=new UI('g'); // crée une fenètre "jeu"
            window.menu.addActionListener(this);
            window.shoot.addActionListener(this);

        }else if(e.getSource()==window.menu){
            window.setVisible(false);
            window.dispose();
            window =new UI('m'); //crée une fenêtre "menu"
            window.startGame.addActionListener(this);

        }else if(e.getSource() == window.shoot){

            if (roundNb >= 0){

                roundNb --;

                double angleInit = window.angle.getValue() ;
                double speedInit = 0;
                arrowType = difficulty;
                if (arrowType == 0) speedInit = window.speed.getValue()*40 ;
                if (arrowType == 1) speedInit = window.speed.getValue()*30 ;
                if (arrowType == 2) speedInit = window.speed.getValue()*20 ;

                if (difficulty == 0) windSpeed = 1; 
                if (difficulty == 1) windSpeed = 2;
                if (difficulty == 2) windSpeed = 3;
                
                 int x = 10;
                 int y = 10;

                Arrow arrow = new Arrow(weight, x, y, angleInit, speedInit, windSpeed, Color.BLACK);
            }
        }
            
    }
    //TODO décompte score (fait)
    //TODO gestion tours du jeu
        // récupérer infos et créer flèche quand "shoot" cliqué (fait)
        //Ajouter au score du joueur si la cible est touchée (fait)
        //remettre la fenètre à zéro à chaque tour  

    public void onGoingGame(){
        while (roundNb > 0){
            if(arrow.collision(target)){
                player.score ++;
            }
        }
    }
    public void gameEnd(){
        
    }

}