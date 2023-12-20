package com.simplegardening.bean.out;

import com.simplegardening.model.Weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherClientOutBean {


    private List<String> days = new ArrayList<>();
    private List<Integer> tempMax = new ArrayList<>();
    private List<Integer> tempMin = new ArrayList<>();
    private List<Integer> fall = new ArrayList<>();

    public WeatherClientOutBean(){}


    public List<String> getDays() {
        return days;
    }
    public void setDays(List<String> days){
        this.days=days;
    }

    public List<Integer> getTempMax() {
        return tempMax;
    }

    public void setTempMax(List<Integer> tempMax) {
        this.tempMax = tempMax;
    }

    public List<Integer> getTempMin() {
        return tempMin;
    }

    public void setTempMin(List<Integer> tempMin) {
        this.tempMin = tempMin;
    }

    public List<Integer> getFall() {
        return fall;
    }

    public void setFall(List<Integer> fall) {
        this.fall = fall;
    }


    public void setAll(List<Weather> weatherList) {
        List<String> days = new ArrayList<>();
        List<Integer> tempMax = new ArrayList<>();
        List<Integer> tempMin = new ArrayList<>();
        List<Integer> fall = new ArrayList<>();

        for (Weather w : weatherList){
            days.add(w.getDay());
            tempMax.add(w.getTempMax());
            tempMin.add(w.getTempMin());
            fall.add(w.getFall());
        }
        setDays(days);
        setTempMax(tempMax);
        setTempMin(tempMin);
        setFall(fall);
    }
}
