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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class RequestProGraphicController {

    @FXML
    private FlowPane requestsPane;
    private int idSession;

    private void setIdSession(int idSession){
        this.idSession = idSession;
    }

    @FXML
    public void initialize(int idSession){
        setIdSession(idSession);
        requestsPane.getChildren().clear();
        ManageRequestController manageRequestController = new ManageRequestController();
        try {
            RequestOutBean requests = manageRequestController.getRequests(idSession);
            for (int i=0;i<requests.getPro().size();i++){
                if(!Objects.equals(requests.getState().get(i), "SENT")||requests.getStart().get(i).isBefore(LocalDate.now()))continue;
                FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("pendingRequest.fxml"));
                AnchorPane pane = fxmlLoader.load();
                ((Label) pane.lookup("#endLabel")).setText(requests.getEnd().get(i).toString());
                ((Label) pane.lookup("#addressClientLabel")).setText(requests.getAddressClient().get(i));
                ((Label) pane.lookup("#clientLabel")).setText(requests.getClient().get(i));
                ((Label) pane.lookup("#plantLabel1")).setText(requests.getPlant().get(i).getName());
                ((Label) pane.lookup("#priceLabel")).setText(requests.getPrice().get(i));
                ((Label) pane.lookup("#startLabel")).setText(requests.getStart().get(i).toString());
                ((Label) pane.lookup("#pickupLabel")).setText(requests.getPickup().get(i).toString());
                RequestInBean requestInBean = new RequestInBean(requests.getPro().get(i),requests.getPrice().get(i),requests.getPlant().get(i).getName(),requests.getPickup().get(i),requests.getClient().get(i),requests.getStart().get(i),requests.getEnd().get(i));
                requestInBean.requestInBean2(requests.getIdRequestForm().get(i),idSession);
                if (requests.getPlant().get(i).getImage() != null)
                    ((ImageView) pane.lookup("#imageView")).setImage(new Image(requests.getPlant().get(i).getImage()));
                ((Button) pane.lookup("#acceptButton")).setOnAction((ActionEvent event) -> {
                    try {
                        manageRequestController.acceptRequest(requestInBean);
                        initialize(idSession);
                    } catch (ControllerException e) {
                        ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
                    }
                    });
                ((Button) pane.lookup("#refuseButton")).setOnAction((ActionEvent event) -> {
                    try {
                        manageRequestController.refuseRequest(requestInBean);
                        initialize(idSession);
                    } catch (ControllerException e) {
                        ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
                    }
                    initialize(idSession);
                });
                requestsPane.getChildren().add(pane);
            }
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        } catch (IOException e) {
            ExceptionHandler.handleException("Could not go to next scene", e.getMessage());
        } catch (BeanException e) {
            ExceptionHandler.handleException(ExceptionHandler.BEAN_HEADER_TEXT,e.getMessage());
        }
    }
    @FXML
    public void goNewRequestForm(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("newRequestForm.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        NewRequestFormGraphicController newRequestFormGraphicController = fxmlLoader.getController();
        newRequestFormGraphicController.initialize(idSession);


    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("home_pro.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        HomeProGraphicController homeGraphicController = fxmlLoader.getController();
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


    public void goFutureRequest(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("requestOverTime.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        RequestOverTimeGraphicController requestOverTimeGraphicController = fxmlLoader.getController();
        requestOverTimeGraphicController.initialize(idSession,1);
    }

    public void goPassRequest(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("requestOverTime.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        RequestOverTimeGraphicController requestOverTimeGraphicController = fxmlLoader.getController();
        requestOverTimeGraphicController.initialize(idSession,0);
    }
}
