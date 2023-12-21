package com.simplegardening.graphic_controller;

import com.simplegardening.bean.in.ConvertAddressInBean;
import com.simplegardening.bean.in.RegisterInBean;
import com.simplegardening.bean.out.ConvertAddressOutBean;
import com.simplegardening.controller.ConvertAddressController;
import com.simplegardening.controller.RegisterController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
    public void register(){

        ConvertAddressController convertAddressController = new ConvertAddressController();
        ConvertAddressInBean convertAddressInBean = new ConvertAddressInBean(nation.getText(),city.getText(),street.getText(),pC.getText());
        ConvertAddressOutBean convertAddressOutBean = convertAddressController.convert(convertAddressInBean);
        String address = street.getText()+number.getText()+city.getText()+pC.getText();
        String daFareType = "Client";
        RegisterInBean registerInBean = new RegisterInBean(usernameTextField.getText(),passwordTextField.getText(),address,daFareType,convertAddressOutBean.getLongitude(),convertAddressOutBean.getLatitude());
        RegisterController registerController = new RegisterController();
        registerController.register(registerInBean);


    }
}
