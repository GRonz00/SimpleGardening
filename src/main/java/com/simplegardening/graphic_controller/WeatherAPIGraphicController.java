package com.simplegardening.graphic_controller;

import java.io.BufferedReader;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplegardening.bean.in.WeatherAPIinBean;
import com.simplegardening.bean.out.WeatherAPIOutBean;
import com.simplegardening.bean.in.Daily;
import com.simplegardening.bean.in.ForecastJson;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherAPIGraphicController {
    public WeatherAPIinBean doWeatherForecast(WeatherAPIOutBean weatherAPIoutBean) {
        URL url; // creating a url object
        StringBuilder content = new StringBuilder();
        double lon = weatherAPIoutBean.getLongitude();
        double lat = weatherAPIoutBean.getLatitude();
        String s = "https://api.open-meteo.com/v1/forecast?latitude=" + lon + "&longitude="+lat+"&daily=temperature_2m_max,temperature_2m_min,precipitation_probability_max&timezone=auto";



        {
            try {
              //  url = new URL(s);
                url = new URL(s);
                URLConnection urlConnection = url.openConnection(); // creating a urlconnection object
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                ObjectMapper objectMapper = new ObjectMapper();


                String line;
                while ((line = bufferedReader.readLine()) != null) {// reading from the urlconnection using the bufferedreader
                    content.append(line).append("\n");
                }

                bufferedReader.close();


                ForecastJson son = objectMapper.readValue(content.toString(), ForecastJson.class);
                Daily daily = son.getDaily();
                WeatherAPIinBean weatherAPIinBean = new WeatherAPIinBean();
                weatherAPIinBean.setWeatherWeek(daily);
                return weatherAPIinBean;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
