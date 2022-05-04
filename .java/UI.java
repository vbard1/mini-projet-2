import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.awt.Color;
import javax.swing.ImageIcon;

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
        JLabel image_cible;
        JLabel image;

        JPanel settings;
        JScrollBar angle;
        JScrollBar speed;
        JButton shoot;
        JButton menu;
        JLabel score;
        JLabel angleText;
        JLabel speedText;
        GameZone gameZone;

        JPanel gameEnd;
        JButton restart;
        JButton menuEndGame;
        JButton quit;
        JLabel announcement;

        String toMemory;

        /**
         * @param type le type de menu a affiche
         */
        public UI(char type) {
                // initialisation du timer

                this.type = type;
                // Acquisition de la taille de l'écran
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                // Définition de la fenêtre du menu
                this.setLocation(0, 0);
                this.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
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
                        String[] level = { "Mode sans échec", "Légère bise", "Tempête" };
                        difficulty = new JComboBox<String>(level);
                        difficulty.setSelectedIndex(0);
                        difficulty.setLayout(null);

                        // Bouton pour lancer la partie
                        startGame = new JButton("JOUER");
                        startGame.setBackground(new Color(51, 204, 102));
                        startGame.setForeground(Color.WHITE);

                        // arriere plan du menu
                        image_cible = new JLabel(new ImageIcon("./Images/archery_menu.png"), JLabel.LEFT);
                        image_cible.setSize(800, 800);

                        // contour du bouton
                        startGame.setBorder(BorderFactory.createLineBorder(Color.WHITE, 15));

                        // polices des textes
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

                        startGame.setFont(new Font("Comic sans MS", Font.BOLD, (int) background.getWidth() / 60));
                        startGame.setSize((int) background.getWidth() / 10, (int) background.getHeight() / 10);
                        startGame.setLocation((int) ((background.getWidth() / 2) - (startGame.getWidth() / 2)),
                                        difficulty.getLocation().y + difficulty.getHeight() + 100);

                        // Ajout à background
                        background.add(username);
                        background.add(difficulty);
                        background.add(startGame);
                        background.add(textDifficulty);
                        background.add(textUser);
                        background.add(title);
                        background.add(image_cible);
                        resizeTimer.start();

                } else if (type == 'g') {

                        // Panel contenant l'affichage du jeu
                        gameZone = new GameZone(background.getWidth(), (int) (background.getHeight() * 0.85));
                        gameZone.setSize(gameZone.width, gameZone.height);
                        gameZone.setLocation(0, 0);

                        gameZone.repaint();
                        // Panel contenant les réglages pour la flèche
                        settings = new JPanel();
                        settings.setSize(this.getWidth(), (int) (background.getHeight() * 0.15));
                        settings.setLocation(0, (int) (background.getHeight() - settings.getHeight()));
                        settings.setLayout(new FlowLayout()); // Layout qui permet de mettre les éléments à la suite

                        // jlabel score
                        score = new JLabel("Score");
                        score.setSize(100, 40);
                        score.setLayout(null);

                        // scrollbar angle
                        angle = new JScrollBar(JScrollBar.HORIZONTAL, 45, 1, 1, 90);
                        angle.setPreferredSize(new Dimension(200, 40));

                        // Affichage angle
                        angleText = new JLabel("Angle : " + angle.getValue() + "°");
                        angle.addAdjustmentListener(new AdjustmentListener() {
                                @Override
                                public void adjustmentValueChanged(AdjustmentEvent e) {
                                        angleText.setText("Angle : " + e.getValue() + "°");
                                }
                        });

                        // scrollbar vitesse
                        speed = new JScrollBar(JScrollBar.HORIZONTAL, 50, 1, 1, 101);
                        speed.setPreferredSize(new Dimension(200, 40));

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
                        shoot.setLayout(null);

                        // JButton menu (fait quitter la partie -> action event)
                        menu = new JButton("Menu");

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
                        String[] arrowType1 = { "Carbone", "Aluminium", "Bois" }; // Tableau contenant les différents//
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
                        // background.add(menu);
                        settings.add(score);
                        // ajout à background
                        background.add(settings);
                        background.add(gameZone);

                }

                // Ajout à la fenêtre
                this.add(background);
                this.setVisible(true);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == resizeTimer && type == 'm') {
                        resize();
                }

        }

        /**
         * @method permet de redimensionner en temps reel la fenetre aux dimensions
         *         choisies par l'utilisateur, ne fonctionne que pour le type 'm' :
         *         menu.
         */
        public void resize() {
                background.setSize(this.getWidth() - this.getInsets().right - this.getInsets().left,
                                this.getHeight() - this.getInsets().top - this.getInsets().bottom);

                title.setFont(new Font("Comic sans MS", Font.BOLD,
                                (int) ((background.getHeight() + background.getWidth()) / 40)));
                title.setSize((int) background.getWidth(), (int) background.getHeight() / 8);
                title.setLocation(0, (int) background.getHeight() / 10);

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

                startGame.setFont(new Font("Comic sans MS", Font.BOLD, (int) background.getWidth() / 60));
                startGame.setSize((int) background.getWidth() / 10, (int) background.getHeight() / 10);
                startGame.setLocation((int) ((background.getWidth() / 2) - (startGame.getWidth() / 2)),
                                difficulty.getLocation().y + difficulty.getHeight() + 100);
                startGame.setBorder(BorderFactory.createLineBorder(Color.WHITE, 15 / 2));

        }

        /**
         * @method Met fin a la partie et prepare la suivante en nettoyant l'espace de
         *         jeu et en affichant les messages de fin.
         * @param type     victoire ou defaite, 'v' ou 'd'
         * @param p        joueur associe a la partie
         * @param maxRound nombre de manches de la partie
         */
        public void gameEnd(char type, Player p, int maxRound) {
                // Création du JPanel contenant le résustat/choix de fin de partie, style popup
                // (relatif), au centre de la fenêtre de jeu
                gameEnd = new JPanel();
                gameEnd.setPreferredSize(new Dimension((int) (this.getWidth() / 2), (int) (this.getHeight() / 2)));
                SpringLayout layout = new SpringLayout();
                gameEnd.setLocation((int) (this.getWidth() / 4), (int) (this.getHeight() / 4));

                gameEnd.setBackground(new Color(250, 255, 224));
                gameEnd.setBorder(BorderFactory.createLineBorder(Color.black, 5));
                // JPanel announcement : annonce la victoire ou la défaite du joueur
                announcement = new JLabel();
                if (type == 'v') {
                        announcement.setText("Victoire! Vous avez touché la cible " + p.score + " fois sur " + maxRound
                                        + ".");
                } else if (type == 'd') {
                        announcement.setText("Défaite! Vous avez touché la cible " + p.score + " fois sur " + maxRound
                                        + ".");
                }
                announcement.setFont(new Font("Comic sans MS", Font.BOLD, (int) background.getWidth() / 60));

                // JButton menu : retour au menu de création de partie
                menuEndGame = new JButton("Menu");
                menuEndGame.setPreferredSize(new Dimension((int) (this.getWidth() / 8), (int) (this.getHeight() / 8)));
                menuEndGame.setLocation(gameEnd.getWidth() / 2 - (menuEndGame.getWidth() / 2),
                                announcement.getLocation().y + 50);

                // JButton restart : recommencer une partie avec les mêmes réglages
                restart = new JButton("Rejouer");
                restart.setPreferredSize(new Dimension((int) (this.getWidth() / 8), (int) (this.getHeight() / 8)));
                restart.setLocation(gameEnd.getWidth() / 2 - (restart.getWidth() / 2),
                                menuEndGame.getLocation().y + menuEndGame.getWidth() + 10);

                // Jbutton quit : quitte le jeu (fermeture fenêtre at arrêt programme)
                quit = new JButton("Quiter");
                quit.setPreferredSize(new Dimension((int) (this.getWidth() / 8), (int) (this.getHeight() / 8)));
                quit.setLocation(gameEnd.getWidth() / 2 - (quit.getWidth() / 2),
                                restart.getLocation().y + restart.getWidth() + 10);

                // AJout des éléments au Jpanel de fin de partie
                gameEnd.add(announcement);
                gameEnd.add(Box.createRigidArea(new Dimension(5, 5)));
                gameEnd.add(menu);
                gameEnd.add(Box.createRigidArea(new Dimension(5, 5)));
                gameEnd.add(restart);
                gameEnd.add(Box.createRigidArea(new Dimension(5, 5)));
                gameEnd.add(quit);
                gameEnd.add(Box.createRigidArea(new Dimension(5, 5)));
                gameZone.add(gameEnd);
                this.setVisible(true);
        }

        /**
         * @method stocke le dernier score dans un fichier texte
         *         peut evoluer en leaderboard (tableau des meilleurs scores)
         */
        public void storeScore() {
                System.out.println("Storing data...");
                try {
                        File leaderboard = new File("Leaderboard.txt");
                        BufferedReader eye = new BufferedReader(new FileReader("Leaderboard.txt"));
                        FileWriter pen = new FileWriter("Leaderboard.txt");
                        ArrayList<String> lb = new ArrayList<String>();

                        while (eye.readLine() != null) {
                                eye.readLine();
                                lb.add(eye.readLine());
                        }
                        lb.add(toMemory);
                        for (String line : lb)
                                pen.write(line);

                        pen.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }

                System.out.print("...done.");
        }
}
