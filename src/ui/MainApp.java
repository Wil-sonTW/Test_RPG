package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MainApp extends Application {


    
    @Override
    public void start(Stage stage) {

        stage.setTitle("Test RPG");
        
        showMainMenu(stage);

        stage.show();
    }

    private void showMainMenu(Stage stage) {
        MainMenu menu = new MainMenu(() -> {
            showCharacterCreation(stage);
        });

        Scene scene = new Scene(menu.getRoot(), 1000, 700);

        stage.setScene(scene);
    }

    private void showCharacterCreation(Stage stage) {
        CharacterCreation characterCreation = new CharacterCreation(() -> {
            showMainMenu(stage);
        });

        Scene scene = new Scene(characterCreation.getRoot(), 1000, 700);

        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}