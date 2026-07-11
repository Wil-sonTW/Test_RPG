package ui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CharacterCreation {
    
    private VBox root;

    public CharacterCreation(Runnable onBack) {

        root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        Label title = new Label("Character Creation");
            title.setStyle("-fx-font-size: 32px");

        Button back = new Button("Back");
            back.setOnAction(e -> onBack.run());

        root.getChildren().addAll(title, back);
    }

    public Parent getRoot() {
        return root;
    }
}
