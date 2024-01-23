package com.simplegardening.graphic_controller;

import com.simplegardening.SimpleGardeningApplication;
import com.simplegardening.bean.in.ChatInBean;
import com.simplegardening.bean.in.MessageInBean;
import com.simplegardening.bean.out.MessagesOutBean;
import com.simplegardening.controller.LoginController;
import com.simplegardening.controller.SendMessageController;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.List;

public class MessagesGraphicController {
    private int idSession;
    private String receiver;
    @FXML
    private FlowPane messagePane;
    @FXML
    private TextArea textField;


    private void setIdSession(int idSession){
        this.idSession=idSession;
    }
    public void initialize(int idSession, String receiver){
        setIdSession(idSession);
        setReceiver(receiver);
        updateMessagesView();
    }

    public void updateMessagesView(){
        try {
            SendMessageController sendMessageController = new SendMessageController();
            messagePane.getChildren().clear();
            MessagesOutBean messagesOutBean = sendMessageController.getMessages(new ChatInBean(idSession,receiver));
            List<String> messages = messagesOutBean.getMessages();
            for (String message : messages){
                messagePane.setAlignment(Pos.CENTER);
                messagePane.getChildren().add(setMessage(message));
            }
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        }
    }

    public TextField setMessage (String message){
        TextField mes = new TextField();
        mes.setAlignment(Pos.CENTER_LEFT);
        mes.setPrefHeight(48);
        mes.setPrefWidth(600);
        mes.setText(message);
        return mes;
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

    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("chatList.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
        ChatListGraphicController graphicController = fxmlLoader.getController();
        graphicController.initialize(idSession);
    }

    public void sendMessage() {
        try{
            SendMessageController sendMessageController = new SendMessageController();
            MessageInBean message = new MessageInBean(idSession,textField.getText(),receiver);
            sendMessageController.sendMessage(message);
            textField.clear();
            updateMessagesView();
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        }
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
