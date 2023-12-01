module com.example.simplegardening {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.simplegardening to javafx.fxml;
    exports com.example.simplegardening;
}