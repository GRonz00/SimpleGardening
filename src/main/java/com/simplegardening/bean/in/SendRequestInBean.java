package com.simplegardening.bean.in;

import com.simplegardening.exception.BeanException;

import java.time.LocalDate;

public class SendRequestInBean {
    private String plant;
    private int idRequestForm;
    private int idSession;
    private boolean pickup;
    private LocalDate start;
    private LocalDate end;

    public SendRequestInBean(String plant, int idSession,int idRequestForm, boolean pickup, LocalDate start, LocalDate end) throws BeanException {
        setPlant(plant);
        setIdSession(idSession);
        setPickup(pickup);
        setStart(start);
        setEnd(end);
        setIdRequestForm(idRequestForm);
    }


    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
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

    public boolean isPickup() {
        return pickup;
    }

    public void setPickup(boolean pickup) {
        this.pickup = pickup;
    }

    public LocalDate getStart() {
        return start;
    }



    public LocalDate getEnd() {

        return end;
    }

    public void setStart(LocalDate start) throws BeanException {
        if(start.isBefore(LocalDate.now()))throw new BeanException("Start","Is before of today");
        this.start = start;
    }

    public void setEnd(LocalDate end) throws BeanException {
        if(end.isBefore(start))throw new BeanException("Start","Is before of today");
        this.end = end;
    }
}
