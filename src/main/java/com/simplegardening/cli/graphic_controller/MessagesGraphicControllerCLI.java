package com.simplegardening.cli.graphic_controller;

import com.simplegardening.bean.in.ChatInBean;
import com.simplegardening.bean.in.MessageInBean;
import com.simplegardening.bean.in.SessionBeanIn;
import com.simplegardening.bean.out.ChatBeanOut;
import com.simplegardening.bean.out.MessagesOutBean;
import com.simplegardening.cli.view.MessageViewCLI;
import com.simplegardening.controller.SendMessageController;
import com.simplegardening.exception.ControllerException;

import java.io.IOException;
import java.util.List;

public class MessagesGraphicControllerCLI {
    private int idSession;
    private MessageViewCLI messageViewCLI;
    public void initialize(int idSession){
        this.idSession=idSession;
        this.messageViewCLI = new MessageViewCLI();
        try {
            int choice = messageViewCLI.getChoice();
            if(choice==1){
                sendMessage();
            }
            if(choice==2){seeListChat();}
            if(choice==3){seeChat();}
        } catch (IOException | ControllerException e) {
            System.out.println("[ERR] " + e.getMessage());
            System.out.println("Please retry.");
        }finally {
            HomeGraphicControllerCLI homeGraphicControllerCLI = new HomeGraphicControllerCLI();
            homeGraphicControllerCLI.initialize(idSession);
        }
    }

    private void seeChat() throws IOException, ControllerException {
        SendMessageController sendMessageController = new SendMessageController();
        ChatInBean chat = new ChatInBean(idSession, messageViewCLI.getChat());
        MessagesOutBean messages = sendMessageController.getMessages(chat);
        messageViewCLI.viewChat(messages.getMessages());
    }

    private void seeListChat() throws ControllerException {
        SendMessageController sendMessageController = new SendMessageController();
        SessionBeanIn sessionBeanIn = new SessionBeanIn(idSession);
        ChatBeanOut chatBeanOut = sendMessageController.getChat(sessionBeanIn);
        messageViewCLI.viewChat(chatBeanOut.getUsername());
    }

    private void sendMessage() throws IOException, ControllerException {
        SendMessageController sendMessageController = new SendMessageController();
        List<String> mes = messageViewCLI.sendMessage();
        MessageInBean message = new MessageInBean(idSession,mes.get(1),mes.get(0));
        sendMessageController.sendMessage(message);
    }
}
