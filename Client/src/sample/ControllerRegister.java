package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerRegister
{
    @FXML
    Label frontText;



    @FXML
    public void handleGetBack() throws IOException
    {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("LoginFX.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) frontText.getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

    public void handleSendRequest() throws IOException
    {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("EndRegisterFX.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) frontText.getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }
}
