package com.simplegardening.bean.in;

import com.simplegardening.utils.UserType;

public class RegisterInBean {
    private String username;
    private String password;
    private String address;
    private UserType userType;
    private double longitude;
    private double latitude;

    public RegisterInBean(String username, String password, String address, String userType, double longitude, double latitude){
        setAddress(address);
        setPassword(password);
        setUsername(username);
        setUserType(userType);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(String type) {
        switch (type) {
            case "Client" -> this.userType = UserType.CLIENT;
            case "Pro" -> this.userType = UserType.PRO;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
