package com.simplegardening.graphic_controller;

import com.simplegardening.bean.in.AddPlantInBean;
import com.simplegardening.bean.in.ImageInBean;
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

import java.io.*;

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
    private HomeGraphicController homeGraphicController;


    private int idSession;
    private void setIdSession(int idSession){
        this.idSession = idSession;
    }
    @FXML
    protected void add(){
        try {
            AddPlantInBean addPlantInBean = new AddPlantInBean(idSession,name.getText(),type.getValue(),size.getValue(),image);
            AddPlantController addPlantController = new AddPlantController();
            addPlantController.savePlant(addPlantInBean);
            homeGraphicController.initialize(idSession);
        } catch (BeanException e) {
            ExceptionHandler.handleException(ExceptionHandler.BEAN_HEADER_TEXT,e.getMessage());
        } catch (ControllerException e) {
            ExceptionHandler.handleException(ExceptionHandler.CONTROLLER_HEADER_TEXT,e.getMessage());
        }catch (IOException e) {
            ExceptionHandler.handleException("Could not go to next scene", e.getMessage());
        }

    }

    @FXML
    public void uploadImage() {
        try {
            UploadImageController controller = new UploadImageController();
            File img = controller.chooseImage();
            ImageInBean imageInBean = new ImageInBean();
            imageInBean.setImage(img);

            image = controller.validateImage(imageInBean);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int bytesRead;

            while ((bytesRead = image.read(data)) != -1) {
                buffer.write(data, 0, bytesRead);
            }
            InputStream imgClone = new ByteArrayInputStream(buffer.toByteArray());
            image = new ByteArrayInputStream(buffer.toByteArray());
            buffer.flush();
            imageButton.setVisible(false);
            imageView.setImage(new Image(imgClone));
            imageView.setVisible(true);
        } catch (ImageException e) {
            ExceptionHandler.handleException("Image", e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize(HomeGraphicController homeGraphicController,int idSession) {
        this.homeGraphicController= homeGraphicController;
        setIdSession(idSession);

    }
}
