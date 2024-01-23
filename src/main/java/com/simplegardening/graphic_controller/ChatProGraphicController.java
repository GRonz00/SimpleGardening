package com.simplegardening.graphic_controller;

import com.simplegardening.SimpleGardeningApplication;
import com.simplegardening.bean.in.SessionBeanIn;
import com.simplegardening.bean.out.ChatBeanOut;
import com.simplegardening.controller.LoginController;
import com.simplegardening.controller.SendMessageController;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.List;

public class ChatProGraphicController {
    private int idSession;
    @FXML
    private FlowPane chatPane;
    private void setIdSession(int idSession){
        this.idSession=idSession;
    }
    public void initialize(int idSession){
        setIdSession(idSession);
        SendMessageController sendMessageController = new SendMessageController();
        try {
            chatPane.getChildren().clear();
            ChatBeanOut chatBean = sendMessageController.getChat(new SessionBeanIn(idSession));
            List<String> chatList = chatBean.getUsername();
            for (String user : chatList){
                FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("chatList_pane.fxml"));
                AnchorPane pane = fxmlLoader.load();
                ((Label) pane.lookup("#userLabel")).setText(user);
                ((Button) pane.lookup("#goButton")).setOnAction((ActionEvent event) -> {
                    try {
                        FXMLLoader fxmlLoader1 = new FXMLLoader(SimpleGardeningApplication.class.getResource("messagesPro.fxml"));
                        Parent node = fxmlLoader1.load();
                        ((Node) event.getSource()).getScene().setRoot(node);
                        MessagesProGraphicController graphicController = fxmlLoader1.getController();
                        graphicController.initialize(idSession,user);
                    } catch (IOException e) {
                        ExceptionHandler.handleException("Could not go to next scene", e.getMessage());
                    }
                });
                chatPane.getChildren().add(pane);
            }
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        } catch (IOException e) {
            ExceptionHandler.handleException("Could not go to next scene", e.getMessage());
        }

    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("home_pro.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        HomeProGraphicController graphicController = fxmlLoader.getController();
        graphicController.initialize(idSession);

    }

    public void logout(ActionEvent event) throws IOException {
        try {
            new LoginController().closeSession(idSession);
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        }
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
