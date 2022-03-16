import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame implements ActionListener {
    // on peut tricher et rajouter une barre de défillement et la méthode get qui va
    // bien avec : )
    // http://web.mit.edu/java_v1.0.2/www/javadoc/java.awt.Scrollbar.html
    char type;

    // attributs-parametres du menu

    public UI(char type) {
        this.type = type;
        if (type == 'm') {
            //// de quoi récupérer :
            // un nom
            // une difficulté
            // un type de flèche
            // bouton lancer la partie
            // (éventuellement animation du joueur en attente)
        } else if (type == 'g') {
            // scrollbar angle
            // scrollbar vitesse
            // JButton tirer
            // JButton menu (fait quitter la partie -> action event)
            // jlabel score
            // jpanel pour le jeu
        }

    }

    // TODO inclure le type d
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        /*
         * game.player.name=JTextField de nom;
         * game.masse=; //...
         * game.difficulty=;
         */
    }

}