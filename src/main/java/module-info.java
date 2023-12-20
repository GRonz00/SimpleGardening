module com.simplegardening {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.fasterxml.jackson.databind;


    exports com.simplegardening;
    exports com.simplegardening.graphic_controller;
    exports com.simplegardening.utils;
    opens com.simplegardening.graphic_controller to javafx.fxml;
    opens com.simplegardening to javafx.fxml;
    exports com.simplegardening.bean.in;
    exports com.simplegardening.bean.out;


}