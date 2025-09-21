package ru.tac.tac;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        Locale locale = new Locale("ru");
        ResourceBundle bundle = ResourceBundle.getBundle("localization/lang", locale);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"), bundle);
        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add(getClass().getResource("styles/darcula.css").toExternalForm());
        stage.setMaximized(true);
        stage.setTitle("TAC");
        stage.setScene(scene);
        stage.show();
    }

    public static void changeAppLanguage(Locale locale) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("localization/lang", locale);
            FXMLLoader loader = new FXMLLoader(App.class.getResource("main.fxml"), bundle);
            BorderPane root = loader.load();

            Stage stage = primaryStage; // primaryStage хранится в поле приложения
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}