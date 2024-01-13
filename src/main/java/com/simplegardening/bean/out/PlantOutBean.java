package com.simplegardening.bean.out;

import com.simplegardening.model.Plant;
import com.simplegardening.utils.PlantSize;
import com.simplegardening.utils.PlantType;

import java.io.InputStream;

public class PlantOutBean {

    private String name;
    private String type;
    private String size;
    private InputStream image;

    public PlantOutBean(String name, PlantType type, PlantSize size, InputStream image){
        setName(name);
        setType(type.toString());
        setSize(size.toString());
        setImage(image);
    }
    public PlantOutBean(Plant plant){
        setImage(plant.getImage());
        setType(plant.getType().toString());
        setSize(plant.getSize().toString());
        setName(plant.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
}
