package com.simplegardening.model;

public class Weather {
    private Integer temp_max;
    private Integer temp_min;
    private Integer precipitation_perc;

    private String day;

    public Weather() {
    }
    public Weather(Integer temp_max, Integer temp_min, int precipitation_perc, String day) {
        this.precipitation_perc = precipitation_perc;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.day= day;

    }

    public Integer getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Integer temp_max) {
        this.temp_max = temp_max;
    }

    public Integer getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Integer temp_min) {
        this.temp_min = temp_min;
    }

    public Integer getPrecipitation_perc() {
        return precipitation_perc;
    }

    public void setPrecipitation_perc(Integer precipitation_perc) {
        this.precipitation_perc = precipitation_perc;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
