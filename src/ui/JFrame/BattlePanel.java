package ui.JFrame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

import characters.Player;
import skills.Skill;
import battle.Battle;
import battle.Action;

public class BattlePanel extends JPanel {

    private MainFrame mainFrame;

    private JLabel enemyNameLabel;
    private JLabel enemyHpLabel;
    private JProgressBar enemyHpBar;

    private JLabel playerNameLabel;
    private JLabel playerHpLabel;
    private JLabel playerClassLabel;
    private JLabel playerLevelLabel;
    private JProgressBar playerHpBar;

    private JTextArea battleLog;
    private JScrollPane scrollPane;

    private JButton attackButton;
    private JButton defendButton;
    private JButton skillButton;
    private JButton inventoryButton;
    private JButton runButton;

    public BattlePanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());

        // Enemy Top Status
        JPanel topPanel = new JPanel(new GridLayout(2, 1));

        topPanel.setBorder(
                BorderFactory.createTitledBorder("Enemy"));

        enemyNameLabel = new JLabel("Enemy : None");
        enemyHpLabel = new JLabel("HP");
        topPanel.add(enemyNameLabel);
        topPanel.add(enemyHpLabel);
        add(topPanel, BorderLayout.NORTH);

        // Battle Log in center
        battleLog = new JTextArea();
        battleLog.setEditable(false);
        battleLog.setFocusable(false);
        battleLog.setLineWrap(true);
        battleLog.setWrapStyleWord(true);
        scrollPane = new JScrollPane(battleLog);
        battleLog.setText("This is a fight! \n");
        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Battle Log"));
        add(scrollPane, BorderLayout.CENTER);

        // Player bottom status
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel playerInfoPanel = new JPanel(new GridLayout(4, 1));

        bottomPanel.setBorder(
                BorderFactory.createTitledBorder("Player"));

        playerNameLabel = new JLabel("Hero : ---");
        playerHpLabel = new JLabel("HP: --/--");
        playerLevelLabel = new JLabel("Level: -");
        playerClassLabel = new JLabel("Class: ----");
        playerInfoPanel.add(playerNameLabel);
        playerInfoPanel.add(playerHpLabel);
        playerInfoPanel.add(playerLevelLabel);
        playerInfoPanel.add(playerClassLabel);
        bottomPanel.add(playerInfoPanel, BorderLayout.CENTER);

        // Button Panel below
        JPanel buttonPanel = new JPanel();
        attackButton = new JButton("Attack");
        attackButton.addActionListener(e -> {
            Battle battle = mainFrame.getGameManager().getCurrentBattle();

            String message = battle.playerAction(Action.ATTACK);

            battleLog.append(message + "\n");

            if (battle.isOver()) {
                refreshBattle();
                setActionButtonEnabled(false);
                return;
            }

            message = battle.gameTurn();
            battleLog.append(message + "\n");

            refreshBattle();

            if (battle.isOver()) {
                setActionButtonEnabled(false);
                return;
            }
        });
        defendButton = new JButton("Defend");
        defendButton.addActionListener(e -> {
            Battle battle = mainFrame.getGameManager().getCurrentBattle();

            String message = battle.playerAction(Action.DEFEND);
            battleLog.append(message + "\n");

            if (battle.isOver()) {
                setActionButtonEnabled(false);
                return;
            }

            message = battle.gameTurn();
            battleLog.append(message + "\n");

            refreshBattle();

            if (battle.isOver()) {
                setActionButtonEnabled(false);
                return;
            }
        });
        skillButton = new JButton("Skill");
        skillButton.addActionListener(e -> {
            Battle battle = mainFrame.getGameManager().getCurrentBattle();

            Skill skill = battle.getPlayer().getSkillManager().getSkill(0);

            if (!skill.isReady()) {
                battleLog.append(
                        skill.getName() +
                                " est en recharge (" +
                                skill.getCurrentCooldown() +
                                " tour(s) )\n");
                return;
            }

            String message = battle.useSkill(skill);
            battleLog.append(message + "\n");

            if (battle.isOver()) {
                refreshBattle();
                setActionButtonEnabled(false);
                return;
            }

            message = battle.gameTurn();
            battleLog.append(message + "\n");

            refreshBattle();

            if (battle.isOver()) {
                setActionButtonEnabled(false);
                return;
            }

        });
        inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(e -> {

        });
        runButton = new JButton("Run");
        runButton.addActionListener(e -> {
            mainFrame.showScreen(MainFrame.GAME);
        });

        buttonPanel.add(attackButton);
        buttonPanel.add(defendButton);
        buttonPanel.add(skillButton);
        buttonPanel.add(inventoryButton);
        buttonPanel.add(runButton);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void refreshBattle() {
        // tomove battleLog.setText("This is a fight");

        Player player = mainFrame.getGameManager().getPlayer();
        Battle battle = mainFrame.getGameManager().getCurrentBattle();
        playerNameLabel.setText(player.getName());
        playerClassLabel.setText("Class : " + player.getPlayerClass());
        playerLevelLabel.setText("Level : " + player.getLevel());
        playerHpLabel.setText("Hp : " + player.getHp() + "/" + player.getMaxHp());

        enemyNameLabel.setText(battle.getEnemy().getName());
        enemyHpLabel.setText("Condition : " + battle.getEnemy().getHealthCondition());
    }

    public void setActionButtonEnabled(boolean enabled) {
        attackButton.setEnabled(enabled);
        defendButton.setEnabled(enabled);
        skillButton.setEnabled(enabled);
        inventoryButton.setEnabled(enabled);
    }
}
