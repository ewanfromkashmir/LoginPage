module com.example.fxloginpage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;


    opens com.example.fxloginpage to javafx.fxml;
    exports com.example.fxloginpage;
}