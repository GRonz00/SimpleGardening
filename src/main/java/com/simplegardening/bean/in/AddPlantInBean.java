package com.simplegardening.bean.in;

import com.simplegardening.exception.BeanException;
import com.simplegardening.utils.PlantSize;
import com.simplegardening.utils.PlantType;

import java.io.InputStream;
import java.util.regex.Pattern;

public class AddPlantInBean {
    private int idSession;
    private String name;
    private PlantType type;
    private PlantSize size;
    private InputStream image;


    public AddPlantInBean(int idSession,String name, String type, String size, InputStream image) throws BeanException {
        setIdSession(idSession);
        setImage(image);
        setSize(size);
        setName(name);
        setType(type);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) throws BeanException {

        if(Pattern.matches("[a-zA-Z0-9]",name))throw new BeanException("name",BeanException.ONLY_REG);
        this.name = name;
    }

    public PlantType getType() {
        return type;
    }

    public void setType(String type) {
        this.type = PlantType.valueOf(type.toUpperCase());
    }

    public PlantSize getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = PlantSize.valueOf(size.toUpperCase());
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }
}
