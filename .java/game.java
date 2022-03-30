import java.util.jar.Attributes.Name;
import java.awt.event.*;

public class game implements ActionListener {

    player player;
    double masse;
    int difficulty; // distance de la cible et et force du vent // de 1 à 3
    UI window;
    int roundNb;
    Arrow arrow;
    Target target;

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
            difficulty=window.difficulty.getSelectedIndex();   
            window.setVisible(false);
            window.dispose();
            window=new UI('g');
            window.menu.addActionListener(this);
            window.shoot.addActionListener(this);
        }else if(e.getSource()==window.menu){
            window.setVisible(false);
            window.dispose();
            window =new UI('m');
            window.startGame.addActionListener(this);
        }else if(e.getSource() == window.shoot){
            if (roundNb >= 0){
                roundNb --;
                double angleInit = window.angle.getValue() ;
                double speedInit = window.speed.getValue() ;
                Arrow arrow = new Arrow(masse, x, y, angleInit, speedInit);
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