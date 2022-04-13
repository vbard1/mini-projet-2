import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.*;

import java.awt.Graphics;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import javax.swing.ImageIcon;

import java.awt.Image;

//TODO mettre en relatif aux dimensions de la fenetre
//TODO @abdel pour résoudre les barres qui s'affichent pas https://stackoverflow.com/questions/16737767/scroll-bar-not-visible-in-jpanel

public class UI extends JFrame implements ActionListener {

        Timer resizeTimer;
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
        JButton preview;
        JLabel image_cible ;
        JLabel image;


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
                // initialisation du timer

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
                background.setBackground(new Color(250, 255, 224)); // COULEUR FOND
                background.setLayout(null);

    

                if (type == 'm') {// Affichage du menu

                        resizeTimer = new Timer(10, this);
                        

                        // Définition titre
                        title = new JLabel("Tir à l'arc");
                        title.setHorizontalAlignment(SwingConstants.CENTER);
                        title.setLayout(null);

                        // Définition texte "Nom de l'utilisateur"
                        textUser = new JLabel("Nom de l'utilisateur");
                        textUser.setHorizontalAlignment(SwingConstants.CENTER);
                        textUser.setLayout(null);

                        // Définition du nom de l'utilisateur
                        username = new JTextField();
                        username.setLayout(null);

                        // Définition texte "difficulté"
                        textDifficulty = new JLabel("Difficulté");

                        textDifficulty.setHorizontalAlignment(SwingConstants.CENTER);
                        textDifficulty.setLayout(null);

                        // Definition difficulté
                        String[] level = { "Facile", "Normal", "Difficile" };
                        difficulty = new JComboBox<String>(level);
                        difficulty.setSelectedIndex(0);
                        difficulty.setLayout(null);

                        // Bouton pour lancer la partie
                        startGame = new JButton("JOUER");
                        startGame.setBackground(new Color(51, 204, 102));
                        startGame.setForeground(Color.WHITE);

                        //startGame.setLayout();
                        
                        // Ajout image de fond
                        //ImageIcon imageIcon = new ImageIcon(new ImageIcon("./Images/archery_menu2.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
						//image_cible.setIcon(imageIcon);
						
						image_cible = new JLabel(new ImageIcon("./Images/archery_menu.png"),JLabel.LEFT);
						image_cible.setSize(100, 200);
						
						//Image imageTailleResize = new ImageIcon("./Images/archery_menu.png").getImage().getScaledInstance(image_cible.getWidth(), image_cible.getHeight(), Image.SCALE_DEFAULT);
						//image_cible.setIcon(new ImageIcon(imageTailleResize));
					
				
				
						

                        startGame.setBorder(BorderFactory.createLineBorder(Color.WHITE, 15));
                        // startGame.setLayout();

                        // Ajout image de fond
                        // image = new JLabel(new ImageIcon("./Images/archery_menu.png"));
                        // image.setBounds(500,500,100,150);

                        title.setFont(new Font("Comic sans MS", Font.BOLD,
                                        (int) ((background.getHeight() + background.getWidth()) / 40)));
                        title.setSize((int) background.getWidth(), (int) background.getHeight() / 8);
                        title.setLocation(0, (int) background.getHeight() / 10);

                        textUser.setFont(new Font("Comic sans MS", Font.PLAIN, (int) background.getWidth() / 50));
                        textUser.setSize((int) background.getWidth(), (int) background.getHeight() / 16);
                        textUser.setLocation(0, (int) background.getHeight() / 3);

                        username.setFont(new Font("Comic sans", Font.PLAIN, (int) background.getWidth() / 90));
                        username.setSize((int) background.getWidth() / 10, (int) background.getHeight() / 25);
                        username.setLocation((int) ((background.getWidth() / 2) - (username.getWidth() / 2)),
                                        textUser.getLocation().y + textUser.getHeight() + 10);

                        textDifficulty.setFont(new Font("Comic sans MS", Font.PLAIN, (int) background.getWidth() / 60));
                        textDifficulty.setSize((int) background.getWidth() / 10, (int) background.getHeight() / 25);
                        textDifficulty.setLocation(
                                        (int) ((background.getWidth() / 2) - (textDifficulty.getWidth() / 2)),
                                        username.getLocation().y + username.getHeight() + 50);

                        difficulty.setFont(new Font("Comic sans", Font.PLAIN, (int) background.getWidth() / 90));
                        difficulty.setLocation((int) ((background.getWidth() / 2) - (difficulty.getWidth() / 2)),
                                        textDifficulty.getLocation().y + textDifficulty.getHeight() + 10);
                        difficulty.setSize((int) background.getWidth() / 11, (int) background.getHeight() / 25);
                        
                        /*
                         * textType.setFont(new Font("Comic sans MS",
                         * Font.PLAIN,(int)background.getWidth()/60));
                         * textType.setSize((int) background.getWidth()/5,
                         * (int)background.getHeight()/25);
                         * textType.setLocation((int) ((background.getWidth() / 2) -
                         * (textType.getWidth() / 2)),
                         * difficulty.getLocation().y + difficulty.getHeight() + 50);
                         * 
                         * arrowType.setFont(new Font("Comic sans",
                         * Font.PLAIN,(int)background.getWidth()/90));
                         * arrowType.setLocation((int) ((background.getWidth() / 2) -
                         * (arrowType.getWidth() / 2)),
                         * textType.getLocation().y + textType.getHeight() + 10);
                         * arrowType.setSize((int) background.getWidth()/11,
                         * (int)background.getHeight()/25);
                         */

                        startGame.setFont(new Font("Comic sans MS", Font.BOLD, (int) background.getWidth() / 60));
                        startGame.setSize((int) background.getWidth() / 10, (int) background.getHeight() / 10);
                        startGame.setLocation((int) ((background.getWidth() / 2) - (startGame.getWidth() / 2)),
                                        difficulty.getLocation().y + difficulty.getHeight() + 100);


                        // Ajout à background
                        background.add(username);
                        background.add(difficulty);
                        // background.add(arrowType);
                        background.add(startGame);
                        background.add(textDifficulty);
                        // background.add(textType);
                        background.add(textUser);
                        background.add(title);
                        background.add(image_cible);
                        // background.add(image);
                        resizeTimer.start();


                        // (éventuellement animation du joueur en attente)
                } else if (type == 'g') {

                        // Panel contenant l'affichage du jeu

                        gameZone = new gameZone(background.getWidth(), (int)(background.getHeight()*0.85));
                        gameZone.setSize(gameZone.width, gameZone.height);
                        gameZone.setLocation(0, 0);

                        gameZone.repaint();
                        // Panel contenant les réglages pour la flèche
                        settings = new JPanel();
                        settings.setSize(this.getWidth(),(int)(background.getHeight()*0.15) );
                        settings.setLocation(0, (int) (background.getHeight() - settings.getHeight()));
                        settings.setLayout(new FlowLayout()); // Layout qui permet de mettre les éléments à la suite

                        // scrollbar angle
                        angle = new JScrollBar(JScrollBar.HORIZONTAL, 45, 1, 0, 90);
                        angle.setPreferredSize(new Dimension(200, 40));
                        // angle.setLocation(10, 10);m
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
                        // menu.setLayout(null);

                        // jlabel score
                        score = new JLabel("Score");
                        score.setSize(100, 40);
                        // score.setLocation(shoot.getLocation().x + shoot.getWidth() + 10, 10);
                        score.setLayout(null);

                        // JButton Preview
                        preview = new JButton("Preview");
                        preview.setSize(200, 40);
                        // shoot.setLocation(speed.getLocation().x + speed.getWidth() + 10, 10);
                        preview.setLayout(null);

                        // Définition texte "Type de flèche"
                        textType = new JLabel("Type de flèche");
                        textType.setHorizontalAlignment(SwingConstants.CENTER);
                        textType.setFont(new Font("Times New Roman", Font.BOLD, (int) background.getWidth() / 60));
                        textType.setLayout(null);

                        // Définition type de flèche
                        String[] arrowType1 = { "Aluminium", "Bois", "Carbone" }; // Tableau contenant les différents
                                                                                  // type de flèche
                        arrowType = new JComboBox<String>(arrowType1);
                        arrowType.setSelectedIndex(0);
                        arrowType.setLayout(null);

                        // ajout au panel principal
                        settings.add(menu);
                        settings.add(angle);
                        settings.add(angleText);
                        settings.add(speed);
                        settings.add(speedText);
                        settings.add(arrowType);
                        settings.add(preview);
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
                if (e.getSource() == resizeTimer && type == 'm') {
                        resize();
                }
        }

        


        public void resize(){
                background.setSize(this.getWidth() - this.getInsets().right - this.getInsets().left,
                                this.getHeight() - this.getInsets().top - this.getInsets().bottom);

                title.setFont(new Font("Comic sans MS", Font.BOLD,
                                (int) ((background.getHeight() + background.getWidth()) / 40)));
                title.setSize((int) background.getWidth(), (int) background.getHeight() / 8);
                title.setLocation(0, (int) background.getHeight() / 10);

                //textUser.setFont(new Font("Comic sans MS", Font.PLAIN, (int) background.getWidth() / 50));
                textUser.setSize((int) background.getWidth(), (int) background.getHeight() / 16);
                textUser.setLocation(0, (int) background.getHeight() / 3);

                username.setFont(new Font("Comic sans", Font.PLAIN, (int) background.getWidth() / 90));
                username.setSize((int) background.getWidth() / 10, (int) background.getHeight() / 25);
                username.setLocation((int) ((background.getWidth() / 2) - (username.getWidth() / 2)),
                                textUser.getLocation().y + textUser.getHeight() + 10);

                textDifficulty.setFont(new Font("Comic sans MS", Font.PLAIN, (int) background.getWidth() / 60));
                textDifficulty.setSize((int) background.getWidth() / 10, (int) background.getHeight() / 25);
                textDifficulty.setLocation((int) ((background.getWidth() / 2) - (textDifficulty.getWidth() / 2)),
                                username.getLocation().y + username.getHeight() + 50);

                difficulty.setFont(new Font("Comic sans", Font.PLAIN, (int) background.getWidth() / 90));
                difficulty.setLocation((int) ((background.getWidth() / 2) - (difficulty.getWidth() / 2)),
                                textDifficulty.getLocation().y + textDifficulty.getHeight() + 10);
                difficulty.setSize((int) background.getWidth() / 11, (int) background.getHeight() / 25);

                /*
                 * textType.setFont(new Font("Comic sans MS",
                 * Font.PLAIN,(int)background.getWidth()/60));
                 * textType.setSize((int) background.getWidth()/5,
                 * (int)background.getHeight()/25);
                 * textType.setLocation((int) ((background.getWidth() / 2) -
                 * (textType.getWidth() / 2)),
                 * difficulty.getLocation().y + difficulty.getHeight() + 50);
                 * 
                 * arrowType.setFont(new Font("Comic sans",
                 * Font.PLAIN,(int)background.getWidth()/90));
                 * arrowType.setLocation((int) ((background.getWidth() / 2) -
                 * (arrowType.getWidth() / 2)),
                 * textType.getLocation().y + textType.getHeight() + 10);
                 * arrowType.setSize((int) background.getWidth()/11,
                 * (int)background.getHeight()/25);
                 */

                startGame.setFont(new Font("Comic sans MS", Font.BOLD, (int) background.getWidth() / 60));
                startGame.setSize((int) background.getWidth() / 10, (int) background.getHeight() / 10);
                startGame.setLocation((int) ((background.getWidth() / 2) - (startGame.getWidth() / 2)),
                                difficulty.getLocation().y + difficulty.getHeight() + 100);
                startGame.setBorder(BorderFactory.createLineBorder(Color.WHITE, 15 / 2));
                
                
                
        }

}
