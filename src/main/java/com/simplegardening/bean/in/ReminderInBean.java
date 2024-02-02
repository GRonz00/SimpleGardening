package com.simplegardening.bean.in;

import com.simplegardening.exception.BeanException;
import com.simplegardening.utils.ReminderType;

public class ReminderInBean {
    private ReminderType type;
    private String time;
    private String plant;
    private int idSession;
    public ReminderInBean(int idSession, String plant, int type, String hour, String min) throws BeanException {
        setType(type);
        setTime(hour,min);
        setPlant(plant);
        setIdSession(idSession);
    }
    public ReminderInBean(int idSession, String plant, String type, String hour, String min) throws BeanException {
        this.type=ReminderType.valueOf(type);
        setTime(hour,min);
        setPlant(plant);
        setIdSession(idSession);
    }

    public ReminderType getType() {
        return type;
    }

    public void setType(int type) throws BeanException {
        switch (type){
            case 1 -> this.type=ReminderType.WATER;
            case 2 -> this.type=ReminderType.NEBULIZE;
            case 3 -> this.type=ReminderType.FERTILIZE;
            default -> throw new BeanException("Type","Unexpected value: " + type);
        }

    }

    public String getTime() {
        return time;
    }

    public void setTime(String hour, String min) throws BeanException {
        try{
            int h = Integer.parseInt(hour);
            if(h<0||h>23)throw new BeanException("Hour","is out 24h");
            int m = Integer.parseInt(min);
            if(m<0||m>59)throw new BeanException("Min","is out 0-59");
            this.time = hour+":"+min;
        }catch (NumberFormatException e){
            throw new BeanException("Time",e.getMessage());
        }

    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }
}
