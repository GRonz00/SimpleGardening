package com.simplegardening.controller;

import com.simplegardening.FactoryChatDAO;
import com.simplegardening.bean.in.ChatInBean;
import com.simplegardening.bean.in.MessageInBean;
import com.simplegardening.bean.in.SessionBeanIn;
import com.simplegardening.bean.out.ChatBeanOut;
import com.simplegardening.bean.out.MessagesOutBean;
import com.simplegardening.dao.RequestDAO;
import com.simplegardening.dao.UserDAO;
import com.simplegardening.dao.chatDAO.ChatDAO;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.exception.DatabaseException;
import com.simplegardening.exception.SessionException;
import com.simplegardening.model.*;
import com.simplegardening.utils.RequestState;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SendMessageController {
    public ChatBeanOut getChat(SessionBeanIn id) throws ControllerException {
        return new ChatBeanOut(getChat(id.getIdSession()));
    }
    public void sendMessage(MessageInBean message) throws ControllerException {
        try {
            Session session = SessionManager.getInstance().getSession(message.getIdSession());
            if(!getChat(message.getIdSession()).contains(message.getReceiver()))throw new ControllerException("Receiver not in list");
            ChatDAO chatDAO = new FactoryChatDAO().createChatDAO(session.getType());
            String mes = session.getUser().getUsername()+": "+message.getMessage();
            chatDAO.saveMessage(mes, session, new UserDAO().getUserByUsername(message.getReceiver()));
        } catch (SessionException e) {
            throw new ControllerException(e);
        } catch (SQLException e) {
            throw new ControllerException(e.getMessage());
        } catch (DatabaseException e) {
            throw new ControllerException("Database",e);
        }

    }

    public MessagesOutBean getMessages(ChatInBean chat) throws ControllerException {
        try{
            Session session = SessionManager.getInstance().getSession(chat.getIdSession());
            ChatDAO chatDAO = new FactoryChatDAO().createChatDAO(session.getType());
            List<String> messages = chatDAO.getMessages(session,new UserDAO().getUserByUsername(chat.getUsername()));
            return new MessagesOutBean(messages);
        } catch (SessionException e) {
            throw new ControllerException(e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database",e);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }
    private List<String> getChat(int idSession) throws ControllerException {
        try {
            List<String> chat = new ArrayList<>();
            Session session = SessionManager.getInstance().getSession(idSession);
            if (session.getUser() instanceof Client) {
                List<Request> requests = new RequestDAO().getRequestFromUser(session.getUser(),session);
                for (Request r:requests){
                    if(r.getState().compareTo(RequestState.ACCEPTED)==0&&!chat.contains(r.getPro().getUsername()))chat.add(r.getPro().getUsername());
                }
            }
            if (session.getUser() instanceof Pro) {
                List<Request> requests = new RequestDAO().getRequestFromUser(session.getUser(),session);
                for (Request r:requests){
                    if(r.getState().compareTo(RequestState.ACCEPTED)==0&&!chat.contains(r.getClient().getUsername()))chat.add(r.getClient().getUsername());
                }
            }
            return chat;
        } catch (SessionException e) {
            throw new ControllerException(e);
        } catch (SQLException e) {
            throw new ControllerException(e.getMessage());
        } catch (DatabaseException e) {
            throw new ControllerException("Database",e);
        }
    }
    }


