package com.simplegardening.cli.graphic_controller;

import com.simplegardening.bean.in.SessionBeanIn;
import com.simplegardening.bean.out.WeatherClientOutBean;
import com.simplegardening.cli.view.WeatherViewCLI;
import com.simplegardening.controller.WeatherForecastController;
import com.simplegardening.exception.ControllerException;

public class WeatherGraphicControllerCLI {
    public void initialize(int idSession){
        try {
            WeatherViewCLI weatherViewCLI = new WeatherViewCLI();
        SessionBeanIn sessionBeanIn = new SessionBeanIn(idSession);
        WeatherForecastController weatherForecastController = new WeatherForecastController();
        WeatherClientOutBean outBean = weatherForecastController.weeklyWeatherForecast(sessionBeanIn);
        for (int i=0;i<outBean.getFall().size();i++){
            weatherViewCLI.viewWeather(outBean.getDays().get(i),outBean.getTempMax().get(i).toString(),outBean.getTempMin().get(i).toString(),outBean.getFall().get(i).toString());
        }
        } catch ( ControllerException e) {
            System.out.println("[ERR] " + e.getMessage());
            System.out.println("Please retry.");
        }finally {
            HomeGraphicControllerCLI homeGraphicControllerCLI = new HomeGraphicControllerCLI();
            homeGraphicControllerCLI.initialize(idSession);
        }
    }
}
