package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        MainMenu menu = new MainMenu();

        Scene scene = new Scene(menu.getRoot(), 1000, 700);

        stage.setTitle("Test RPG");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}