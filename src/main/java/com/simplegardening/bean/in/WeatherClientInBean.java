package com.simplegardening.bean.in;

public class WeatherClientInBean {
    private int idSession;

    public WeatherClientInBean(int idSession) {
        setIdSession(idSession);
    }


    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }
}
