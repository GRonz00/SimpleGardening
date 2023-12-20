package com.simplegardening.dao;

import com.simplegardening.model.Client;
import com.simplegardening.model.User;

public class UserDAO {
    public static User getUserByUsername(String loginUsername) {
        User user = new Client();
        user.setPassword("prova");
        user.setLatitude(40.0);
        user.setLongitude(40.0);
        return user;
    }
}
