package com.example.fxloginpage;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PageController
{
    @FXML private Button logOut;

    @FXML protected void onLogOutButtonClick() throws IOException
    {
        switchToLoginPage();
    }

    @FXML protected void onCloseButtonClick()
    {
        Platform.exit();
    }

    @FXML private void switchToLoginPage() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("login-view.fxml"));
        Stage stage = (Stage) logOut.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 420, 340);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}
