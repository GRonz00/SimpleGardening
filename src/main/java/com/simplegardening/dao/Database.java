package com.simplegardening.dao;

public class Database {
    private Database() {
        throw new IllegalStateException();
    }

    public static final String USER = "simplegardening";
    public static final String PASS = "password";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/simplegardening";
}
