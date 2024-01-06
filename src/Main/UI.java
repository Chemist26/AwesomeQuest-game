package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UI {

    GameManager gm;
    JFrame window;
    public JTextArea messageText;
    public JPanel[] bgPanel = new JPanel[10];
    public JLabel[] bgLabel = new JLabel[10];

    // PLAYER UI
    JPanel lifePanel;
    JLabel[] lifeLabel = new JLabel[6];
    JPanel inventoryPanel;
    public JLabel swordLabel, shieldLabel, lanternLabel;

    // GAME OVER UI
    public JLabel titleLabel;
    public JButton restartButton;

    public UI(GameManager gm) {
        this.gm = gm;
        createMainField();
        createPlayerField();
        createGameOverField();
        generateScene();

        window.setVisible(true);
    }



    // Method to create the main visual interface
    public void createMainField() {
        // Creates the main window
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        // Creates a text area for displaying messages
        messageText = new JTextArea("THIS IS SAMPLE TEXT");
        messageText.setBounds(50, 410, 700, 150);
        messageText.setBackground(Color.black);
        messageText.setForeground(Color.white);
        messageText.setEditable(false);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setFont(new Font("Book Antique", Font.PLAIN, 26));
        window.add(messageText);
    }

    // Method to create the background panel with an image
    public void createBackground(int bgNum, String bgFileName) {
        // Creates a panel for the background
        bgPanel[bgNum] = new JPanel();
        bgPanel[bgNum].setBounds(50, 50, 700, 350);
        bgPanel[bgNum].setBackground(Color.black);
        bgPanel[bgNum].setLayout(null);
        bgPanel[bgNum].setVisible(false);
        window.add(bgPanel[bgNum]);

        // Creates a label for displaying the background image
        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0, 0, 700, 350);

        // Loads the background image using getClass().getClassLoader().getResource
        ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource(bgFileName));
        bgLabel[bgNum].setIcon(bgIcon);

        // Adds the label to the background panel
    }

    /**
     * Creates an object with a specified image and associates a popup menu with choices.
     *
     * @param bgNum       The index of the background panel where the object will be added.
     * @param objX        The x-coordinate of the object.
     * @param objY        The y-coordinate of the object.
     * @param objWidth    The width of the object.
     * @param objHeight   The height of the object.
     * @param objFileName The file name of the image for the object.
     * @param choice1Name The name of the first choice in the popup menu.
     * @param choice2Name The name of the second choice in the popup menu.
     * @param choice3Name The name of the third choice in the popup menu.
     */
    public void createObject(int bgNum, int objX, int objY, int objWidth, int objHeight, String objFileName,
                             String choice1Name, String choice2Name, String choice3Name, String choice1Command, String choice2Command, String choice3Command) {

        // Create a popup menu with specified choices
        JPopupMenu popMenu = new JPopupMenu(choice1Name);

        JMenuItem[] menuItem = new JMenuItem[4]; //  Use [1], [2], [3] to match choices
        menuItem[1] = new JMenuItem(choice1Name);
        menuItem[1].addActionListener(gm.actionHandler);
        menuItem[1].setActionCommand(choice1Command);
        popMenu.add(menuItem[1]);

        menuItem[2] = new JMenuItem(choice2Name);
        menuItem[2].addActionListener(gm.actionHandler);
        menuItem[2].setActionCommand(choice2Command);
        popMenu.add(menuItem[2]);

        menuItem[3] = new JMenuItem(choice3Name);
        menuItem[3].addActionListener(gm.actionHandler);
        menuItem[3].setActionCommand(choice3Command);
        popMenu.add(menuItem[3]);

        // CREATE OBJECTS
        JLabel objectlabel = new JLabel();
        objectlabel.setBounds(objX, objY, objWidth, objHeight);
        //Check blank space
//        objectlabel.setOpaque(true);
//        objectlabel.setBackground(Color.blue);


        ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource(objFileName));
        objectlabel.setIcon(objectIcon);

        objectlabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popMenu.show(objectlabel, e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        bgPanel[bgNum].add(objectlabel);
    }

    public void createArrowButton(int bgNum, int x, int y, int width, int height, String arrowFileName, String command) {
        ImageIcon arrowIcon = new ImageIcon(getClass().getClassLoader().getResource(arrowFileName));

        JButton arrowButton = new JButton();

        arrowButton.setBounds(x, y, width, height);
        arrowButton.setBackground(null);
        arrowButton.setContentAreaFilled(false);
        arrowButton.setFocusPainted(false);
        arrowButton.setIcon(arrowIcon);
        arrowButton.addActionListener(gm.actionHandler);
        arrowButton.setActionCommand(command);
        arrowButton.setBorderPainted(false);

        bgPanel[bgNum].add(arrowButton);
    }


    private void createPlayerField() {
        lifePanel = new JPanel();
        lifePanel.setBounds(50, 0, 250, 50);  // Adjust the bounds as needed
        lifePanel.setBackground(Color.black);
        lifePanel.setLayout(new GridLayout(1, 5));
        window.add(lifePanel);

        ImageIcon lifeIcon = new ImageIcon(getClass().getClassLoader().getResource("heart 35x35.png"));
        Image image = lifeIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        lifeIcon = new ImageIcon(image);

        for (int i = 1; i < 6; i++) {
            lifeLabel[i] = new JLabel();
            lifeLabel[i].setIcon(lifeIcon);
            lifePanel.add(lifeLabel[i]);
        }

        inventoryPanel = new JPanel();
        inventoryPanel.setBounds(630, 0, 100, 50);
        inventoryPanel.setBackground(Color.black);
        inventoryPanel.setLayout(new GridLayout(1, 5));
        window.add(inventoryPanel);

        // CREATE ITEMS
        // Sword item
        swordLabel = new JLabel();
        ImageIcon swordIcon = new ImageIcon(getClass().getClassLoader().getResource("plain-dagger.png"));
        image = swordIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        swordIcon = new ImageIcon(image);
        swordLabel.setIcon(swordIcon);
        inventoryPanel.add(swordLabel);

        // Shield item
        shieldLabel = new JLabel();
        ImageIcon shieldIcon = new ImageIcon(getClass().getClassLoader().getResource("round-shield.png"));
        image = shieldIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        shieldIcon = new ImageIcon(image);
        shieldLabel.setIcon(shieldIcon);
        inventoryPanel.add(shieldLabel);

        // Lantern item
        lanternLabel = new JLabel();
        ImageIcon lanternIcon = new ImageIcon(getClass().getClassLoader().getResource("lantern-flame.png"));
        image = lanternIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        lanternIcon = new ImageIcon(image);
        lanternLabel.setIcon(lanternIcon);
        inventoryPanel.add(lanternLabel);
    }

    public void createGameOverField() {
        titleLabel = new JLabel("", JLabel.CENTER);
        titleLabel.setBounds(200, 150, 400, 200);
        titleLabel.setForeground(Color.red);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 70));
        titleLabel.setVisible(false);
        window.add(titleLabel);

        restartButton = new JButton();
        restartButton.setBounds(340, 320, 120, 50);
        restartButton.setBorder(null);
        restartButton.setBackground(null);
        restartButton.setForeground(Color.white);
        restartButton.setFocusPainted(false);
        restartButton.addActionListener(gm.actionHandler);
        restartButton.setActionCommand("restart");
        restartButton.setVisible(false);
        window.add(restartButton);
    }

    public void generateScene() {
        //SCENE 1
        createBackground(1, "christmas-background.png");
        createObject(1, 430, 100, 215, 200, "hut3.png", "Look", "Talk", "Rest", "lookHut", "talkHut", "restHut");
//        createObject(1, 230, 130, 150, 150, "Character2.png");
        createObject(1, 250, 230, 150, 150, "Character1.png", "Look", "Talk", "Attack", "lookNPC", "talkNPC", "attackNPC");
        createArrowButton(1, 4, 110, 50, 50, "leftArrow.png", "goScene2");
        createArrowButton(1, 586, 110, 50, 50, "rightArrow.png", "goScene3");
        bgPanel[1].add(bgLabel[1]);

        //SCENE 2
        createBackground(2, "snowCave.png");

        createObject(2, 150, 0, 360, 50, "starSky.png", "Look", "", "", "lookStars", "", "");

        createObject(2, 330, 200, 100, 100, "signSnow.png", "Read", "", "", "readSnowSign", "", "");

        createObject(2, 60, 250, 100, 100, "chest.png", "Look", "Talk", "Loot", "lookChest", "talkChest", "openChest");

        createObject(2, 70, 130, 160, 160, "Empty2.png", "Look", "Talk", "Enter", "lookCave", "talkCave", "enterCave");

        createObject(2, 50, 100, 215, 200, "snowHouse.png", "", "", "", "", "", "");

        createArrowButton(2, 646, 110, 50, 50, "rightArrow.png", "goScene1");
        bgPanel[2].add(bgLabel[2]);

        //SCENE 3
        createBackground(3, "cave-inside.png");
        createArrowButton(3, 650, 150, 50, 50, "rightArrow.png", "goScene2");
        bgPanel[3].add((bgLabel[3]));

    }

}
