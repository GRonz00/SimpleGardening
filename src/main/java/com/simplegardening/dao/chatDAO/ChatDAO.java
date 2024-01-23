package com.simplegardening.dao.chatDAO;

import com.simplegardening.model.Session;
import com.simplegardening.model.User;

import java.sql.SQLException;
import java.util.List;

public interface ChatDAO {
     void saveMessage(String message, Session session, User receiver)throws SQLException;
     List<String> getMessages(Session session, User receiver) throws Exception;


}
