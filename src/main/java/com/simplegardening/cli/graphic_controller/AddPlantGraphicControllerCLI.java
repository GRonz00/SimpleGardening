package com.simplegardening.cli.graphic_controller;

import com.simplegardening.bean.in.PlantInBean;
import com.simplegardening.cli.view.AddPlantViewCLI;
import com.simplegardening.controller.AddPlantController;
import com.simplegardening.exception.BeanException;
import com.simplegardening.exception.ControllerException;

import java.io.IOException;
import java.util.List;

public class AddPlantGraphicControllerCLI {

    public void initialize(int idSession) {
        AddPlantViewCLI addPlantViewCLI = new AddPlantViewCLI();
        try {
            List<String> p = addPlantViewCLI.getPlant();
            PlantInBean plantInBean = new PlantInBean(idSession,p.get(0), p.get(1),p.get(2),null);
            AddPlantController addPlantController = new AddPlantController();
            addPlantController.savePlant(plantInBean);
            new HomeGraphicControllerCLI().initialize(idSession);

        } catch (ControllerException | IOException | BeanException e) {
            System.out.println("[ERR] " + e.getMessage());
            System.out.println("Please retry.");
        }finally {
            HomeGraphicControllerCLI homeGraphicControllerCLI = new HomeGraphicControllerCLI();
            homeGraphicControllerCLI.initialize(idSession);
        }

    }
}
