package com.simplegardening.model;

import com.simplegardening.utils.TypesOfPersistenceLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Session {
    private User user;
    private int id;
    private Connection connection;
    private TypesOfPersistenceLayer type;

    public Session(){}
    public Session(int id, User user, TypesOfPersistenceLayer type) throws SQLException {
        setId(id);
        setUser(user);
        setConnection();
        setType(type);

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
        this.connection = DriverManager.getConnection(System.getenv("DB_URL"), System.getenv("USER"),System.getenv("DATABASE_P") );
    }

    public TypesOfPersistenceLayer getType() {
        return type;
    }

    public void setType(TypesOfPersistenceLayer type) {
        this.type = type;
    }
}
