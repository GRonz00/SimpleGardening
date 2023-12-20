package com.simplegardening.bean.out;

import com.simplegardening.model.Weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherClientOutBean {


    private List<String> days = new ArrayList<>();
    private List<Integer> temp_max = new ArrayList<>();
    private List<Integer> temp_min = new ArrayList<>();
    private List<Integer> prec = new ArrayList<>();

    public WeatherClientOutBean(){}


    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public List<Integer> getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(List<Integer> temp_max) {
        this.temp_max = temp_max;
    }

    public List<Integer> getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(List<Integer> temp_min) {
        this.temp_min = temp_min;
    }

    public List<Integer> getPrec() {
        return prec;
    }

    public void setPrec(List<Integer> prec) {
        this.prec = prec;
    }

    public void setAll(List<Weather> weatherList) {
        for (Weather w : weatherList){
            this.days.add(w.getDay());
            this.temp_max.add(w.getTemp_max());
            this.temp_min.add(w.getTemp_min());
            this.prec.add(w.getPrecipitation_perc());
        }
    }
}
