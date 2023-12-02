package com.example.simplegardening;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SimpleGardeningApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("plant.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 280);
        stage.setTitle("Simple Gardening");
        //stage.setMinWidth(800);
        //stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}