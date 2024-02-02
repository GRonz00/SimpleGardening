package com.simplegardening.cli.view;

public class WeatherViewCLI {
    public void viewWeather(String date, String tempMax, String tempMin, String pre){
        System.out.println(date+" temp max:"+tempMax+" temp min:"+tempMin+" pre"+pre+"%");
    }
}
