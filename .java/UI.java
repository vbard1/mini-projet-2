import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import java.awt.Graphics;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.*;


//TODO mettre en relatif aux dimensions de la fenetre
//TODO @abdel pour résoudre les barres qui s'affichent pas https://stackoverflow.com/questions/16737767/scroll-bar-not-visible-in-jpanel

public class UI extends JFrame implements ActionListener {

		public Timer resizeTimer;
		JComboBox<String> arrowType;
        char type;
        JPanel background;
        JLabel title;
        JLabel textUser;
        JTextField username;
        JLabel textDifficulty;
        JComboBox<String> difficulty;
        JLabel textType;
        JButton startGame;

        JPanel settings;
        JScrollBar angle;
        JScrollBar speed;
        JButton shoot;
        JButton menu;
        JLabel score;
        JLabel angleText;
        JLabel speedText;
        gameZone gameZone;

        // attributs-parametres du menu
        //
        public UI(char type) {
				//initialisation du timer
                resizeTimer = new Timer(100, this);
				resizeTimer.start();
                this.type = type;
                // Acquisition de la taille de l'écran
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                // Définition de la fenêtre du menu
                this.setLocation(0, 0);
                this.setSize(1910, 1070);
                this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Défini la taille de la fenêtre à celle de l'écran
                this.setResizable(true);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Définition du fond du menu
                background = new JPanel();
                background.setSize(this.getWidth(), this.getHeight());
                background.setLocation(this.getInsets().left, this.getInsets().top);
                background.setLayout(null);
                
                

                if (type == 'm') {// Affichage du menu

                        // Définition titre
                        title = new JLabel("Tir à l'arc (nom à voir)");
                        title.setHorizontalAlignment(SwingConstants.CENTER);
                        title.setFont(new Font("Times New Roman", Font.BOLD,(int)background.getWidth()/30));
                        title.setSize((int) screenSize.getWidth(), (int)background.getHeight()/16);
                        title.setLocation(0, (int) background.getHeight()/10);
                        title.setLayout(null);

                        // Définition texte "Nom de l'utilisateur"
                        textUser = new JLabel("Nom de l'utilisateur");
                        textUser.setHorizontalAlignment(SwingConstants.CENTER);
                        textUser.setFont(new Font("Times New Roman", Font.BOLD,(int)background.getWidth()/60));
                        textUser.setSize((int) background.getWidth(), (int)background.getHeight()/16);
                        textUser.setLocation(0, (int) background.getHeight()/3);
                        textUser.setLayout(null);

                        // Définition du nom de l'utilisateur
                        username = new JTextField();
                        username.setSize((int) background.getWidth()/10, (int)background.getHeight()/25);
                        username.setLocation((int) ((background.getWidth() / 2) - (username.getWidth() / 2)),
                                        textUser.getLocation().y + textUser.getHeight() + 10);
                        username.setLayout(null);

                        // Définition texte "difficulté"
                        textDifficulty = new JLabel("Difficulté");
                        textDifficulty.setHorizontalAlignment(SwingConstants.CENTER);
                        textDifficulty.setFont(new Font("Times New Roman", Font.BOLD,(int)background.getWidth()/60));
                        textDifficulty.setSize((int) background.getWidth()/10, (int)background.getHeight()/25);
                        textDifficulty.setLocation(
                                        (int) ((background.getWidth() / 2) - (textDifficulty.getWidth() / 2)),
                                        username.getLocation().y + username.getHeight() + 50);
                        textDifficulty.setLayout(null);

                        // Definition difficulté
                        String[] level = { "Facile", "Normal", "Difficile" };
                        difficulty = new JComboBox<String>(level);
                        difficulty.setSelectedIndex(0);
                        difficulty.setLayout(null);
                        difficulty.setLocation((int) ((background.getWidth() / 2) - (difficulty.getWidth() / 2)),
                                        textDifficulty.getLocation().y + textDifficulty.getHeight() + 10);
                        difficulty.setSize((int) background.getWidth()/11, (int)background.getHeight()/25);

                        // Définition texte "Type de flèche"
                        textType = new JLabel("Type de flèche");
                        textType.setHorizontalAlignment(SwingConstants.CENTER);
                        textType.setFont(new Font("Times New Roman", Font.BOLD,(int)background.getWidth()/60));
                        textType.setSize((int) background.getWidth()/5, (int)background.getHeight()/25);
                        textType.setLocation((int) ((screenSize.getWidth() / 2) - (textType.getWidth() / 2)),
                                        difficulty.getLocation().y + difficulty.getHeight() + 50);
                        textType.setLayout(null);

                        // Définition type de flèche
                        String[] arrowType1 = { "Aluminium", "Bois", "Carbone" }; // Tableau contenant les différents
                                                                                  // type de flèche
                        JComboBox<String> arrowType = new JComboBox<String>(arrowType1);
                        arrowType.setSelectedIndex(0);
                        arrowType.setLayout(null);
                        arrowType.setLocation((int) ((background.getWidth() / 2) - (arrowType.getWidth() / 2)),
                                        textType.getLocation().y + textType.getHeight() + 10);
                        arrowType.setSize((int) background.getWidth()/11, (int)background.getHeight()/25);

                        // Bouton pour lancer la partie
                        startGame = new JButton("Start Game");
                        startGame.setSize(100, 40);
                        startGame.setLocation((int) ((screenSize.getWidth() / 2) - (startGame.getWidth() / 2)),
                                        arrowType.getLocation().y + arrowType.getHeight() + 100);
                        // startGame.setLayout();

                        // Ajout à background
                        background.add(username);
                        background.add(difficulty);
                        background.add(arrowType);
                        background.add(startGame);
                        background.add(textDifficulty);
                        background.add(textType);
                        background.add(textUser);
                        background.add(title);

                        // (éventuellement animation du joueur en attente)
                } else if (type == 'g') {

                        // Panel contenant l'affichage du jeu
                        this.setResizable(false);
                        gameZone = new gameZone(1920, 930);
                        gameZone.setSize(gameZone.width, gameZone.height);
                        gameZone.setLocation(0, 0);

                        gameZone.repaint();
                        // Panel contenant les réglages pour la flèche
                        settings = new JPanel();
                        settings.setSize(1000, 150);
                        settings.setLocation(0, (int) screenSize.getHeight() - settings.getHeight());
                        settings.setLayout(new FlowLayout()); // Layout qui permet de mettre les éléments à la suite

                        // scrollbar angle
                        angle = new JScrollBar(JScrollBar.HORIZONTAL, 45, 1, 0, 90);
                        angle.setPreferredSize(new Dimension(200, 40));
                        // angle.setLocation(10, 10);
                        // angle.setLayout(null); //non sinon ça empêche l'affichage automatique géré
                        // par le layout

                        // Affichage angle
                        angleText = new JLabel("Angle : " + angle.getValue() + "°");
                        angle.addAdjustmentListener(new AdjustmentListener() {
                                @Override
                                public void adjustmentValueChanged(AdjustmentEvent e) {
                                        angleText.setText("Angle : " + e.getValue() + "°");

                                        // TODO repaint!
                                }
                        });
                        // addAdjustmentListener(new AdjustmentListener())

                        // scrollbar vitesse
                        speed = new JScrollBar(JScrollBar.HORIZONTAL, 50, 1, 0, 101);
                        speed.setPreferredSize(new Dimension(200, 40));
                        // speed.setLocation(angle.getLocation().x + angle.getWidth() + 10, 10);
                        // speed.setLayout(null); ////non sinon ça empêche l'affichage automatique géré
                        // par le layout

                        // Affichage speed
                        speedText = new JLabel("Speed : " + speed.getValue() + "%");
                        speed.addAdjustmentListener(new AdjustmentListener() {
                                @Override
                                public void adjustmentValueChanged(AdjustmentEvent e) {
                                        speedText.setText("Speed : " + e.getValue() + "%");
                                }
                        });
                        // JButton tirer
                        shoot = new JButton("Tirer");
                        shoot.setSize(200, 40);
                        // shoot.setLocation(speed.getLocation().x + speed.getWidth() + 10, 10);
                        shoot.setLayout(null);

                        // JButton menu (fait quitter la partie -> action event)
                        menu = new JButton("Menu");
                        menu.setSize(100, 50);
                        menu.setLocation(20, 20);
                        menu.setLayout(null);

                        // jlabel score
                        score = new JLabel("Score");
                        score.setSize(100, 40);
                        // score.setLocation(shoot.getLocation().x + shoot.getWidth() + 10, 10);
                        score.setLayout(null);

                        // ajout au panel principal
                        settings.add(angle);
                        settings.add(angleText);
                        settings.add(speed);
                        settings.add(speedText);
                        settings.add(shoot);
                        background.add(menu);
                        settings.add(score);

                        background.add(settings);
                        background.add(gameZone);
                        settings.setVisible(true);
                }
                // Ajout à la fenêtre
                this.add(background);

                setVisible(true);

        }

        // TODO inclure le type d
        @Override
        public void actionPerformed(ActionEvent e) {
<<<<<<< Updated upstream

                // TODO Auto-generated method stub
                /*
                 * game.player.name=JTextField de nom;
                 * game.masse=; //...
                 * game.difficulty=;
                 */
=======
                resize();
                //repaint();
>>>>>>> Stashed changes
        }
        
        public void resize(){
			background.setSize(this.getWidth()-this.getInsets().right-this.getInsets().left,this.getHeight()-this.getInsets().top-this.getInsets().bottom);
			
			title.setFont(new Font("Times New Roman", Font.BOLD,(int)((background.getHeight()+background.getWidth())/50)));
            title.setSize((int) background.getWidth(), (int)background.getHeight()/8);
            title.setLocation(0, (int) background.getHeight()/10);
            
            textUser.setFont(new Font("Times New Roman", Font.BOLD,(int)background.getWidth()/50));
            textUser.setSize((int) background.getWidth(), (int)background.getHeight()/16);
            textUser.setLocation(0, (int) background.getHeight()/3);           
            
            username.setSize((int) background.getWidth()/10, (int)background.getHeight()/25);
            username.setLocation((int) ((background.getWidth() / 2) - (username.getWidth() / 2)),
                                        textUser.getLocation().y + textUser.getHeight() + 10);
                                        
            textDifficulty.setFont(new Font("Times New Roman", Font.BOLD,(int)background.getWidth()/60));
            textDifficulty.setSize((int) background.getWidth()/10, (int)background.getHeight()/25);
            textDifficulty.setLocation((int) ((background.getWidth() / 2) - (textDifficulty.getWidth() / 2)),
                                        username.getLocation().y + username.getHeight() + 50);
                                        
            difficulty.setLocation((int) ((background.getWidth() / 2) - (difficulty.getWidth() / 2)),
            textDifficulty.getLocation().y + textDifficulty.getHeight() + 10);
            difficulty.setSize((int) background.getWidth()/11, (int)background.getHeight()/25);
            
            textType.setFont(new Font("Times New Roman", Font.BOLD,(int)background.getWidth()/60));
            textType.setSize((int) background.getWidth()/5, (int)background.getHeight()/25);
            textType.setLocation((int) ((background.getWidth() / 2) - (textType.getWidth() / 2)),
										difficulty.getLocation().y + difficulty.getHeight() + 50);
		    
		    arrowType.setLocation((int) ((background.getWidth() / 2) - (arrowType.getWidth() / 2)),
            textType.getLocation().y + textType.getHeight() + 10);
            arrowType.setSize((int) background.getWidth()/11, (int)background.getHeight()/25);
            
            
		}

}
