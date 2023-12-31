package com.simplegardening.controller;

import com.simplegardening.bean.in.WeatherAPIinBean;
import com.simplegardening.bean.out.WeatherAPIOutBean;
import com.simplegardening.bean.in.WeatherClientInBean;
import com.simplegardening.bean.out.WeatherClientOutBean;
import com.simplegardening.exception.APIException;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.exception.SessionException;
import com.simplegardening.graphic_controller.WeatherAPIGraphicController;
import com.simplegardening.model.Session;
import com.simplegardening.model.SessionManager;
import com.simplegardening.model.Weather;

import java.util.List;

public class WeatherForecastController {

    public WeatherClientOutBean weeklyWeatherForecast(WeatherClientInBean weatherClientInBean) throws ControllerException {
        int idSession = weatherClientInBean.getIdSession();
        WeatherAPIGraphicController weatherAPIGraphicController = new WeatherAPIGraphicController();
        try{
            WeatherAPIOutBean weatherAPIoutBean = new WeatherAPIOutBean();
        SessionManager.getInstance().validSession(idSession);
            Session session = SessionManager.getInstance().getSession(idSession);
            weatherAPIoutBean.setLongitude(session.getUser().getLongitude());
            weatherAPIoutBean.setLatitude(session.getUser().getLatitude());
            WeatherAPIinBean weatherAPIinBean = weatherAPIGraphicController.doWeatherForecast(weatherAPIoutBean);
            List<Weather> weatherList = weatherAPIinBean.getWeatherWeek();
            WeatherClientOutBean weatherClientOutBean = new WeatherClientOutBean();
            weatherClientOutBean.setAll(weatherList);
            return weatherClientOutBean;
        } catch (APIException | SessionException e) {
            throw new ControllerException(e);
        }


    }
}
