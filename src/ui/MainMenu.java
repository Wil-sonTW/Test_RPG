package ui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainMenu {
    
    private VBox root;

    public MainMenu(Runnable onNewGame) {

        root = new VBox(20);

        root.setAlignment(Pos.CENTER);

        Label title = new Label("Test RPG");
        title.setStyle("-fx-font-size: 32px;");

        Button newGame = new Button("New game");
            newGame.setOnAction(e -> onNewGame.run());
        
        Button continueGame = new Button("Continue");
            continueGame.setOnAction(e -> System.out.println("Continue is selected"));

        Button settings = new Button("Open the settings");
            settings.setOnAction(e -> System.out.println("New Game selected"));
        
        Button exit = new Button("Exit");
            exit.setOnAction(e -> System.exit(0));

        root.getChildren().addAll(
            title,
            newGame,
            continueGame,
            settings,
            exit
        );
    }

    public Parent getRoot() {
        return root;
    }
    
}
