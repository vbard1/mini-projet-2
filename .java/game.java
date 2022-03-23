import java.util.jar.Attributes.Name;
import java.awt.event.*;

public class game implements ActionListener {

    player player;
    double masse;
    int difficulty; // distance de la cible et et force du vent // de 1 à 3
    UI window;

    public game(UI menu) {
        window = menu;
        player = new player();
        window.startGame.addActionListener(this);
        
        
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
        }else if(e.getSource()==window.menu){
            window.setVisible(false);
            window.dispose();
            window =new UI('m');
            window.startGame.addActionListener(this);
        }
    }
    //TODO décompte score ?
    //TODO gestion tours du jeu
        // récupérer infos et créer trajectoire quand "launch" cliqué
        //Ajouter au score du joueur si la cible est touchée
        //remettre la fenètre à zéro à chaque tour

}