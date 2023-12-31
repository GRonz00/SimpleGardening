package com.simplegardening.model;

public class Client extends User{
    public Client(String username, String password,  double longitude, double latitude, String address){
        this.setUsername(username);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setPassword(password);
        this.setAddress(address);
    }
}
