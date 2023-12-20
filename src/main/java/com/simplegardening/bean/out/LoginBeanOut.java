package com.simplegardening.bean.out;

import com.simplegardening.model.Client;
import com.simplegardening.model.Pro;
import com.simplegardening.model.Session;

public class LoginBeanOut {
    private int idSession;
    private int typeUser;

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(Session session) {
        this.idSession = session.getId();
    }

    public int getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(Session session) {
        if(session.getUser() instanceof Client)this.typeUser=1;
        if(session.getUser() instanceof Pro)this.typeUser=2;
    }
}
