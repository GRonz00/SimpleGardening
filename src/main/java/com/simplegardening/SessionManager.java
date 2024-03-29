package com.simplegardening;

import com.simplegardening.exception.SessionException;
import com.simplegardening.model.Session;
import com.simplegardening.model.User;
import com.simplegardening.utils.TypesOfPersistenceLayer;

import java.sql.SQLException;
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

    public Session createNewSession(User user, TypesOfPersistenceLayer type) throws SQLException {
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
        Session newSession = new Session(id, user,type);
        sessionList.add(newSession);
        return newSession;
    }

    public void validSession(int idSession) throws SessionException {
        for(Session session: sessionList){
            if(session.getId()==idSession)return;
        }
        throw new SessionException("Session expired");
    }

    public Session getSession(int idSession) throws SessionException {
        validSession(idSession);
        Session s = new Session();
        for(Session session: sessionList){
            if(session.getId()==idSession)return session;
        }
        return s;
    }

    public void closeSession(int idSession) throws SQLException, SessionException {
        getSession(idSession).closeConnection();
        sessionList.removeIf(session -> session.getId() == idSession);
    }



}
