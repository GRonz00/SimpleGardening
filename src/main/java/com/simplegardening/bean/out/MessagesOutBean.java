package com.simplegardening.bean.out;

import java.util.List;

public class MessagesOutBean {
    private List<String> messages;

    public MessagesOutBean(List<String> messages) {
        setMessages(messages);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
