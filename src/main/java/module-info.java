module com.example.fxloginpage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires java.sql;


    opens com.example.fxloginpage to javafx.fxml;
    exports com.example.fxloginpage;
}