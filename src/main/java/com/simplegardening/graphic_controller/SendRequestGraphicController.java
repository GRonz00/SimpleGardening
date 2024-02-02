package com.simplegardening.graphic_controller;

import com.simplegardening.SimpleGardeningApplication;
import com.simplegardening.bean.in.RequestInBean;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class SendRequestGraphicController {
    @FXML
    private FlowPane requestPane;
    private int idSession;

    private void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    @FXML
    public void initialize(RequestOutBean requests, int idSession) {
        setIdSession(idSession);
        requestPane.getChildren().clear();
        try {
            for (int i = 0; i < requests.getAddressPro().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("request_pane.fxml"));
                AnchorPane pane = fxmlLoader.load();

                ((Label) pane.lookup("#nameLabel")).setText(requests.getPro().get(i));
                ((Label) pane.lookup("#addressLabel")).setText(requests.getAddressPro().get(i));
                ((Label) pane.lookup("#priceLabel")).setText(requests.getPrice().get(i));
                RequestInBean sendRequestInBean = new RequestInBean(requests.getPlant().get(i).getName(),requests.getStart().get(i),requests.getEnd().get(i),requests.getIdRequestForm().get(i),requests.getPickup().get(i),idSession);
                ((Button) pane.lookup("#sendButton")).setOnAction((ActionEvent event) -> {
                    try {
                        ManageRequestController manageRequestController = new ManageRequestController();
                        manageRequestController.sendRequest(sendRequestInBean);
                        FXMLLoader fxmlLoader1 = new FXMLLoader(SimpleGardeningApplication.class.getResource("home.fxml"));
                        Parent node = fxmlLoader1.load();
                        HomeGraphicController homeGraphicController = fxmlLoader1.getController();
                        homeGraphicController.initialize(idSession);
                        ((Node) event.getSource()).getScene().setRoot(node);
                    } catch (IOException e) {
                        ExceptionHandler.handleException("Could not go to next scene", e.getMessage());
                    } catch (ControllerException e) {
                        ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
                    }
                });
                requestPane.getChildren().add(pane);
            }
        } catch (IOException e) {
            ExceptionHandler.handleException("Could not go to next scene", e.getMessage());

        } catch (BeanException e) {
            ExceptionHandler.handleException(ExceptionHandler.BEAN_HEADER_TEXT,e.getMessage());
        }
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("home.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        HomeGraphicController homeGraphicController = fxmlLoader.getController();
        homeGraphicController.initialize(idSession);
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
}
