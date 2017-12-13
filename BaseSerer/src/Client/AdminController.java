package Client;

import Base.AddAccountRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminController
{
    private Admin admin;

    @FXML
    private Button home;
    @FXML
    private Label reqAmountLab, errGetAddAccReg, errAddFunds;
    @FXML
    private TextField addFundsLogin, addFundsAmount, addFundsAmountAfterComma;
    @FXML
    private Label idReq, nameAndLastName, pesel, idNumber, street, city, zipCode, email, phoneNumber, switchReq, errAddAcc;
    @FXML
    private List<AddAccountRequest> listAddAccReq;
    @FXML
    private PasswordField newPassword, newPasswordRepeat;
    @FXML
    private Label errChangePass;

    private int regAmount;
    private int currentReq;
    private int doubleClicks0;
    private int doubleClicks1;


    @FXML
    private AnchorPane currentPane;
    @FXML
    private AnchorPane greetingPane;
    @FXML
    private AnchorPane addAccPane;
    @FXML
    private AnchorPane viewAddAccPane;
    @FXML
    private AnchorPane addFundsPane;
    @FXML
    private AnchorPane changePasswordPane;


    public void setControllerAdmin(Admin admin)
    {
        this.admin = admin;
        currentPane = greetingPane;
        currentPane.setVisible(true);
    }

    @FXML
    public void handleAddFundsPane(){
        currentPane.setVisible(false);
        currentPane = addFundsPane;
        currentPane.setVisible(true);

        errAddFunds.setText("");

    }

    @FXML
    public void handleAddFunds(){

        int errorCode = 1;
        if(doubleClicks0 == 1)
        {
            doubleClicks0 = 0;
            errorCode = admin.addFunds(addFundsLogin.getText(), addFundsAmount.getText(), addFundsAmountAfterComma.getText());

            if (errorCode == 0)
            {
                addFundsLogin.setText("");
                addFundsAmount.setText("");
                addFundsAmountAfterComma.setText("");
                errAddFunds.setText("Dodano środki do konta wybranego klienta");
            }
            else if (errorCode == 1)
                errAddFunds.setText("Wystąpił problem z baza danych, spróboj ponownie za chwile.");
            else if (errorCode == 2)
                errAddFunds.setText("Klient o podanym numerze nie istnieje.");
            else if (errorCode == 3)
                errAddFunds.setText("Wprowadzone dane są niepoprawne");
        }
        else
        {
            errAddFunds.setText("Kliknij jeszcze raz aby potwierdzić wybór.");
            doubleClicks0++;
        }
    }

    @FXML
    public void handleAcceptAddAccReq()
    {
        if(doubleClicks0 == 0)
        {
            errAddAcc.setText("Kliknij, ponownie aby potwierdzić wybór!");
            doubleClicks0++;
        }
        else if (doubleClicks0 == 1)
        {
            errAddAcc.setText("");
            decideAddAccReq("y");
            doubleClicks0 = 0;
        }
    }

    @FXML
    public void handleRefuseAddAccReq()
    {
        if(doubleClicks1 == 0)
        {
            errAddAcc.setText("Kliknij, ponownie aby potwierdzić wybór!");
            doubleClicks1++;
        }
        else if (doubleClicks1 == 1)
        {
            errAddAcc.setText("");
            decideAddAccReq("n");
            doubleClicks1 = 0;
        }
    }

    public void decideAddAccReq(String decision)
    {   int er;


        errAddAcc.setText("Zatwierdzono wybór");
        er = admin.sendAddAccDecision(listAddAccReq.get(currentReq).id_request, decision);

        if (er == -2)
        {
            errAddAcc.setText("Wystąpił bład podczas przetważania wniosku.");
        }
        else if (er == -1)
        {
            errAddAcc.setText("Wystąpił bład podczas wysyłania wniosku.");
        }
        else if (er == 0)
        {
            System.out.println("decyzja : " + decision);
            listAddAccReq.remove(currentReq);
            listAddAccReq = admin.getListAddAccReq();
            regAmount = listAddAccReq.size();
            currentReq = 0;

            if(regAmount == 0)
            {
                currentPane.setVisible(false);
                currentPane = addAccPane;
                currentPane.setVisible(true);
                reqAmountLab.setText("0");
            }
            else
            {
                loadAddAccReqData(currentReq);
            }

        }

    }

    @FXML
    public void handleNextAddAccReq()
    {

        if(currentReq+1 == regAmount)
        {
            switchReq.setText("To juz ostani wniosek.");
        }
        else
        {
            currentReq++;
            loadAddAccReqData(currentReq);
        }
    }

    @FXML
    public void handlePreviousAddAccReq()
    {

        if(currentReq-1 == -1)
        {
            switchReq.setText("To jest pierwszy wniosek na liście.");
        }
        else
        {
            currentReq--;
            loadAddAccReqData(currentReq);
        }
    }

    @FXML
    public void handleViewAddAccReq()
    {
        if(regAmount > 0)
        {
            currentPane.setVisible(false);
            currentPane = viewAddAccPane;
            currentPane.setVisible(true);

            currentReq = 0;
            loadAddAccReqData(currentReq);
        }
        else if(regAmount == 0)
        {
            errGetAddAccReg.setText("Nie ma wniosków do sprawdzenia.");
        }
    }

    @FXML
    public void handleAddAccRequests()
    {
        currentPane.setVisible(false);
        currentPane = addAccPane;
        currentPane.setVisible(true);

        listAddAccReq = admin.getListAddAccReq();

        if(listAddAccReq != null)
        {
            regAmount = listAddAccReq.size();
            currentReq = 0;
            reqAmountLab.setText(regAmount + "");

        }
        else
        {
            errGetAddAccReg.setText("Niestety obecnie nie jest możliwe pobranie wniosków, sprobój ponownie pożniej.");
            regAmount = -1;
        }
    }

    private void loadAddAccReqData(int index)
    {
        AddAccountRequest el= listAddAccReq.get(index);

        idReq.setText(el.id_request);
        nameAndLastName.setText(el.firstName + el.lastName);
        pesel.setText(el.pesel);
        idNumber.setText(el.idNumber);
        street.setText(el.street);
        city.setText(el.city);
        zipCode.setText(el.zipCode);
        email.setText(el.email);
        phoneNumber.setText(el.phoneNumber);
        switchReq.setText("");
        errAddAcc.setText("");

        doubleClicks0 = 0;
        doubleClicks1 = 0;
    }

    @FXML
    public void handleChangePasswordPane()
    {
        currentPane.setVisible(false);
        currentPane = changePasswordPane;
        currentPane.setVisible(true);

        errChangePass.setText("");
        doubleClicks0 = 0;
    }

    @FXML
    public void handleChangePass() throws IOException
    {
        int errorCode = 1;
        if(doubleClicks0 == 1)
        {
            doubleClicks0 = 0;
            errorCode = admin.changePassword(newPassword.getText(), newPasswordRepeat.getText());

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
            doubleClicks0++;
        }

    }

    @FXML
    public void handleLogOut() throws IOException
    {
        //log out server

        Parent homePageParent = FXMLLoader.load(getClass().getResource("LoginFX.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) home.getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }
}
