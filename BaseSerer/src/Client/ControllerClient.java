package Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerClient
{
    @FXML
    private Button home;
    @FXML
    private Label accNo;
    @FXML
    private Label balance;

    @FXML
    private AnchorPane currentPane;
    @FXML
    private AnchorPane greetingPane;
    @FXML
    private AnchorPane homePane;

    private Client client;

    public void setControllerClient(Client client){
        this.client = client;
        currentPane = greetingPane;
        homePane.setVisible(false);
    }

    public void handleBtnBalance()throws IOException
    {

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
    @FXML
    public void handleMainPage()
    {   currentPane.setVisible(false);
        currentPane = homePane;
        currentPane.setVisible(true);

        accNo.setText(client.accNo);
        balance.setText(client.balance);
    }
}
