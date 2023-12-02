package com.example.simplegardening.graphic_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class HomeGraphicController {
    @FXML
    public void goBack(ActionEvent event) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("searchstore.fxml"));
        //((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
    @FXML
    public void logout(ActionEvent event) throws IOException {
       // LoggedInUser.getInstance().setUser(null);
        //FXMLLoader fxmlLoader = new FXMLLoader(ShoppingPointApplication.class.getResource("login.fxml"));
        //((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
