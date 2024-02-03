package com.simplegardening.graphic_controller;

import com.simplegardening.SimpleGardeningApplication;
import com.simplegardening.bean.in.FindRequestInBean;
import com.simplegardening.bean.in.SessionBeanIn;
import com.simplegardening.bean.out.RequestOutBean;
import com.simplegardening.controller.LoginController;
import com.simplegardening.controller.ManageRequestController;
import com.simplegardening.exception.BeanException;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FindRequestGraphicController {
    @FXML
    private TextField maxKmField;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private RadioButton pickUp;
    private int idSession;
    private String plantName;

    private void setIdSession(int idSession){
        this.idSession = idSession;
    }


    public void findRequest(ActionEvent actionEvent) throws IOException {
        try {
            FindRequestInBean findRequestInBean = new FindRequestInBean(startDate.getValue(),endDate.getValue(),plantName,idSession,pickUp.isSelected(),maxKmField.getText());
            ManageRequestController manageRequestController = new ManageRequestController();
            RequestOutBean outBean = manageRequestController.findsPossibleRequest(findRequestInBean);
            FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("sendRequest.fxml"));
            ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
            SendRequestGraphicController sendRequestGraphicController = fxmlLoader.getController();
            sendRequestGraphicController.initialize(outBean,idSession);



        } catch (BeanException e) {
            ExceptionHandler.handleException(ExceptionHandler.BEAN_HEADER_TEXT,e.getMessage());
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        }

    }

    public void initialize(int idSession, String name) {
        setIdSession(idSession);
        this.plantName=name;
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("home.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
        HomeGraphicController homeGraphicController = fxmlLoader.getController();
        homeGraphicController.initialize(idSession);
    }
    @FXML
    public void logout(ActionEvent event) throws IOException {
        try {
            new LoginController().closeSession(new SessionBeanIn(idSession));
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        }
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }



}
