package com.simplegardening.graphic_controller;

import com.simplegardening.SimpleGardeningApplication;
import com.simplegardening.bean.in.RequestFormInBean;
import com.simplegardening.controller.LoginController;
import com.simplegardening.controller.ManageRequestController;
import com.simplegardening.exception.BeanException;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NewRequestFormGraphicController {
    @FXML
    private TextField priceKm;
    @FXML
    private TextField extraPrice;
    @FXML
    private TextField amount;
    @FXML
    private TextField basePricePickUp;
    @FXML
    private TextField maxKm;
    @FXML
    private RadioButton pickUpAviButton;
    @FXML
    private RadioButton newCostButton;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField basePriceField;
    @FXML
    private ComboBox<String> plantTypeCombo;
    @FXML
    private ComboBox<String> plantSizeCombo;



    private int idSession;

    private void setIdSession(int idSession){
        this.idSession = idSession;
    }

    @FXML
    public void initialize(int idSession){
        setIdSession(idSession);
    }
    @FXML
    public void newRequestForm(ActionEvent actionEvent){
        try {
            RequestFormInBean requestFormInBean = new RequestFormInBean(startDate.getValue(),endDate.getValue(),basePriceField.getText(),pickUpAviButton.isSelected(),maxKm.getText(),basePricePickUp.getText(),priceKm.getText(),newCostButton.isSelected(),extraPrice.getText(),plantSizeCombo.getValue(),plantTypeCombo.getValue(),amount.getText(),idSession);
            ManageRequestController manageRequestController = new ManageRequestController();
            manageRequestController.addRequestForm(requestFormInBean);
            FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("home_pro.fxml"));
            ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
            HomeProGraphicController homeGraphicController = fxmlLoader.getController();
            homeGraphicController.initialize(idSession);

        } catch (BeanException e) {
            ExceptionHandler.handleException(ExceptionHandler.BEAN_HEADER_TEXT,e.getMessage());
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        } catch (IOException e) {
            ExceptionHandler.handleException("Could not go to next scene", e.getMessage());
        }
    }


    public void logout(ActionEvent actionEvent) throws IOException {
        try {
            new LoginController().closeSession(idSession);
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        }
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("login.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("newRequestForm.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        RequestProGraphicController requestProGraphicController = fxmlLoader.getController();
        requestProGraphicController.initialize(idSession);
    }
}
