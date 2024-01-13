package com.simplegardening.bean.in;

import com.simplegardening.exception.BeanException;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class FindRequestInBean {
    private LocalDate start;
    private LocalDate end;
    private String plantName;
    private int idSession;
    private boolean pickUp;
    private int maxKm;

    public FindRequestInBean(LocalDate start, LocalDate end, String plantName, int idSession, boolean pickUp, String maxKm) throws BeanException {
        setEnd(end,start);
        setStart(start);
        setPickUp(pickUp);
        setPlantName(plantName);
        setIdSession(idSession);
        setMaxKm(maxKm,pickUp);
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) throws BeanException {
        if(start.isBefore(LocalDate.now()))throw new BeanException("Start","Is before of today");
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end,LocalDate start) throws BeanException {
        if(end.isBefore(start))throw new BeanException("Date","End is before start");
        this.end = end;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) throws BeanException {
        if(Pattern.matches("[a-zA-Z0-9 ]",plantName))throw new BeanException("plant name",BeanException.ONLY_REG);

        this.plantName = plantName;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public boolean isPickUp() {
        return pickUp;
    }

    public void setPickUp(boolean pickUp) {
        this.pickUp = pickUp;
    }

    public int getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(String maxKm, boolean pickUp) throws BeanException {

        try {
            if (pickUp)this.maxKm = Integer.parseInt(maxKm);
            else this.maxKm=0;
        }catch (NumberFormatException e){
            throw new BeanException("Km max",e.getMessage());
        }
    }
}
