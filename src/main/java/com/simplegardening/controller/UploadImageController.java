package com.simplegardening.controller;

import com.simplegardening.bean.in.UploadImageInBean;
import com.simplegardening.exception.ImageException;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UploadImageController {
    public File chooseImage() {
        FileChooser chooser = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        chooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        return chooser.showOpenDialog(null);
    }

    public InputStream validateImage(UploadImageInBean imageBean) throws ImageException {
        File image = imageBean.getImage();
        if (image == null)
            throw new ImageException("No image selected");
        try {
            return new FileInputStream(image);
        } catch (FileNotFoundException e) {
            throw new ImageException("Image not found");
        }
    }


}
