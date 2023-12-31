package com.simplegardening.graphic_controller;

import com.simplegardening.SimpleGardeningApplication;
import com.simplegardening.bean.out.ListPlantOutBean;
import com.simplegardening.bean.out.PlantOutBean;
import com.simplegardening.controller.AddPlantController;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class HomeGraphicController {

    private int idSession;
    @FXML
    private FlowPane plantsPane;

    public void setIdSession(int idSession){
        this.idSession = idSession;
    }
    @FXML
    protected void initialize() throws IOException {

        plantsPane.getChildren().clear();
        AddPlantController plantController = new AddPlantController();
        try {
            ListPlantOutBean listPlant = plantController.getPlants(idSession);
            for (PlantOutBean plant: listPlant.getPlant()){
                FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("plant.fxml"));
                AnchorPane pane = fxmlLoader.load();
                ((Label) pane.lookup("#nameLabel")).setText(plant.getName());
                ((Label) pane.lookup("#typeLabel")).setText(plant.getType());
                ((Label) pane.lookup("#sizeLabel")).setText(plant.getSize());
                if (plant.getImage() != null)
                    ((ImageView) pane.lookup("#imageView")).setImage(new Image(plant.getImage()));
                plantsPane.getChildren().add(pane);
            }
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("new_plant.fxml"));
        AnchorPane pane = fxmlLoader.load();
        AddPlantGraphicController addPlantGraphicController = fxmlLoader.getController();
        addPlantGraphicController.setIdSession(idSession);
        plantsPane.getChildren().add(pane);}
        catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        }


    }
    @FXML
    public void logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
    @FXML
    public void weather(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("weather.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
        WeatherGraphicController weatherGraphicController = fxmlLoader.getController();
        weatherGraphicController.setIdSession(idSession);
    }
}
