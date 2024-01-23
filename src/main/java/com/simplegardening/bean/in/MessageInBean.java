package com.simplegardening.bean.in;

public class MessageInBean {
    private String message;
    private int idSession;
    private String receiver;

    public MessageInBean(int idSession, String message, String receiver){
        setIdSession(idSession);
        setMessage(message);
        setReceiver(receiver);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
