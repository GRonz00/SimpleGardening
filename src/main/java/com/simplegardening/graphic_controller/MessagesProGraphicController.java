package com.simplegardening.graphic_controller;

import com.simplegardening.SimpleGardeningApplication;
import com.simplegardening.bean.in.ChatInBean;
import com.simplegardening.bean.in.MessageInBean;
import com.simplegardening.bean.out.MessagesOutBean;
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

public class MessagesProGraphicController {
    private int idSession;
    private String receiver;
    @FXML
    private FlowPane flowPane;
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

    private void updateMessagesView(){
        try {
            SendMessageController sendMessageController = new SendMessageController();
            flowPane.getChildren().clear();
            flowPane.setAlignment(Pos.CENTER);
            MessagesOutBean messagesOutBean = sendMessageController.getMessages(new ChatInBean(idSession,receiver));
            List<String> messages = messagesOutBean.getMessages();
            for (String message : messages){
                TextField mes = new MessagesGraphicController().setMessage(message);
                flowPane.getChildren().add(mes);
            }
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        }
    }



    public void logout(ActionEvent event) throws IOException {
        new MessagesGraphicController().logout(event);
    }

    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("chatPro.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
        ChatProGraphicController graphicController = fxmlLoader.getController();
        graphicController.initialize(idSession);
    }

    public void sendMessage() {
        try{
            String t = textField.getText();
            MessageInBean message = new MessageInBean(idSession,t,receiver);
            new SendMessageController().sendMessage(message);
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
