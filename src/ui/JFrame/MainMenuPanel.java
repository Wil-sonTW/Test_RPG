package ui.JFrame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel {
    
    private JLabel titleLabel;
    private JButton newGameButton;
    private MainFrame mainFrame;

    public MainMenuPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        titleLabel = new JLabel("Test RPG");
        newGameButton = new JButton("New Game");
            newGameButton.addActionListener(e -> {
                mainFrame.showScreen(MainFrame.CHARACTER);
            });

        add(titleLabel);
        add(newGameButton);
    }
}
