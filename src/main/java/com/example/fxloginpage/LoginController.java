package com.example.fxloginpage;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController
{
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button okayButton;

    @FXML
    protected void onSubmitButtonClick()
    {

    }

    @FXML
    protected void onClearButtonClick()
    {
        usernameField.setText("");
        passwordField.setText("");
    }

    @FXML
    protected void onCancelButtonClick()
    {
        Platform.exit();
    }

}