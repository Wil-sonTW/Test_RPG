package ui.JFrame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import characters.Player;

public class GamePanel extends JPanel {

    private JLabel nameLabel;
    private JLabel classLabel;
    private JLabel hpLabel;
    private JLabel levelLabel;
    private JLabel xpLabel;

    private MainFrame mainFrame;

    private JPanel statsPanel;
    private JPanel centerPanel;
    private JPanel buttonPanel;

    private JButton battleButton;
    private JButton inventoryButton;

    public GamePanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        statsPanel = new JPanel();
            statsPanel.setLayout(new GridLayout(5, 1));
            statsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder("Character Info"),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                )
            );
        centerPanel = new JPanel();
        buttonPanel = new JPanel();
            battleButton = new JButton("Battle");
            inventoryButton = new JButton("Inventory");

        setLayout(new BorderLayout());

        add(statsPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        nameLabel = new JLabel();
        classLabel = new JLabel();
        hpLabel = new JLabel();
        levelLabel = new JLabel();
        xpLabel = new JLabel();

        statsPanel.add(nameLabel);
        statsPanel.add(classLabel);
        statsPanel.add(hpLabel);
        statsPanel.add(levelLabel);
        statsPanel.add(xpLabel);

        buttonPanel.add(battleButton);
        buttonPanel.add(inventoryButton);
    }

    public void playerInfo() {
        Player player = mainFrame.getGameManager().getPlayer();

        nameLabel.setText("|Name : " + player.getName());
        classLabel.setText("|Class : " + player.getPlayerClass());
        hpLabel.setText("|Hp : " + player.getHp() + "/" + player.getMaxHp());
        levelLabel.setText("|Level : " + player.getLevel());
        xpLabel.setText("|XP : " + player.getXp() + "/" + player.getXpToNextLevel());
    }
}
