package com.simplegardening.graphic_controller;

import com.simplegardening.SimpleGardeningApplication;
import com.simplegardening.bean.in.ReminderInBean;
import com.simplegardening.bean.out.ListPlantOutBean;
import com.simplegardening.bean.out.PlantOutBean;
import com.simplegardening.controller.AddPlantController;
import com.simplegardening.controller.LoginController;
import com.simplegardening.controller.ManageReminderController;
import com.simplegardening.exception.BeanException;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class HomeGraphicController {


    @FXML
    private FlowPane plantsPane;
    private int idSession;

    private void setIdSession(int idSession){
        this.idSession = idSession;
    }
    @FXML
    public void initialize(int idSession) throws IOException {

        setIdSession(idSession);
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
                ((Button) pane.lookup("#sendButton")).setOnAction((ActionEvent event) -> {
                            try {
                                FXMLLoader fxmlLoader1 = new FXMLLoader(SimpleGardeningApplication.class.getResource("findRequest.fxml"));
                                Parent node = fxmlLoader1.load();
                                ((Node) event.getSource()).getScene().setRoot(node);
                                FindRequestGraphicController findRequestGraphicController = fxmlLoader1.getController();
                                findRequestGraphicController.initialize(idSession, plant.getName());
                            } catch (IOException e) {
                                ExceptionHandler.handleException("Could not go to next scene", e.getMessage());
                            }
                });
                ((Button) pane.lookup("#waterButton")).setOnAction((ActionEvent event) -> {
                    try {
                        ReminderInBean bean = new ReminderInBean(idSession,plant.getName(),1,((TextField) pane.lookup("#waterHour")).getText(),((TextField) pane.lookup("#waterMin")).getText());
                        new ManageReminderController().setReminder(bean);
                        (pane.lookup("#HWat")).setVisible(false);
                        ((Label)pane.lookup("#TimeW")).setText(bean.getTime());
                        (pane.lookup("#TimeW")).setVisible(true);
                        }
                     catch (BeanException e) {
                        ExceptionHandler.handleException(ExceptionHandler.BEAN_HEADER_TEXT,e.getMessage());
                    }catch (ControllerException e) {
                    ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
                }
                });
                ((Button) pane.lookup("#nebButton")).setOnAction((ActionEvent event) -> {
                    try {
                        ReminderInBean bean = new ReminderInBean(idSession,plant.getName(), 2,((TextField) pane.lookup("#nebHour")).getText(),((TextField) pane.lookup("#nebMin")).getText());
                        new ManageReminderController().setReminder(bean);
                        (pane.lookup("#HNeb")).setVisible(false);
                        ((Label)pane.lookup("#TimeN")).setText(bean.getTime());
                        (pane.lookup("#TimeN")).setVisible(true);

                    } catch (BeanException e) {
                        ExceptionHandler.handleException(ExceptionHandler.BEAN_HEADER_TEXT,e.getMessage());
                    }catch (ControllerException e){
                        ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
                    }
                });
                ((Button) pane.lookup("#ferButton")).setOnAction((ActionEvent event) -> {
                    try {
                        ReminderInBean bean = new ReminderInBean(idSession,plant.getName(), 3,((TextField) pane.lookup("#ferHour")).getText(),((TextField) pane.lookup("#ferMin")).getText());
                        new ManageReminderController().setReminder(bean);
                        (pane.lookup("#HFer")).setVisible(false);
                        ((Label)pane.lookup("#TimeF")).setText(bean.getTime());
                        (pane.lookup("#TimeF")).setVisible(true);
                    } catch (BeanException e) {
                        ExceptionHandler.handleException(ExceptionHandler.BEAN_HEADER_TEXT,e.getMessage());
                    }catch (ControllerException e){
                        ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
                    }
                });
                if (plant.getImage() != null)
                {
                    ((ImageView) pane.lookup("#imageView")).setImage(new Image(plant.getImage()));
                    (pane.lookup("#imageView")).setVisible(true);
                }
                plantsPane.getChildren().add(pane);
            }
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("new_plant.fxml"));
        AnchorPane pane = fxmlLoader.load();
        AddPlantGraphicController addPlantGraphicController = fxmlLoader.getController();
        addPlantGraphicController.initialize(this,idSession);
        plantsPane.getChildren().add(pane);}
        catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        }


    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        try {
            new LoginController().closeSession(idSession);
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        }
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
    @FXML
    public void weather(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("weather.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
        WeatherGraphicController weatherGraphicController = fxmlLoader.getController();
        weatherGraphicController.initialize(idSession);
    }

    @FXML
    public void goChat(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("chatList.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
        ChatListGraphicController graphicController = fxmlLoader.getController();
        graphicController.initialize(idSession);


    }
}
