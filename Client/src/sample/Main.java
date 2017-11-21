package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

public class Main extends Application {

    Client client = new Client();

    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Label labLoginProblem;


    @FXML
    public void handleBtnLogin() throws IOException
    {
        int errorCode;

        errorCode = client.login(login.getText(), password.getText());
        //admin
        if (errorCode==1)
        {   labLoginProblem.setText("Zalogowany!");
            //changing scene for admin
            Parent homePageParent = FXMLLoader.load(getClass().getResource("HomePageAdminFX.fxml"));
            Scene homePageScene = new Scene(homePageParent);
            Stage appStage = (Stage) labLoginProblem.getScene().getWindow();
            appStage.setScene(homePageScene);
            appStage.show();

        }else if (errorCode==0)
        {
            labLoginProblem.setText("Zalogowany!");
            //changing scene for client
            Parent homePageParent = FXMLLoader.load(getClass().getResource("HomePageClientFX.fxml"));
            Scene homePageScene = new Scene(homePageParent);
            Stage appStage = (Stage) labLoginProblem.getScene().getWindow();
            appStage.setScene(homePageScene);
            appStage.show();

        }else if (errorCode==-1)
            labLoginProblem.setText("Nie można połaczyc sie z serwerem!");
        else if (errorCode== -2)
            labLoginProblem.setText("Niepoprawny login lub hasło!");
    }
    @FXML
    public void handleBtnRegister() throws IOException
    {
            Parent homePageParent = FXMLLoader.load(getClass().getResource("RegisterFX.fxml"));
            Scene homePageScene = new Scene(homePageParent);
            Stage appStage = (Stage) labLoginProblem.getScene().getWindow();
            appStage.setScene(homePageScene);
            appStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Twój Hajs");
        primaryStage.setScene(new Scene(root, 600, 550));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
