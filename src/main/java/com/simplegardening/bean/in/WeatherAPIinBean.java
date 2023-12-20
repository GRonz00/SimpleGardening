package com.simplegardening.bean.in;

import com.simplegardening.model.Weather;
import com.simplegardening.utils.Daily;

import java.util.ArrayList;
import java.util.List;

public class WeatherAPIinBean {


    private List<Weather> weather_week;



    public List<Weather> getWeather_week() {
        return weather_week;
    }

    public void setWeather_week(Daily daily) {
        List<Weather> weatherList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Weather weather = new Weather(daily.getTemperature_2m_max()[i],daily.getTemperature_2m_min()[i],daily.getPrecipitation_probability_max()[i],daily.getTime()[i]);
            weatherList.add(weather);
        }
        this.weather_week = weatherList;
    }
}
