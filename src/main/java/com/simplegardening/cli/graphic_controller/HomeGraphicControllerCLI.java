package com.simplegardening.cli.graphic_controller;

import com.simplegardening.CLIApplication;
import com.simplegardening.bean.in.SessionBeanIn;
import com.simplegardening.bean.out.ListPlantOutBean;
import com.simplegardening.bean.out.PlantOutBean;
import com.simplegardening.cli.view.HomeViewCLI;
import com.simplegardening.controller.AddPlantController;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.exception.SessionException;
import com.simplegardening.model.SessionManager;

import java.io.IOException;
import java.sql.SQLException;

public class HomeGraphicControllerCLI {

    public void initialize(int idSession) {
        HomeViewCLI homeViewCLI = new HomeViewCLI();
        AddPlantController plantController = new AddPlantController();
        try {
            ListPlantOutBean listPlant = plantController.getPlants(new SessionBeanIn(idSession));
            for(PlantOutBean plant : listPlant.getPlant()){
                homeViewCLI.init(plant.getName(), plant.getType(), plant.getSize());
            }

            int choice = homeViewCLI.getAction();
            switch (choice){
                case 1-> {
                    AddPlantGraphicControllerCLI addPlantGraphicControllerCLI = new AddPlantGraphicControllerCLI();
                    addPlantGraphicControllerCLI.initialize(idSession);
                }
                case 2-> {
                    RequestClientGraphicControllerCLI requestClientGraphicControllerCLI = new RequestClientGraphicControllerCLI();
                    requestClientGraphicControllerCLI.initialize(idSession);
                }
                case 3-> {
                    ReminderGraphicControllerCLI reminderGraphicControllerCLI = new ReminderGraphicControllerCLI();
                    reminderGraphicControllerCLI.initialize(idSession);
                }
                case 4-> {
                    WeatherGraphicControllerCLI weatherGraphicControllerCLI = new WeatherGraphicControllerCLI();
                    weatherGraphicControllerCLI.initialize(idSession);
                }
                case 5-> {
                    MessagesGraphicControllerCLI messagesGraphicControllerCLI = new MessagesGraphicControllerCLI();
                    messagesGraphicControllerCLI.initialize(idSession);
                }
                case 6-> {
                    SessionManager.getInstance().closeSession(idSession);
                    CLIApplication.main(new String[1]);
                }
                default -> System.out.println("action choice error");
            }


        } catch (ControllerException | IOException | SQLException | SessionException e) {
            System.out.println("[ERR] " + e.getMessage());
            System.out.println("Please retry.");
        }

    }
    }
