package com.example.fxloginpage;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;

public class LoginController
{
    // Initialising usernameField TextField, passwordField PasswordField
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    // Initialising integer attempts which tracks the number of attempts the user has remaining to log in
    int attempts = 3;

    // Initialising server URL, username and password as variables for use when connecting to database
    static String serverURL = "jdbc:sqlserver://SQL8002.site4now.net;database=db_a8cc79_Ewahes";
    static String serverUsername = "db_a8cc79_Ewahes_admin";
    static String serverPassword = "Fr43yX52kE71";

    @FXML protected void initialize()
    {
        setPrompts();
    }

    @FXML protected void onSubmitButtonClick() throws IOException, SQLException
    {
        // Initialising username and password strings based on user input
        String username = usernameField.getText().toLowerCase();
        String password = passwordField.getText();

        // Declaring alerts correct, incorrect and locked which will be used to alert the user of the status of their login attempt
        Alert correct = new Alert(Alert.AlertType.INFORMATION, "Login successful.", ButtonType.OK);
        Alert incorrect = new Alert(Alert.AlertType.INFORMATION, "Username and/or password not recognised.", ButtonType.OK);
        Alert locked = new Alert(Alert.AlertType.INFORMATION, "System locked. Please contact an administrator.", ButtonType.OK);

        // Case where the user has an attempt or more left to log in
        while (attempts > 0)
        {
            // Case where the username entered can be found in the database i.e. a valid user
            if (queryUsername(username))
            {
                // Case where the username and password together can be found in the database i.e. a valid login
                if (queryPassword(username, password))
                {
                    // Calling the switchToPage function to switch pages and displaying the correct alert
                    switchToPage();
                    correct.showAndWait();
                }

                else
                {
                    attempts--;

                    if (attempts > 0)
                    {
                        incorrect.showAndWait();
                        clearPasswordField();
                    }

                    else
                    {
                        locked.showAndWait();
                        clearFields();
                        disableFields();
                    }
                    return;
                }
            }

            else
            {
                incorrect.showAndWait();
                clearPasswordField();
            }
        }
    }

    @FXML protected void onClearButtonClick()
    {
        // Calling the clearFields function
        clearFields();
    }

    @FXML protected void onCancelButtonClick()
    {
        // Closing the window
        Platform.exit();
    }

    @FXML protected void onCreateAccountButtonClick() throws IOException
    {
        switchToCreateAccount();
    }

    @FXML private void switchToPage() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("page-view.fxml"));
        Stage stage = (Stage) passwordField.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 420, 340);
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();
    }

    @FXML private void switchToCreateAccount() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("create-account-view.fxml"));
        Stage stage = (Stage) passwordField.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 420, 340);
        stage.setTitle("Create Account");
        stage.setScene(scene);
        stage.show();
    }

    @FXML public void setPrompts()
    {
        usernameField.setPromptText("Enter username");
        passwordField.setPromptText("Enter password");
    }

    protected void clearPasswordField()
    {
        // Clearing passwordField by setting its text to ""
        passwordField.setText("");
    }

    protected void clearFields()
    {
        // Clearing usernameField and passwordField by setting their text to ""
        usernameField.setText("");
        passwordField.setText("");
    }

    protected void disableFields()
    {
        // Disabling usernameField and passwordField by preventing them from being edited
        usernameField.setEditable(false);
        passwordField.setEditable(false);
    }

    protected boolean queryUsername(String username)
    {
        try
        {
            Connection connection = DriverManager.getConnection(serverURL, serverUsername, serverPassword);
            String usernameQuery = "SELECT * FROM users2;";
            var statement = connection.prepareStatement(usernameQuery);
            var results = statement.executeQuery();

            while (results.next())
            {
                if (results.getString("Username").toLowerCase().equals(username))
                {
                    return true;
                }
            }

            attempts--;
            return false;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    protected boolean queryPassword(String username, String password)
    {
        try
        {
            Connection connection = DriverManager.getConnection(serverURL, serverUsername, serverPassword);
            String usernameQuery = "SELECT * FROM users2;";
            var statement = connection.prepareStatement(usernameQuery);
            var results = statement.executeQuery();

            while (results.next())
            {
                if (results.getString("Username").toLowerCase().equals(username))
                {
                    if (results.getString("UserPassword").equals(password))
                    {
                        return true;
                    }

                    attempts--;
                    return false;
                }
            }

            attempts--;
            return false;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }
}
