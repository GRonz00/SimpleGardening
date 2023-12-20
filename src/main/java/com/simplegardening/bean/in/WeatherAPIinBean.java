package com.simplegardening.bean.in;

import com.simplegardening.model.Weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherAPIinBean {


    private List<Weather> weatherWeek;



    public List<Weather> getWeatherWeek() {
        return weatherWeek;
    }

    public void setWeatherWeek(Daily daily) {
        List<Weather> weatherList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Weather weather = new Weather(daily.getTemperature2MMax()[i],daily.getTemperature2MMin()[i],daily.getPrecipitationProbabilityMax()[i],daily.getTime()[i]);
            weatherList.add(weather);
        }
        this.weatherWeek = weatherList;
    }
}
