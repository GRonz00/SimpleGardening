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

public class ChatListGraphicController {
    @FXML
    private FlowPane chatPane;
    private int idSession;
    private void setIdSession(int idSession){
        this.idSession=idSession;
    }
    public void initialize(int idSession){
        setIdSession(idSession);
        FlowPane c = chatPane;
        setP(c,"messages.fxml",idSession);


    }


    @FXML
    public void setP(FlowPane c,String goPage, int id){
        try {
            SendMessageController sendMessageController = new SendMessageController();
            c.getChildren().clear();
            ChatBeanOut chatBean = sendMessageController.getChat(new SessionBeanIn(id));
            List<String> chatList = chatBean.getUsername();
            for (String user : chatList){
                FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("chatList_pane.fxml"));
                AnchorPane pane = fxmlLoader.load();
                ((Label) pane.lookup("#userLabel")).setText(user);
                ((Button) pane.lookup("#goButton")).setOnAction((ActionEvent event) -> {
                    try {
                        FXMLLoader fxmlLoader1 = new FXMLLoader(SimpleGardeningApplication.class.getResource(goPage));
                        Parent node = fxmlLoader1.load();
                        ((Node) event.getSource()).getScene().setRoot(node);
                        if(goPage.equals("messagesPro.fxml")){
                            MessagesProGraphicController graphicController = fxmlLoader1.getController();
                            graphicController.initialize(id,user);
                        }
                        else {MessagesGraphicController graphicController = fxmlLoader1.getController();
                        graphicController.initialize(id,user);}


                    } catch (IOException e) {
                        ExceptionHandler.handleException("Could not go to next scene", e.getMessage());
                    }
                });
                c.getChildren().add(pane);

            }

        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        } catch (IOException e) {
            ExceptionHandler.handleException("Could not go to next scene", e.getMessage());
        }
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("home.fxml"));
            ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
            HomeGraphicController homeGraphicController = fxmlLoader.getController();
            homeGraphicController.initialize(idSession);

    }

    public void logout(ActionEvent event) throws IOException {
        try {
            new LoginController().closeSession(new SessionBeanIn(idSession));
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        }
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }


}
