package Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ResetPassController
{
    private User user;
    @FXML
    private Label frontText, errorText;
    @FXML
    private TextField name, lastName, clientNo;

    @FXML
    private AnchorPane getDataResetPane, resetEndPane;

    public void setController(User user)
    {
        this.user = user;
        getDataResetPane.setVisible(true);
        resetEndPane.setVisible(false);
        errorText.setText("");
    }

    @FXML
    public void handleGetBack() throws IOException
    {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("LoginFX.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) frontText.getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

    @FXML
    public void handleResetPass() throws  IOException
    {
        String error = user.resetPass(clientNo.getText(), name.getText(), lastName.getText());

        if (error.equals("-2"))
            errorText.setText("Wprowadzono nieprawidłowe dane.");
        else if (error.equals("-1"))
            errorText.setText("Nie można połaczyc sie z serwerem!");
        else if (error.equals("0"))
        {
            getDataResetPane.setVisible(false);
            resetEndPane.setVisible(true);
        }
        else if(error.equals("1"))
            errorText.setText("Wystąpił problem z bazą danych, spróbuj ponownie za chwilę.");
        else if(error.equals("2"))
            errorText.setText("Podany nr klienta nie istnieje.");
    }
}
