package com.simplegardening.graphic_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplegardening.bean.in.WeatherAPIinBean;
import com.simplegardening.bean.out.WeatherAPIOutBean;
import com.simplegardening.exception.APIException;
import com.simplegardening.utils.Daily;
import com.simplegardening.utils.ForecastJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherAPIGraphicController {
    public WeatherAPIinBean doWeatherForecast(WeatherAPIOutBean weatherAPIoutBean) throws APIException {
        URL url; // creating a url object
        StringBuilder content = new StringBuilder();
        double lon = weatherAPIoutBean.getLongitude();
        double lat = weatherAPIoutBean.getLatitude();
        String s = "https://api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude="+lon+"&daily=temperature_2m_max,temperature_2m_min,precipitation_probability_max&timezone=auto";
            try {
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
                throw new APIException("weather not received");
            }
        }
    }



