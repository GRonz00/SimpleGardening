package com.simplegardening.bean.out;

import com.simplegardening.model.Plant;
import com.simplegardening.model.Request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestOutBean {

    private List<String> pro;
    private List<String> addressPro;

    private List<String> price;
    private List<PlantOutBean> plant;

    private List<Boolean> pickup;
    private List<String> client;
    private List<String> addressClient;
    private List<LocalDate> start;
    private List<LocalDate> end;
    private List<String> state;
    private List<Integer> idRequestForm;
    public RequestOutBean(List<Request> requests) {
        List<String> pro= new ArrayList<>();
        List<String> ad= new ArrayList<>();
        List<String> price= new ArrayList<>();
        List<String> client = new ArrayList<>();
        List<Boolean> pickup = new ArrayList<>();
        List<LocalDate> start = new ArrayList<>();
        List<LocalDate> end = new ArrayList<>();
        List<Plant> plant = new ArrayList<>();
        List<String> adc = new ArrayList<>();
        List<String> sta = new ArrayList<>();
        List<Integer> rf = new ArrayList<>();
        for (Request request : requests){
            pro.add(request.getPro().getUsername());
            ad.add(request.getPro().getAddress());
            price.add(String.valueOf(request.getPrice()));
            client.add(request.getClient().getUsername());
            pickup.add(request.isPickup());
            start.add(request.getStart());
            end.add(request.getEnd());
            plant.add(request.getPlant());
            adc.add(request.getClient().getAddress());
            sta.add(request.getState().toString());
            rf.add(request.getRequestForm().getIdRequestForm());


        }
        setAddressPro(ad);
        setPrice(price);
        setPro(pro);
        setStart(start);
        setEnd(end);
        setPickup(pickup);
        setPlant(plant);
        setAddressClient(adc);
        setClient(client);
        setState(sta);
        setIdRequestForm(rf);
    }


    public List<String> getPro() {
        return pro;
    }

    public void setPro(List<String> pro) {
        this.pro = pro;
    }

    public List<String> getAddressPro() {
        return addressPro;
    }

    public void setAddressPro(List<String> addressPro) {
        this.addressPro = addressPro;
    }

    public List<String> getPrice() {
        return price;
    }

    public void setPrice(List<String> price) {
        this.price = price;
    }

    public List<PlantOutBean> getPlant() {
        return plant;
    }

    public void setPlant(List<Plant> plant) {
        List<PlantOutBean> plant1 = new ArrayList<>();
        for(Plant p : plant){
            plant1.add(new PlantOutBean(p));
        }
        this.plant = plant1;
    }

    public List<Boolean> getPickup() {
        return pickup;
    }

    public void setPickup(List<Boolean> pickup) {
        this.pickup = pickup;
    }

    public List<String> getClient() {
        return client;
    }

    public void setClient(List<String> client) {
        this.client = client;
    }

    public List<LocalDate> getStart() {
        return start;
    }

    public void setStart(List<LocalDate> start) {
        this.start = start;
    }

    public List<LocalDate> getEnd() {
        return end;
    }

    public void setEnd(List<LocalDate> end) {
        this.end = end;
    }

    public List<String> getAddressClient() {
        return addressClient;
    }

    public void setAddressClient(List<String> addressClient) {
        this.addressClient = addressClient;
    }

    public List<String> getState() {
        return state;
    }

    public void setState(List<String> state) {
        this.state = state;
    }

    public List<Integer> getIdRequestForm() {
        return idRequestForm;
    }

    public void setIdRequestForm(List<Integer> idRequestForm) {
        this.idRequestForm = idRequestForm;
    }
}
