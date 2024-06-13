package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("css/application.css").toExternalForm();
            Font.loadFont(getClass().getResource("fonts/Montserrat-VariableFont_wght.ttf").toExternalForm(), 24);
            scene.getStylesheets().add(css);
            stage.setFullScreen(true);
            stage.setScene(scene);
            stage.show();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}