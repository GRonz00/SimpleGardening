package com.simplegardening.dao.chat_dao;

import com.simplegardening.model.Session;
import com.simplegardening.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ChatDAO {
     void saveMessage(String message, Session session, User receiver) throws SQLException, IOException;
     List<String> getMessages(Session session, User receiver) throws IOException, SQLException;


}
