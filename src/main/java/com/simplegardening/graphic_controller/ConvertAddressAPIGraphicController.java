package com.simplegardening.graphic_controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplegardening.bean.in.AddressInAPIBean;
import com.simplegardening.bean.out.AddressOutAPIBean;
import com.simplegardening.exception.APIException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ConvertAddressAPIGraphicController {
    public AddressInAPIBean convertAddress(AddressOutAPIBean addressOutAPIBean) throws APIException {
        String s = "https://geocode.maps.co/search?q= "+ addressOutAPIBean.getStreet()+" + "+ addressOutAPIBean.getpC()+" + "+ addressOutAPIBean.getCity()+" + "+ addressOutAPIBean.getNation()+"&api_key="+System.getenv("API_KEY");
        try {
            URL url = new URL(s);
            URLConnection urlConnection = url.openConnection();

        // wrapping the urlconnection in a bufferedreader
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();


        String line;
        // reading from the urlconnection using the bufferedreader
        StringBuilder content = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null)
        {
            content.append(line).append("\n");
        }
        content.deleteCharAt(0);

        bufferedReader.close();
        //read JSON like DOM Parser
        JsonNode rootNode = objectMapper.readTree(content.toString());
        JsonNode lat = rootNode.path("lat");
        JsonNode lon = rootNode.path("lon");
        return new AddressInAPIBean(lat.asDouble(), lon.asDouble());
        } catch (IOException e) {
            throw new APIException("address conversion failed");
        }


    }

}
