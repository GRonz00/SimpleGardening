package com.simplegardening.bean.in;

public class ChatInBean {
    private String username;
    private int idSession;
    public ChatInBean(int idSession,String username){
        setIdSession(idSession);
        setUsername(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }
}
