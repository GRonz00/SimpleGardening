package com.simplegardening.bean.in;

public class CoordinatesInAPIBean {
    private double longitude;
    private double latitude;

    public CoordinatesInAPIBean(double latitude, double longitude){
        setLatitude(latitude);
        setLongitude(longitude);
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
