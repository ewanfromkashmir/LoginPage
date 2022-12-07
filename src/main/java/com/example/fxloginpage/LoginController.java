package com.example.fxloginpage;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController
{
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    // Initialising integer attempts which tracks the number of attempts the user has remaining to log in
    int attempts = 3;

    @FXML
    protected void onSubmitButtonClick()
    {
        // Initialising username and password strings
        // In future this may be altered to incorporate an array of usernames and passwords
        String username = "admin";
        String password = "PaSSword!!!";

        // Declaring alerts correct, incorrect and locked which will be used to alert the user of the status of their log in attempt
        Alert correct = new Alert(Alert.AlertType.INFORMATION, "Login successful.", ButtonType.OK);
        Alert incorrect = new Alert(Alert.AlertType.INFORMATION, "Username and/or password not recognised.", ButtonType.OK);
        Alert locked = new Alert(Alert.AlertType.INFORMATION, "System locked. Please contact an administrator.", ButtonType.OK);

        // Case where the user still has an attempt or more remaining
        while (attempts > 0)
        {
            // Case where the username "Admin" or equivalent is entered
            // In future this may be expanded to check the input against an array of usernames
            if (usernameField.getText().toLowerCase().equals(username))
            {
                // Case where the correct password is entered
                if (passwordField.getText().equals(password))
                {
                    // Showing the correct alert and closing the window
                    correct.showAndWait();
                    Platform.exit();
                }

                // Case where the incorrect password is entered
                else
                {
                    // Decrementing the attempts variable
                    attempts--;

                    // Case where the user still has an attempt or more remaining
                    if (attempts > 0)
                    {
                        // Showing the incorrect alert, clearing passwordField and returning
                        incorrect.showAndWait();
                        passwordField.setText("");
                        return;
                    }

                    // Case where the user has now exhausted all of their attempts to log in
                    else
                    {
                        // Showing the locked alert and clearing both usernameField and passwordField
                        locked.showAndWait();
                        usernameField.setText("");
                        passwordField.setText("");

                        // Disabling usernameField and passwordField from being edited and returning
                        usernameField.setEditable(false);
                        passwordField.setEditable(false);
                        return;
                    }
                }
            }

            // Case where the username entered is not recognised
            else
            {
                // Showing the incorrect alert, clearing passwordField and returning
                incorrect.showAndWait();
                passwordField.setText("");
                return;
            }
        }

        // Case where the user has exhausted all of their attempts to log in
        // Showing the locked alert once again
        locked.showAndWait();
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