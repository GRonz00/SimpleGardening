package com.simplegardening.graphic_controller;

import com.simplegardening.bean.in.AddPlantInBean;
import com.simplegardening.bean.in.UploadImageInBean;
import com.simplegardening.controller.AddPlantController;
import com.simplegardening.controller.UploadImageController;
import com.simplegardening.exception.BeanException;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.exception.ImageException;
import com.simplegardening.utils.ExceptionHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.InputStream;

public class AddPlantGraphicController {
    @FXML
    private ComboBox<String> type;
    @FXML
    private ComboBox<String> size;
    @FXML
    private TextField name;
    @FXML
    private Button imageButton;
    @FXML
    private ImageView imageView;

    private InputStream image;


    private int idSession;
    public void setIdSession(int idSession){
        this.idSession = idSession;
    }
    @FXML
    protected void add(){
        try {
            AddPlantInBean addPlantInBean = new AddPlantInBean(idSession,name.getText(),type.getValue(),size.getValue(),image);
            AddPlantController addPlantController = new AddPlantController();
            addPlantController.savePlant(addPlantInBean);
        } catch (BeanException e) {
            ExceptionHandler.handleException(ExceptionHandler.BEAN_HEADER_TEXT,e.getMessage());
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        }

    }

    @FXML
    public void uploadImage() {
        try {
            UploadImageController controller = new UploadImageController();
            File img = controller.chooseImage();
            UploadImageInBean uploadImageInBean = new UploadImageInBean();
            uploadImageInBean.setImage(img);
            image = controller.validateImage(uploadImageInBean);
            imageButton.setVisible(false);
            if (image!= null)
                imageView.setImage(new Image(image));
            imageView.setVisible(true);
        } catch (ImageException e) {
            ExceptionHandler.handleException("Image", e.getMessage());
        }
    }
}
