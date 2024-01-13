package com.simplegardening.graphic_controller;

import com.simplegardening.SimpleGardeningApplication;
import com.simplegardening.bean.in.WeatherClientInBean;
import com.simplegardening.bean.out.WeatherClientOutBean;
import com.simplegardening.controller.LoginController;
import com.simplegardening.controller.WeatherForecastController;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class WeatherGraphicController {

    private int idSession;

    private void setIdSession(int idSession){
        this.idSession = idSession;
    }
    @FXML
    private HBox week;
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
    public void goBack(ActionEvent event)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("home.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
        HomeGraphicController homeGraphicController = fxmlLoader.getController();
        homeGraphicController.initialize(idSession);
    }

    @FXML
    public void initialize(int idSession){

        setIdSession(idSession);
        week.getChildren().clear();
        WeatherForecastController weatherForecastController = new WeatherForecastController();
        WeatherClientInBean weatherClientInBean = new WeatherClientInBean(idSession);
        try {
            WeatherClientOutBean weatherClientOutBean = weatherForecastController.weeklyWeatherForecast(weatherClientInBean);
            for(int i=0;i<weatherClientOutBean.getDays().size();i++){
                System.out.println(idSession);
                FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("weather_pane.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                if(i%2==0){
                    anchorPane.setStyle("-fx-background-color: #FFF5EE");
                }else anchorPane.setStyle("-fx-background-color: #C0C0C0");
                if(weatherClientOutBean.getFall().get(i)<40)
                {(anchorPane.lookup("#rainIcon")).setVisible(false); }
                else (anchorPane.lookup("#sunIcon")).setVisible(false);
                ((Label)anchorPane.lookup("#day")).setText(weatherClientOutBean.getDays().get(i));
                ((Label)anchorPane.lookup("#t_max")).setText(weatherClientOutBean.getTempMax().get(i).toString());
                ((Label)anchorPane.lookup("#t_min")).setText(weatherClientOutBean.getTempMin().get(i).toString());
                ((Label)anchorPane.lookup("#rain")).setText(weatherClientOutBean.getFall().get(i).toString());
                week.getChildren().add(anchorPane);

            }

        } catch (ControllerException e) {
            ExceptionHandler.handleException("Weather not received", e.getMessage());
        } catch (IOException e) {
            ExceptionHandler.handleException("Could not go to next scene", e.getMessage());
        }
    }
}
