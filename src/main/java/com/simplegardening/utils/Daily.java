package com.simplegardening.utils;

public class Daily {
    private String[] time;
    private Integer[] temperature_2m_max;
    private Integer[] temperature_2m_min;
    private int[] precipitation_probability_max;

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public Integer[] getTemperature_2m_max() {
        return temperature_2m_max;
    }

    public void setTemperature_2m_max(Integer[] temperature_2m_max) {
        this.temperature_2m_max = temperature_2m_max;
    }

    public Integer[] getTemperature_2m_min() {
        return temperature_2m_min;
    }

    public void setTemperature_2m_min(Integer[] temperature_2m_min) {
        this.temperature_2m_min = temperature_2m_min;
    }

    public int[] getPrecipitation_probability_max() {
        return precipitation_probability_max;
    }

    public void setPrecipitation_probability_max(int[] precipitation_probability_max) {
        this.precipitation_probability_max = precipitation_probability_max;
    }
}
