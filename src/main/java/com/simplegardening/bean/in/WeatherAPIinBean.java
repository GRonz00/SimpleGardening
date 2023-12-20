package com.simplegardening.bean.in;

import com.simplegardening.model.Weather;
import com.simplegardening.utils.Daily;

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
            Weather weather = new Weather(daily.getTemperature_2m_max()[i],daily.getTemperature_2m_min()[i],daily.getPrecipitation_probability_max()[i],daily.getTime()[i]);
            weatherList.add(weather);
        }
        this.weatherWeek = weatherList;
    }
}
