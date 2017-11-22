package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class ControllerClient
{
    @FXML
    private Button home;
    @FXML
    private AnchorPane content;

    public void handleBtnBalance()throws IOException
    {
        content.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("ContentTransferFX.fxml")));

    }
    public void handleBtnHome()throws IOException
    {
        content.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("HomePageClientFX.fxml")));

    }

    @FXML
    public void handleLogOut() throws IOException
    {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("LoginFX.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) home.getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }
}
