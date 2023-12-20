package com.simplegardening.controller;

import com.simplegardening.bean.in.WeatherAPIinBean;
import com.simplegardening.bean.out.WeatherAPIoutBean;
import com.simplegardening.bean.in.WeatherClientInBean;
import com.simplegardening.bean.out.WeatherClientOutBean;
import com.simplegardening.graphic_controller.WeatherAPIGraphicController;
import com.simplegardening.model.Session;
import com.simplegardening.model.SessionManager;
import com.simplegardening.model.Weather;

import java.util.List;

public class WeatherForecastController {

    public WeatherClientOutBean weekly_weather_forecast(WeatherClientInBean weatherClientInBean){
        int idSession = weatherClientInBean.getIdSession();
        WeatherAPIGraphicController weatherAPIGraphicController = new WeatherAPIGraphicController();
        WeatherAPIoutBean weatherAPIoutBean = new WeatherAPIoutBean();
        if(SessionManager.getInstance().validSession(idSession))
        {
            Session session = SessionManager.getInstance().getSession(idSession);
            weatherAPIoutBean.setLongitude(session.getUser().getLongitude());
            weatherAPIoutBean.setLatitude(session.getUser().getLatitude());}
        WeatherAPIinBean weatherAPIinBean = weatherAPIGraphicController.do_weather_forecast(weatherAPIoutBean);
        List<Weather> weatherList = weatherAPIinBean.getWeatherWeek();
        WeatherClientOutBean weatherClientOutBean = new WeatherClientOutBean();
        weatherClientOutBean.setAll(weatherList);
        return weatherClientOutBean;


    }
}
