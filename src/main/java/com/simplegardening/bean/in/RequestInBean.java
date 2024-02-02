package com.simplegardening.bean.in;

import com.simplegardening.exception.BeanException;

import java.time.LocalDate;

public class RequestInBean {
    private String plant;

    private String client;
    private LocalDate start;
    private LocalDate end;
    private int idRequestForm;

    private int idSession;

    public RequestInBean( String plant,  String client, LocalDate start, LocalDate end, int idRequestForm) throws BeanException {
        setClient(client);
        setEnd(end,start);
        setPlant(plant);
        setStart(start);
        setIdRequestForm(idRequestForm);

    }
    public void requestInBean2(int idRequestForm, int idSession){
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
}
