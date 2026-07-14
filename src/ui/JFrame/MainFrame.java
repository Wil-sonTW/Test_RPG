package ui.JFrame;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import characters.PlayerClass;
import game.GameManager;

public class MainFrame extends JFrame {
    
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MainMenuPanel menuPanel;
    private CharacterCreationPanel characterCreationPanel;
    private GamePanel gamePanel;
    private GameManager gameManager;


    public static final String MENU = "menu";
    public static final String CHARACTER = "character";
    public static final String GAME = "game";

    public MainFrame() {

        setTitle("Test RPG");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameManager = new GameManager();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        //Create scenes
        menuPanel = new MainMenuPanel(this);
        characterCreationPanel = new CharacterCreationPanel(this);
        gamePanel = new GamePanel(this);

        //Add the scenes to the Layout
        mainPanel.add(menuPanel, MENU);
        mainPanel.add(characterCreationPanel, CHARACTER);
        mainPanel.add(gamePanel, GAME);

        cardLayout.show(mainPanel, MENU);

        add(mainPanel);

        setVisible(true);
    }

    public void startNewGame(String name, PlayerClass playerClass) {

        gameManager.createPlayer(name, playerClass);

        gamePanel.playerInfo();

        showScreen(GAME);
    }

    public void showScreen(String screenName) {
        cardLayout.show(mainPanel, screenName);
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
