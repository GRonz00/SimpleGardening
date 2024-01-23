package com.simplegardening.dao.chat_dao;

import com.simplegardening.model.Session;
import com.simplegardening.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatDAOJDBC implements ChatDAO{




    public void saveMessage(String message, Session session, User receiver) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = session.getConnection();
        try {

            String sql = String.format("INSERT INTO Chat ( User_username, User_username1, text) VALUES ('%s', '%s', '%s')",session.getUser().getUsername(),receiver.getUsername(),message);
            // Execute query
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();


        }catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            if (statement != null) statement.close();
        }
    }

    @Override
    public List<String> getMessages(Session session, User receiver) throws SQLException {
        List<String> messages = new ArrayList<>();
        Connection connection = session.getConnection();
        try ( Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            String query = String.format("SELECT * FROM Chat WHERE (User_username = '%s' or User_username1 = '%s') and (User_username = '%s' or User_username1 = '%s') ", session.getUser().getUsername(),session.getUser().getUsername(),receiver.getUsername(),receiver.getUsername());
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                messages.add(rs.getString("text"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return messages;
    }


}
