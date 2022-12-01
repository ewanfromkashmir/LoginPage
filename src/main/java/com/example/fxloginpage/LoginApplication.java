package com.example.fxloginpage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginApplication extends Application
{
//    int attempts = 3;

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 340);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }

//    public boolean checkCredentials(String user, String password)
//    {
//        if (user.equalsIgnoreCase("admin"))
//        {
//            if (password.equals("PaSSword!!!"))
//            {
//                return true;
//            }
//
//            else
//            {
//                attempts--;
//                return false;
//            }
//        }
//
//        else
//        {
//            return false;
//        }
//    }
}