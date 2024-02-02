package com.simplegardening.bean.in;

import com.simplegardening.exception.BeanException;
import com.simplegardening.utils.UserType;

public class RegisterInBean {
    private String username;
    private String password;
    private String address;
    private UserType userType;
    private double longitude;
    private double latitude;

    public RegisterInBean(String username, String password, String address, String userType, double longitude, double latitude) throws BeanException {
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

    public void setUsername(String username) throws BeanException {
        if (username.length() < 4) throw new BeanException("username", BeanException.TOO_SHORT_REASON);
        if (username.length() > 16) throw new BeanException("username", BeanException.TOO_LONG_REASON);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws BeanException {
        if (password.length() < 4) throw new BeanException("password", BeanException.TOO_SHORT_REASON);
        if (password.length() > 16) throw new BeanException("password", BeanException.TOO_LONG_REASON);
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws BeanException {
        if (address.length() < 4) throw new BeanException("address", BeanException.TOO_SHORT_REASON);
        this.address = address;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(String type) throws BeanException {
        switch (type) {
            case "Client" -> this.userType = UserType.CLIENT;
            case "Pro" -> this.userType = UserType.PRO;
            default -> throw new BeanException("Unexpected value: ", type);
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
