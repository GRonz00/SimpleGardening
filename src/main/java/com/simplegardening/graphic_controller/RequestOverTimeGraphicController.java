package com.simplegardening.graphic_controller;

import com.simplegardening.SimpleGardeningApplication;
import com.simplegardening.bean.in.SessionBeanIn;
import com.simplegardening.bean.out.RequestOutBean;
import com.simplegardening.controller.LoginController;
import com.simplegardening.controller.ManageRequestController;
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
import java.time.LocalDate;
import java.util.Objects;

public class RequestOverTimeGraphicController {

    private int idSession;

    private void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    @FXML
    private FlowPane requestsPane;
    @FXML
    private Label titleLabel;

    @FXML
    public void initialize(int idSession, int time) {
        setIdSession(idSession);
        requestsPane.getChildren().clear();
        ManageRequestController manageRequestController = new ManageRequestController();
        if(time==1)titleLabel.setText("Future Request");
        try {
            RequestOutBean requests = manageRequestController.getRequests(new SessionBeanIn(idSession));
            for (int i = 0; i < requests.getPro().size(); i++) {
                if ((!Objects.equals(requests.getState().get(i), "ACCEPTED"))||
                        (time == 0 && !requests.getEnd().get(i).isBefore(LocalDate.now()))||
                        (time == 1 && !requests.getStart().get(i).isAfter(LocalDate.now())))
                    continue;
                showRequest(requests, i);
            }
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
        } catch (IOException e) {
            ExceptionHandler.handleException("Could not go to next scene", e.getMessage());
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("requestPro.fxml"));
        ((Node) actionEvent.getSource()).getScene().setRoot(fxmlLoader.load());
        HomeProGraphicController graphicController = fxmlLoader.getController();
        graphicController.initialize(idSession);
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        try {
            new LoginController().closeSession(new SessionBeanIn(idSession));
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT, e.getMessage());
        }
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("login.fxml"));
        ((Node) event.getSource()).getScene().setRoot(fxmlLoader.load());
    }



    private void showRequest(RequestOutBean requests, int i) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleGardeningApplication.class.getResource("requestPro_pane.fxml"));
        AnchorPane pane = fxmlLoader.load();
        if (requests.getPlant().get(i).getImage() != null)
            ((ImageView) pane.lookup("#imageView")).setImage(new Image(requests.getPlant().get(i).getImage()));
        ((Label) pane.lookup("#startLabel")).setText(requests.getStart().get(i).toString());
        ((Label) pane.lookup("#plantLabel1")).setText(requests.getPlant().get(i).getName());
        ((Label) pane.lookup("#addressClientLabel")).setText(requests.getAddressClient().get(i));
        ((Label) pane.lookup("#priceLabel")).setText(requests.getPrice().get(i));
        ((Label) pane.lookup("#clientLabel")).setText(requests.getClient().get(i));
        ((Label) pane.lookup("#endLabel")).setText(requests.getEnd().get(i).toString());
        ((Label) pane.lookup("#pickupLabel")).setText(requests.getPickup().get(i).toString());
        requestsPane.getChildren().add(pane);
    }
}


