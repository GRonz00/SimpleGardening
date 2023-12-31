package com.simplegardening.model;

import com.simplegardening.utils.PlantSize;
import com.simplegardening.utils.PlantType;

import java.io.InputStream;

public class Plant {
    private User client;
    private String name;
    private PlantSize size;
    private PlantType type;
    private InputStream image;

    public Plant(User client,String name,PlantSize size, PlantType type, InputStream image){
        setClient(client);
        setName(name);
        setSize(size);
        setType(type);
        setImage(image);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlantSize getSize() {
        return size;
    }

    public void setSize(PlantSize size) {
        this.size = size;
    }

    public PlantType getType() {
        return type;
    }

    public void setType(PlantType type) {
        this.type = type;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
