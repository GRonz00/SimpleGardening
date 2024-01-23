package com.simplegardening.bean.out;

import java.util.List;

public class ChatBeanOut {
    private List<String> username;

    public ChatBeanOut(List<String> chat) {
        this.username= chat;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }
}
