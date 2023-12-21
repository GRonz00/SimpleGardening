package com.simplegardening.dao;

import com.simplegardening.model.Client;
import com.simplegardening.model.User;
import com.simplegardening.utils.UserType;

public class UserDAO {
    public static User getUserByUsername(String loginUsername) {
        User user = new Client();
        user.setPassword("prova");
        user.setLatitude(40.0);
        user.setLongitude(40.0);
        return user;
    }

    public void registerUser(String username, String password, String address, UserType userType, double longitude, double latitude){
    }
}
