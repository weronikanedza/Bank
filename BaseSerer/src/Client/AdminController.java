package Client;

import Base.AddAccountRequest;
import Base.LoanReq;
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
    private Label reqAmountLab, errGetPersonalDataReg, errAddFunds;
    @FXML
    private TextField addFundsLogin, addFundsAmount, addFundsAmountAfterComma;
    @FXML
    private Label personalDataReqTitle, idReq, nameAndLastName, pesel, idNumber, street, city, zipCode, email, phoneNumber, switchReq, errPersonalData;
    @FXML
    private PasswordField newPassword, newPasswordRepeat;
    @FXML
    private Label errChangePass;
    @FXML
    private Label idReqLoan, nameAndLastNameLoan, peselLoan, idNumberLoan, streetLoan, cityLoan, zipCodeLoan, emailLoan, phoneNumberLoan, switchReqLoan, reqAmountLoan, errGetLoanReq, errSendLoanDecision;
    @FXML
    private Label amountLoan, monthsLoan, salaryLoan, rateLoan, instolmentLoan;


    private List<AddAccountRequest> listPersonalDataReq;
    private List<LoanReq> listLoanReq;
    private int regAmount;
    private int currentReq;
    private int doubleClicks0;
    private int doubleClicks1;
    private int newAccReq;


    @FXML
    private AnchorPane currentPane;
    @FXML
    private AnchorPane greetingPane;
    @FXML
    private AnchorPane personalDataReqPane, viewPersonalDataPane;
    @FXML
    private AnchorPane changePasswordPane;
    @FXML
    private AnchorPane addFundsPane;
    @FXML
    private AnchorPane loanReqPane, viewLoanReqPane;


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

        String errorCode;
        if(doubleClicks0 == 1)
        {
            doubleClicks0 = 0;
            errorCode = admin.addFunds(addFundsLogin.getText(), addFundsAmount.getText(), addFundsAmountAfterComma.getText());

            if (errorCode.equals("0"))
            {
                addFundsLogin.setText("");
                addFundsAmount.setText("");
                addFundsAmountAfterComma.setText("");
                errAddFunds.setText("Dodano środki do konta wybranego klienta");
            }
            else if (errorCode.equals("1"))
                errAddFunds.setText("Wystąpił problem z baza danych, spróboj ponownie za chwile.");
            else if (errorCode.equals("2"))
                errAddFunds.setText("Klient o podanym numerze nie istnieje.");
            else if (errorCode.equals("3"))
                errAddFunds.setText("Wprowadzone dane są niepoprawne");
        }
        else
        {
            errAddFunds.setText("Kliknij jeszcze raz aby potwierdzić wybór.");
            doubleClicks0++;
        }
    }

    @FXML
    public void handleAcceptDataReq()
    {
        if(doubleClicks0 == 0)
        {
            errPersonalData.setText("Kliknij, ponownie aby potwierdzić wybór!");
            doubleClicks0++;
            doubleClicks1 = 0;
        }
        else if (doubleClicks0 == 1)
        {
            errPersonalData.setText("");
            decideAddAccReq("y");
            doubleClicks0 = 0;
        }
    }

    @FXML
    public void handleRefuseDataReq()
    {
        if(doubleClicks1 == 0)
        {
            errPersonalData.setText("Kliknij, ponownie aby potwierdzić wybór!");
            doubleClicks1++;
            doubleClicks0 = 0;
        }
        else if (doubleClicks1 == 1)
        {
            errPersonalData.setText("");
            decideAddAccReq("n");
            doubleClicks1 = 0;
        }
    }

    public void decideAddAccReq(String decision)
    {
        String er;

        errPersonalData.setText("Zatwierdzono wybór");

        if(newAccReq == 1)
            er = admin.sendAddAccDecision(listPersonalDataReq.get(currentReq).id_request, decision);
        else
            er = admin.sendChangeDataDecision(listPersonalDataReq.get(currentReq).id_request, decision, listPersonalDataReq.get(currentReq));

        if (er.equals("1"))
        {
            errPersonalData.setText("Wystąpił bład podczas przetważania wniosku.");
        }
        else if (er.equals("-1"))
        {
            errPersonalData.setText("Wystąpił bład podczas wysyłania wniosku.");
        }
        else if (er.equals("0"))
        {
            listPersonalDataReq.remove(currentReq);

            // get list of requests, in case adding new one
            if(newAccReq == 1)
                listPersonalDataReq = admin.getListAddAccReq();
            else
                listPersonalDataReq = admin.getListChangePersonalDataReq();

            if(listPersonalDataReq == null)
            {
                currentPane.setVisible(false);
                currentPane = personalDataReqPane;
                currentPane.setVisible(true);

                errGetPersonalDataReg.setText("Wystąplił problem z probranie nowych wnioskow, spróbuj ponoawnie za chwile.");
                regAmount = -1;
            }

            regAmount = listPersonalDataReq.size();
            currentReq = 0;

            if(regAmount == 0)
            {
                currentPane.setVisible(false);
                currentPane = personalDataReqPane;
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
    public void handleNextPersonalData()
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
    public void handlePreviousPersonalData()
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
    public void handleViewPersonalData()
    {
        if(regAmount > 0)
        {
            currentPane.setVisible(false);
            currentPane = viewPersonalDataPane;
            currentPane.setVisible(true);

            currentReq = 0;
            loadAddAccReqData(currentReq);
        }
        else if(regAmount == 0)
        {
            errGetPersonalDataReg.setText("Nie ma wniosków do sprawdzenia.");
        }
    }

    @FXML
    public void handleGetAddAccRequests()
    {
        currentPane.setVisible(false);
        currentPane = personalDataReqPane;
        currentPane.setVisible(true);

        newAccReq = 1;
        personalDataReqTitle.setText("Wnioski o założenie konta");
        errGetPersonalDataReg.setText("");

        listPersonalDataReq = admin.getListAddAccReq();

        if(listPersonalDataReq != null)
        {
            regAmount = listPersonalDataReq.size();
            currentReq = 0;
            reqAmountLab.setText(regAmount + "");

        }
        else
        {
            errGetPersonalDataReg.setText("Niestety obecnie nie jest możliwe pobranie wniosków, sprobój ponownie pożniej.");
            regAmount = -1;
        }
    }

    @FXML
    public void handleGetChangePersonalDataRequests()
    {
        currentPane.setVisible(false);
        currentPane = personalDataReqPane;
        currentPane.setVisible(true);

        newAccReq = 0;
        personalDataReqTitle.setText("Wnioski o zmianę danych osobowych");
        errGetPersonalDataReg.setText("");

        listPersonalDataReq = admin.getListChangePersonalDataReq();

        if(listPersonalDataReq != null)
        {
            regAmount = listPersonalDataReq.size();
            currentReq = 0;
            reqAmountLab.setText(regAmount + "");

        }
        else
        {
            errGetPersonalDataReg.setText("Niestety obecnie nie jest możliwe pobranie wniosków, sprobój ponownie pożniej.");
            regAmount = -1;
        }
    }

    private void loadAddAccReqData(int index)
    {
        AddAccountRequest el= listPersonalDataReq.get(index);

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
        errPersonalData.setText("");

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
        String errorCode;
        if(doubleClicks0 == 1)
        {
            doubleClicks0 = 0;
            System.out.println(newPassword.getText());
            errorCode = admin.changePassword(newPassword.getText(), newPasswordRepeat.getText());

            if (errorCode.equals("0"))
                handleLogOut();
            else if (errorCode.equals("1"))
                errChangePass.setText("Wystąpił problem z baza danych, spróboj ponownie za chwile.");
            else if (errorCode.equals("2"))
                errChangePass.setText("Wprowadzone hasło nie spenia wymagan:\nHasło musi zawierac co najmniej jedna duża litere oraz cyfre.");
            else if (errorCode.equals("3"))
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
        admin.logOut();

        Parent homePageParent = FXMLLoader.load(getClass().getResource("LoginFX.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) home.getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

    @FXML
    public void handleGetLoanReq()
    {
        currentPane.setVisible(false);
        currentPane = loanReqPane;
        currentPane.setVisible(true);

        errGetLoanReq.setText("");

        listLoanReq = admin.getListLoanReq();

        if(listLoanReq != null)
        {
            regAmount = listLoanReq.size();
            currentReq = 0;
            reqAmountLoan.setText(regAmount + "");

        }
        else
        {
            errGetLoanReq.setText("Niestety obecnie nie jest możliwe pobranie wniosków, sprobój ponownie pożniej.");
            regAmount = -1;
        }
    }

    private void loadLoanReq(int index)
    {
        LoanReq el= listLoanReq.get(index);

        idReqLoan.setText(el.id_req);
        nameAndLastNameLoan.setText(el.personalData.firstName + el.personalData.lastName);
        peselLoan.setText(el.personalData.pesel);
        idNumberLoan.setText(el.personalData.idNumber);
        streetLoan.setText(el.personalData.street);
        cityLoan.setText(el.personalData.city);
        zipCodeLoan.setText(el.personalData.zipCode);
        emailLoan.setText(el.personalData.email);
        phoneNumberLoan.setText(el.personalData.phoneNumber);

        amountLoan.setText(el.amount);
        monthsLoan.setText(el.numberOfMonths);
        salaryLoan.setText(el.salary);
        rateLoan.setText(el.bankRate);
        instolmentLoan.setText(el.instalment);

        switchReqLoan.setText("");
        errSendLoanDecision.setText("");

        doubleClicks0 = 0;
        doubleClicks1 = 0;
    }

    @FXML
    public void handleViewLoanReq()
    {
        if(regAmount > 0)
        {
            currentPane.setVisible(false);
            currentPane = viewLoanReqPane;
            currentPane.setVisible(true);

            currentReq = 0;
            loadLoanReq(currentReq);
        }
        else if(regAmount == 0)
        {
            errGetLoanReq.setText("Nie ma wniosków do sprawdzenia.");
        }
    }

    @FXML
    public void handleNextLoanReq()
    {

        if(currentReq+1 == regAmount)
        {
            switchReqLoan.setText("To juz ostani wniosek.");
        }
        else
        {
            currentReq++;
            loadLoanReq(currentReq);
        }
    }

    @FXML
    public void handlePreviousLoanReq()
    {

        if(currentReq-1 == -1)
        {
            switchReqLoan.setText("To jest pierwszy wniosek na liście.");
        }
        else
        {
            currentReq--;
            loadLoanReq(currentReq);
        }
    }

    @FXML
    public void handleAcceptLoanReq()
    {
        if(doubleClicks0 == 0)
        {
            errSendLoanDecision.setText("Kliknij, ponownie aby potwierdzić wybór!");
            doubleClicks0++;
            doubleClicks1 = 0;
        }
        else if (doubleClicks0 == 1)
        {
            errSendLoanDecision.setText("");
            decideLoanReq("y");
            doubleClicks0 = 0;
        }
    }

    @FXML
    public void handleRefuseLoanReq()
    {
        if(doubleClicks1 == 0)
        {
            errSendLoanDecision.setText("Kliknij, ponownie aby potwierdzić wybór!");
            doubleClicks1++;
            doubleClicks0 = 0;
        }
        else if (doubleClicks1 == 1)
        {
            errSendLoanDecision.setText("");
            decideLoanReq("n");
            doubleClicks1 = 0;
        }
    }

    public void decideLoanReq(String decision)
    {
        String er;

        er = admin.sendLoanReqDecision(listLoanReq.get(currentReq).id_req, decision);


        if (er.equals("1"))
        {
            errSendLoanDecision.setText("Wystąpił bład podczas przetważania wniosku.");
        }
        else if (er.equals("-1"))
        {
            errSendLoanDecision.setText("Wystąpił bład podczas wysyłania wniosku.");
        }
        else if (er.equals("0"))
        {
            listLoanReq.remove(currentReq);

            listLoanReq = admin.getListLoanReq();

            if(listLoanReq == null)
            {
                currentPane.setVisible(false);
                currentPane = personalDataReqPane;
                currentPane.setVisible(true);

                errGetLoanReq.setText("Wystąplił problem z probranie nowych wnioskow, spróbuj ponoawnie za chwile.");
                regAmount = -1;
            }

            regAmount = listLoanReq.size();
            currentReq = 0;

            if(regAmount == 0)
            {
                currentPane.setVisible(false);
                currentPane = loanReqPane;
                currentPane.setVisible(true);
                reqAmountLoan.setText("0");
            }
            else
            {
                loadLoanReq(currentReq);
            }

        }

    }
}
