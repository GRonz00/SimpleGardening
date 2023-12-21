package com.simplegardening.utils;

public class Daily {
    private String[] time;
    private Integer[] temperature_2m_max;
    private Integer[] temperature_2m_min;
    private int[] precipitation_probability_max;

    public String[] getTime() {
        return time;
    }

    public Integer[] getTemperature_2m_max() {
        return temperature_2m_max;
    }

    public Integer[] getTemperature_2m_min() {
        return temperature_2m_min;
    }

    public int[] getPrecipitation_probability_max() {
        return precipitation_probability_max;
    }

}
