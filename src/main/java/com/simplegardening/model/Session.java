package com.simplegardening.model;

import com.simplegardening.dao.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Session {
    private User user;
    private int id;
    private Connection connection;

    public Session(){}
    public Session(int id, User user) throws SQLException {
        setId(id);
        setUser(user);
        setConnection();

    }
    public void closeConnection() throws SQLException {
        connection.close();
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

    public Connection getConnection() {
        return connection;
    }

    public void setConnection() throws SQLException {
        this.connection = DriverManager.getConnection(Database.DB_URL, Database.USER, System.getenv("DATABASE_P"));
    }
}
