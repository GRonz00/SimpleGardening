package com.simplegardening.graphic_controller;

import com.simplegardening.SimpleGardeningApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class ChatProGraphicController {
    private int idSession;
    @FXML
    private FlowPane chatPane;
    private void setIdSession(int idSession){
        this.idSession=idSession;
    }
    public void initialize(int idSession){
        setIdSession(idSession);
        FlowPane c = chatPane;
        new ChatListGraphicController().setP(c,"messagesPro.fxml",idSession);

    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("home_pro.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        ManageRequestProGraphicController graphicController = fxmlLoader.getController();
        graphicController.initialize(idSession);

    }

    public void logout(ActionEvent event) throws IOException {
        new ChatListGraphicController().logout(event);
    }
}
