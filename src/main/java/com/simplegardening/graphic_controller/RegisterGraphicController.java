package com.simplegardening.graphic_controller;

import com.simplegardening.SimpleGardeningApplication;
import com.simplegardening.bean.in.AddressInBean;
import com.simplegardening.bean.in.RegisterInBean;
import com.simplegardening.bean.out.AddressOutBean;
import com.simplegardening.controller.ConvertAddressController;
import com.simplegardening.controller.RegisterController;
import com.simplegardening.exception.BeanException;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterGraphicController {
    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField city;
    @FXML
    private TextField street;
    @FXML
    private TextField nation;
    @FXML
    private TextField pC;
    @FXML
    private TextField number;
    @FXML
    private ComboBox<String> userType;

    @FXML
    protected void register(ActionEvent actionEvent) throws IOException {

        try {
        ConvertAddressController convertAddressController = new ConvertAddressController();
        AddressInBean addressInBean = new AddressInBean(nation.getText(),city.getText(),street.getText(),pC.getText());
        AddressOutBean addressOutBean = convertAddressController.convert(addressInBean);
        String address = street.getText()+" "+number.getText()+" "+city.getText()+" "+pC.getText();
        RegisterInBean registerInBean = new RegisterInBean(usernameTextField.getText(),passwordTextField.getText(),address, userType.getValue(), addressOutBean.getLongitude(), addressOutBean.getLatitude());
        RegisterController registerController = new RegisterController();
        registerController.register(registerInBean);
        LoginGraphicController loginGraphicController = new LoginGraphicController();
        loginGraphicController.log(usernameTextField.getText(),passwordTextField.getText(),actionEvent);
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
        } catch (BeanException e) {
            ExceptionHandler.handleException(ExceptionHandler.BEAN_HEADER_TEXT, e.getMessage());
        }
    }

    @FXML
    protected void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
}
