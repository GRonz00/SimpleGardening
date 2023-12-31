package com.simplegardening.graphic_controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplegardening.bean.in.ConvertAddressInAPIBean;
import com.simplegardening.bean.out.ConvertAddressOutAPIBean;
import com.simplegardening.exception.APIException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ConvertAddressAPIGraphicController {
    public ConvertAddressInAPIBean convertAddress(ConvertAddressOutAPIBean convertAddressOutAPIBean) throws APIException {
        String s = "https://geocode.maps.co/search?q= "+convertAddressOutAPIBean.getStreet()+" + "+convertAddressOutAPIBean.getpC()+" + "+convertAddressOutAPIBean.getCity()+" + "+convertAddressOutAPIBean.getNation()+"&api_key=658c340c9cff3538808164tbce9d741";
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
        return new ConvertAddressInAPIBean(lat.asDouble(), lon.asDouble());
        } catch (IOException e) {
            throw new APIException("address conversion failed");
        }


    }

}
