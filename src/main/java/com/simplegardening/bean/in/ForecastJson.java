package com.simplegardening.bean.in;

import com.simplegardening.bean.in.Daily;

import java.util.Map;

public class ForecastJson {
    private float latitude;
    private  float longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private float elevation;
    private Map<String, String> daily_units;
    private Daily daily;


    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public double getGenerationtime_ms() {
        return generationtime_ms;
    }

    public void setGenerationtime_ms(double generationtime_ms) {
        this.generationtime_ms = generationtime_ms;
    }

    public int getUtc_offset_seconds() {
        return utc_offset_seconds;
    }

    public void setUtc_offset_seconds(int utc_offset_seconds) {
        this.utc_offset_seconds = utc_offset_seconds;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone_abbreviation() {
        return timezone_abbreviation;
    }

    public void setTimezone_abbreviation(String timezone_abbreviation) {
        this.timezone_abbreviation = timezone_abbreviation;
    }

    public float getElevation() {
        return elevation;
    }

    public void setElevation(float elevation) {
        this.elevation = elevation;
    }

    public Map<String, String> getDaily_units() {
        return daily_units;
    }

    public void setDaily_units(Map<String, String> daily_units) {
        this.daily_units = daily_units;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }
}
