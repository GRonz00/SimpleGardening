package com.simplegardening.bean.in;

public class Daily {
    private String[] time;
    private Integer[] temperature2MMax;
    private Integer[] temperature2MMin;
    private int[] precipitationProbabilityMax;

    public String[] getTime() {
        return time;
    }

    public Integer[] getTemperature2MMax() {
        return temperature2MMax;
    }

    public Integer[] getTemperature2MMin() {
        return temperature2MMin;
    }

    public int[] getPrecipitationProbabilityMax() {
        return precipitationProbabilityMax;
    }

}
