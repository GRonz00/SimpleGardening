package com.simplegardening.model;

public class Session {
    private User user;
    private int id;

    public Session(){}
    public Session(int id, User user){
        setId(id);
        setUser(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
