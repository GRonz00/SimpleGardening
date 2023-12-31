package com.simplegardening.model;

public class Pro extends User{
    public Pro(String loginUsername, String password, double longitude, double latitude, String address) {
        setLatitude(latitude);
        setLongitude(longitude);
        setPassword(password);
        setUsername(loginUsername);
        setAddress(address);
    }
}
