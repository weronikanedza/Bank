package Client;

import Base.AddAccountRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label reqAmountLab, errGetAddAccReg;
    @FXML
    private Label idReq, nameAndLastName, pesel, idNumber, street, city, zipCode, email, phoneNumber, switchReq, doubleClick;
    private List<AddAccountRequest> listAddAccReq;
    private int regAmount;
    private int currentReq;
    private int clicks;


    @FXML
    private AnchorPane currentPane;
    @FXML
    private AnchorPane greetingPane;
    @FXML
    private AnchorPane addAccPane;
    @FXML
    private AnchorPane viewAddAccPane;

    public void setControllerAdmin(Admin admin)
    {
        this.admin = admin;
        currentPane = greetingPane;
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
        doubleClick.setText("");

        clicks = 0;
    }

    @FXML
    public void handleAcceptAddAccReq()
    {
     decideAddAccReq("y");
    }

    @FXML
    public void handleRefuseAddAccReq()
    {
        decideAddAccReq("n");
    }

    public void decideAddAccReq(String decision)
    {   int er;

        if(clicks == 0)
        {
            clicks++;
            doubleClick.setText("Kliknij jeszcze raz aby potwierdzić wybór");
        }
        else if(clicks == 1)
        {   doubleClick.setText("Zatwierdzono wybór");
            er = admin.sendAddAccDecision(listAddAccReq.get(currentReq).id_request, decision);

            if (er == -2)
            {
            doubleClick.setText("Wystąpił bład podczas przetważania wniosku.");
            }
            else if (er == -1)
            {
                doubleClick.setText("Wystąpił bład podczas wysyłania wniosku.");
            }
            else if (er == 0)
            {
                System.out.println("decuzja : " + decision);
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
    }

    @FXML
    public void handleNextAddAccReq(){

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
    public void handlePreviousAddAccReq(){

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
    public void handleViewAddAccReq(){
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
