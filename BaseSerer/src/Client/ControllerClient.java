package Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerClient
{
    @FXML
    private Button home;
    @FXML
    private Label accNo, balance;
    @FXML
    private Label errDataTransfer, accNoToTran, resourcesTran;
    @FXML
    private Label  accNoToTranEnd, resourcesTranEnd, accNoToEnd, amountEnd, transferTitleEnd;
    @FXML
    private TextField accNoTo, amount, amountAfterComma;
    @FXML
    private TextArea transferTitle;

    @FXML
    private AnchorPane currentPane;
    @FXML
    private AnchorPane greetingPane;
    @FXML
    private AnchorPane transferPane;
    @FXML
    private AnchorPane endTransferPane;
    @FXML
    private AnchorPane homePane;

    private Client client;

    public void setControllerClient(Client client){
        this.client = client;
        currentPane = greetingPane;
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

    // handlers switching mian content
    @FXML
    public void handleMainPage()
    {   currentPane.setVisible(false);
        currentPane = homePane;
        currentPane.setVisible(true);

        accNo.setText(client.accNo);
        balance.setText(client.balance);
    }

    @FXML
    public void handleTransfer()
    {   currentPane.setVisible(false);
        currentPane = transferPane;
        currentPane.setVisible(true);

        accNoToTran.setText(client.accNo);
        resourcesTran.setText(client.balance);
        accNoTo.setText("");
        amount.setText("");
        amountAfterComma.setText("");
        transferTitle.setText("");
        errDataTransfer.setText("");
    }

    @FXML
    public void handleSendTransfer() throws IOException
    {
        int errorCode;

        errorCode = client.sendTransfer(accNoTo.getText(), amount.getText(), amountAfterComma.getText(), transferTitle.getText());

        if (errorCode == 0)
        {
            currentPane.setVisible(false);
            currentPane = endTransferPane;
            currentPane.setVisible(true);

            accNoToTranEnd.setText(client.accNo);
            resourcesTranEnd.setText(client.balance);
            accNoToEnd.setText(accNoTo.getText());
            amountEnd.setText(amount.getText() + "," + amountAfterComma.getText());
            transferTitleEnd.setText(transferTitle.getText());
        }
        else if (errorCode == 1)
            errDataTransfer.setText("Nie posiadasz wystarczających środków na koncie.");
        else if (errorCode == 2)
            errDataTransfer.setText("Podane konto odbiorcy nie istnieje.");
        else if (errorCode == 3)
            errDataTransfer.setText("Wystąpił problem z baza danych, spróboj ponownie za chwile.");
        else if (errorCode == 4)
            errDataTransfer.setText("Wystąpił problem z serwerem, spróboj ponownie za chwile.");
        else if (errorCode == 5)
            errDataTransfer.setText("Sprawdź wprowadzone dane:\n1.	Wszystkie pola są obowiązkowe.\n2.	Numer konta powinien miec 9 cyfr.\n3. 	Kwota może mieć tylko dwie liczby po przecinku.");
    }
}
