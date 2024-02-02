package com.simplegardening.dao;

import com.simplegardening.model.Session;
import com.simplegardening.utils.ReminderType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReminderDAO {
    public void saveReminder(String plant,ReminderType reminderType, String time, Session session) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = session.getConnection();
        try {

            String sql = String.format("INSERT INTO `Reminder` ( `client`, `plant`, `when`, `type`) VALUES ('%s', '%s', '%s', '%s')",session.getUser().getUsername(),plant,time,reminderType.toString().toLowerCase());
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
}
