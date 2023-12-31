package com.simplegardening.bean.out;

import com.simplegardening.model.Plant;

import java.util.ArrayList;
import java.util.List;

public class ListPlantOutBean {
    private List<PlantOutBean> plant ;

    public List<PlantOutBean> getPlant() {
        return plant;
    }

    public void setPlant(List<Plant> plantList) {
        this.plant = new ArrayList<>();
        for(Plant pl: plantList) {
            this.plant.add(new PlantOutBean(pl.getName(),pl.getType(),pl.getSize(),pl.getImage()));
        }
    }
}
