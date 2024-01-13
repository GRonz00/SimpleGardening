package com.simplegardening.model;

import com.simplegardening.utils.RequestState;

import java.time.LocalDate;

public class Request {

    private Plant plant;
    private float price;
    private boolean pickup;
    private Pro pro;
    private Client client;
    private LocalDate start;
    private LocalDate end;
    private RequestState state;
    private RequestForm requestForm;


    public Request(RequestForm requestForm, Plant plant, float price, boolean pickup, Pro pro, Client client, LocalDate start, LocalDate end,String state){
        setClient(client);
        setPickup(pickup);
        setPlant(plant);
        setPrice(price);
        setPro(pro);
        setStart(start);
        setEnd(end);
        setRequestForm(requestForm);
        setState(state);

    }
    public Request(RequestForm requestForm, Plant plant, float price, boolean pickup, Pro pro, Client client, LocalDate start, LocalDate end){
        setClient(client);
        setPickup(pickup);
        setPlant(plant);
        setPrice(price);
        setPro(pro);
        setStart(start);
        setEnd(end);
        setRequestForm(requestForm);

    }
    public Request(Plant plant, float price, boolean pickup, Pro pro, Client client, LocalDate start, LocalDate end){
        setClient(client);
        setPickup(pickup);
        setPlant(plant);
        setPrice(price);
        setPro(pro);
        setStart(start);
        setEnd(end);
    }
    public Request(Plant plant, float price, boolean pickup, Pro pro, Client client, LocalDate start, LocalDate end, String state){
        setClient(client);
        setPickup(pickup);
        setPlant(plant);
        setPrice(price);
        setPro(pro);
        setStart(start);
        setEnd(end);
        setState(state);
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isPickup() {
        return pickup;
    }

    public void setPickup(boolean pickup) {
        this.pickup = pickup;
    }

    public Pro getPro() {
        return pro;
    }

    public void setPro(Pro pro) {
        this.pro = pro;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public RequestState getState() {
        return state;
    }

    public void setState(String state) {
        this.state = RequestState.valueOf(state.toUpperCase());
    }

    public RequestForm getRequestForm() {
        return requestForm;
    }

    public void setRequestForm(RequestForm requestForm) {
        this.requestForm = requestForm;
    }
}
