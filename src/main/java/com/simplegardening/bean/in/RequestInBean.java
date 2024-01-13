package com.simplegardening.bean.in;

import com.simplegardening.exception.BeanException;

import java.time.LocalDate;

public class RequestInBean {
    private String pro;
    private float price;
    private String plant;

    private boolean pickup;
    private String client;
    private LocalDate start;
    private LocalDate end;
    private int idRequestForm;

    private int idSession;

    public RequestInBean(String pro, String price, String plant, boolean pickup, String client, LocalDate start, LocalDate end, int idRequestForm, int idSession) throws BeanException {
        setClient(client);
        setEnd(end,start);
        setPlant(plant);
        setPickup(pickup);
        setPrice(price);
        setPro(pro);
        setStart(start);
        setIdRequestForm(idRequestForm);
        setIdSession(idSession);
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end,LocalDate start) throws BeanException {

        if(end.isBefore(start))throw new BeanException("Start","Is before of today");
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) throws BeanException {

        if(start.isBefore(LocalDate.now()))throw new BeanException("Start","Is before of today");
        this.start = start;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public boolean isPickup() {
        return pickup;
    }

    public void setPickup(boolean pickup) {
        this.pickup = pickup;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = Float.parseFloat(price);
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public int getIdRequestForm() {
        return idRequestForm;
    }

    public void setIdRequestForm(int idRequestForm) {
        this.idRequestForm = idRequestForm;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }
}
