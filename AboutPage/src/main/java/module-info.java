module com.example.aboutpage {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aboutpage to javafx.fxml;
    exports com.example.aboutpage;
}