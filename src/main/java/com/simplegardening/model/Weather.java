package com.simplegardening.model;

public class Weather {
    private Integer tempMax;
    private Integer tempMin;
    private Integer fall;

    private String day;

    public Weather() {
    }
    public Weather(Integer tempMax, Integer tempMin, int fall, String day) {
        setFall(fall);
        setTempMax(tempMax);
        setTempMin(tempMin);
        setDay(day);

    }

    public Integer getTempMax() {
        return tempMax;
    }

    public void setTempMax(Integer tempMax) {
        this.tempMax = tempMax;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
    }

    public Integer getFall() {
        return fall;
    }

    public void setFall(Integer fall) {
        this.fall = fall;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
