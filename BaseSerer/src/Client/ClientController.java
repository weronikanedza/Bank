package Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientController
{
    private int doubleClick;

    @FXML
    private Button home;
    @FXML
    private Label accNo, balance, errHomePage;
    @FXML
    private Label errDataTransfer, accNoToTran, resourcesTran, infoTran;
    @FXML
    private Label  accNoToTranEnd, resourcesTranEnd, accNoToEnd, amountEnd, transferTitleEnd;
    @FXML
    private TextField accNoTo, amount, amountAfterComma;
    @FXML
    private TextArea transferTitle;
    @FXML
    private PasswordField newPassword, newPasswordRepeat;
    @FXML
    private Label errChangePass;

    @FXML
    private AnchorPane currentPane;
    @FXML
    private AnchorPane greetingPane;
    @FXML
    private AnchorPane transferPane, endTransferPane;
    @FXML
    private AnchorPane homePane;
    @FXML
    private AnchorPane changePasswordPane;

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

    @FXML
    public void handleMainPane()
    {   currentPane.setVisible(false);
        currentPane = homePane;
        currentPane.setVisible(true);

        accNo.setText(client.accNo);
        balance.setText(client.balance);
    }

    @FXML
    public void handleBalanceRefreash() throws IOException
    {
        int errorCode = 1;

        errorCode = client.getBalance();

        if(errorCode == 0)
        {
            balance.setText(client.balance);
            errHomePage.setText("Odświeżno.");
        }
        else
            errHomePage.setText("Wystąpił problem z bazą danych, spróbuj ponowanie za chwile.");

    }

    @FXML
    public void handleTransferPane()
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

            errorCode = client.getBalance();

            if(errorCode != 0)
                infoTran.setText("W tej chwili nie można odświeżyć stanu konta.");
            else
                infoTran.setText("");

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

    @FXML
    public void handleChangePasswordPane()
     {
         currentPane.setVisible(false);
         currentPane = changePasswordPane;
         currentPane.setVisible(true);

         errChangePass.setText("");
         doubleClick = 0;
     }

     @FXML
     public void handleChangePass() throws IOException
     {
         int errorCode = 1;
        if(doubleClick == 1)
        {
            doubleClick = 0;
            errorCode = client.changePassword(newPassword.getText(), newPasswordRepeat.getText());

            if (errorCode == 0)
                handleLogOut();
            else if (errorCode == 1)
                errChangePass.setText("Wystąpił problem z baza danych, spróboj ponownie za chwile.");
            else if (errorCode == 2)
                errChangePass.setText("Wprowadzone hasło nie spenia wymagan:\nHasło musi zawierac co najmniej jedna duża litere oraz cyfre.");
            else if (errorCode == 3)
                errChangePass.setText("Nie poprawnie powtórzono hasło.");
        }
        else
        {
            errChangePass.setText("Kliknij jeszcze raz aby potwierdzić wybór.");
            doubleClick++;
        }

     }
}
