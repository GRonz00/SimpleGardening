package com.simplegardening.bean.out;

public class ConvertAddressOutBean {
    private double longitude;
    private double latitude;

    public ConvertAddressOutBean(double longitude,double latitude){
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
