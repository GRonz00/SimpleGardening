package com.simplegardening.model;

import com.simplegardening.exception.SessionException;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private static SessionManager instance = null;

    private final List<Session> sessionList = new ArrayList<>();

    protected SessionManager(){}

    public static synchronized SessionManager getInstance(){
        if(SessionManager.instance==null)SessionManager.instance= new SessionManager();
        return instance;
    }

    public Session createNewSession(User user){
        int id = 0;
        boolean b = false;
        for (int i=0;i<sessionList.size();i++){
            for (Session session : sessionList) {
                if (id == session.getId()) {
                    id++;
                    b = true;
                    break;
                }
            }
            if(!b)break;
         }
        Session newSession = new Session(id, user);
        sessionList.add(newSession);
        return newSession;
    }

    public void validSession(int idSession) throws SessionException {
        for(Session session: sessionList){
            if(session.getId()==idSession)return;
        }
        throw new SessionException("Session expired");
    }

    public Session getSession(int idSession){
        Session s = new Session();
        for(Session session: sessionList){
            if(session.getId()==idSession)return session;
        }
        return s;
    }



}
