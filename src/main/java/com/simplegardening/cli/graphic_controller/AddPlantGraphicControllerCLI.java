package com.simplegardening.cli.graphic_controller;

import com.simplegardening.bean.in.AddPlantInBean;
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
            AddPlantInBean addPlantInBean = new AddPlantInBean(idSession,p.get(0), p.get(1),p.get(2),null);
            AddPlantController addPlantController = new AddPlantController();
            addPlantController.savePlant(addPlantInBean);
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
