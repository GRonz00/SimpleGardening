package com.simplegardening.graphic_controller;

import com.simplegardening.SimpleGardeningApplication;
import com.simplegardening.bean.in.LoginBeanIn;
import com.simplegardening.bean.out.LoginBeanOut;
import com.simplegardening.controller.LoginController;
import com.simplegardening.exception.BeanException;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.utils.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginGraphicController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    @FXML
    protected void goToRegister(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("register.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }
    @FXML
    protected void login(ActionEvent actionEvent) throws IOException {
        try {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            LoginBeanIn bean = new LoginBeanIn(username, password);
            LoginController controller = new LoginController();
            LoginBeanOut beanOut = controller.login(bean);
            if (beanOut.getTypeUser() == 1) {
                FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("home.fxml"));
                ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
                HomeGraphicController homeGraphicController = fxmlLoader.getController();
                homeGraphicController.setIdSession(beanOut.getIdSession());
            }
            if (beanOut.getTypeUser() == 2) {
                FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("home_pro.fxml"));
                ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
            }

        } catch (BeanException e) {
            ExceptionHandler.handleException(ExceptionHandler.BEAN_HEADER_TEXT, e.getMessage());
        }
        catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
        }
    }
}
