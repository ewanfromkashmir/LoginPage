package com.example.fxloginpage;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAccountController
{

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField1;
    @FXML private PasswordField passwordField2;

    @FXML protected void initialize()
    {
        setPrompts();
    }

    @FXML protected void onSubmitButtonClick() throws IOException
    {
        String username = usernameField.getText().toLowerCase();
        String password1 = passwordField1.getText();
        String password2 = passwordField2.getText();

        Alert taken = new Alert(Alert.AlertType.INFORMATION, "Username already in use. Please choose another username.", ButtonType.OK);
        Alert different = new Alert(Alert.AlertType.INFORMATION, "Passwords do not match. Please try again.", ButtonType.OK);
        Alert successful = new Alert(Alert.AlertType.INFORMATION, "Account successfully created.", ButtonType.OK);

        if (queryUsername(username))
        {
            clearUsernameField();
            taken.showAndWait();
            return;
        }

        if (!comparePasswords(password1, password2))
        {
            clearPasswordFields();
            different.showAndWait();
            return;
        }

        createAccount(username, password1);
        successful.showAndWait();
        switchToLoginPage();
    }

    @FXML protected void onClearButtonClick()
    {
        clearPasswordFields();
    }

    @FXML protected void onCancelButtonClick() throws IOException
    {
        switchToLoginPage();
    }

    @FXML private void switchToLoginPage() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("login-view.fxml"));
        Stage stage = (Stage) usernameField.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 420, 340);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @FXML protected void setPrompts()
    {
        usernameField.setPromptText("Enter username");
        passwordField1.setPromptText("Enter password");
        passwordField2.setPromptText("Confirm password");
    }

    protected void clearPasswordFields()
    {
        // Clearing passwordField1 and passwordField2 by setting their text to ""
        passwordField1.setText("");
        passwordField2.setText("");
    }

    protected void clearUsernameField()
    {
        usernameField.setText("");
    }

    protected boolean comparePasswords(String password1, String password2)
    {
        return password1.equals(password2);
    }

    protected boolean queryUsername(String username)
    {
        return false;

//        try
//        {
//            Connection connection = DriverManager.getConnection(serverURL, serverUsername, serverPassword);
//            String usernameQuery = "SELECT * FROM users2;";
//            var statement = connection.prepareStatement(usernameQuery);
//            var results = statement.executeQuery();
//
//            while (results.next())
//            {
//                if (results.getString("Username").toLowerCase().equals(username))
//                {
//                    return true;
//                }
//            }
//
//            attempts--;
//            return false;
//        }
//        catch (SQLException e)
//        {
//            throw new RuntimeException(e);
//        }

    }

    protected void createAccount(String username, String password)
    {
        return;
    }
}
