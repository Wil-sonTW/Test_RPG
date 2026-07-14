package ui.JFrame;

import characters.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CharacterCreationPanel extends JPanel {
    
    private JTextField nameField;
    private JComboBox<PlayerClass> classComboBox;
    private JButton createButton;
    private MainFrame mainFrame;

    public CharacterCreationPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        nameField = new JTextField(15);
        classComboBox = new JComboBox<>(PlayerClass.values());
        createButton = new JButton("Create Character");
            createButton.addActionListener(e -> {
                
                String name = nameField.getText();

                PlayerClass playerClass = (PlayerClass) classComboBox.getSelectedItem();

                mainFrame.startNewGame(name, playerClass);
            });


        add(new JLabel("Character Creation"));
        add(new JLabel("Name : "));
        add(nameField);

        add(new JLabel("CLass : "));
        add(classComboBox);

        add(createButton);

    }
}
