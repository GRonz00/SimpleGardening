package com.simplegardening.graphic_controller;

import com.simplegardening.SimpleGardeningApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class HomeGraphicController {

    private int idSession;

    public void setIdSession(int idSession){
        this.idSession = idSession;
    }
    @FXML
    public void logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
    @FXML
    public void weather(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("weather.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
        WeatherGraphicController weatherGraphicController = fxmlLoader.getController();
        weatherGraphicController.setIdSession(idSession);
    }
}
