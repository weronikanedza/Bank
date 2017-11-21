package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class ControllerClient
{

    private Button btnHome;

    private Button btnBalance;
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
}
