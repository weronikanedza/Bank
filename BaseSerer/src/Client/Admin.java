package Client;

import Base.*;

import java.util.ArrayList;
import java.util.List;

public class Admin
{
    private CheckData checkData = new CheckData();
    String userId;
    BaseServerFace server;

    public void logOut(){

        try
        {
            server.LogOut(userId); //encoding userId
        }
        catch (Exception e)
        {
            System.out.println("err Log out");
        }

    }

    //----------------------------------------------------------------------------------------------
    //***********************8*********Admin's Operations*******************************************
    //----------------------------------------------------------------------------------------------
/* errocode:
* 0 everything ok
* 1 sth wrong with base server
* 2 there is no such client
* 3 unsuitable data in field
* */
    public String addFunds(String login, String amount, String amountAfterComma)
    {
        Funds toSend = new Funds();
        String receivedErr;

        //check data
        if (!checkData.checkIfOnlyNum(login) || !checkData.checkIfOnlyNum(amount) || amountAfterComma.length() != 2 || !checkData.checkIfOnlyNum(amountAfterComma))
            return "3";


        //Pack and encode data
        toSend.login = login;
        toSend.amount = amount + "." + amountAfterComma;

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO

//---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
        try
        {
            receivedErr = server.addFunds(userId, toSend);
        }
        catch (Exception e)
        {
            return "-1";
        }

        //chcek if received if null !!!
        if(receivedErr == null)
            return "1";
//----------------------------------------------------------------------------------------------
        //decoding?

        //end thread

        //can I return sth inside a thread or better outside??
        return receivedErr; // only to tests

    }

    /*
* errocode:
* 0 everything ok
* 1 sth wrong with base server
* 2 unsuitable data in field
* 3 not equal password and repeated password
* */
    public String changePassword( String newPassword, String newPasswordRepeat)
    {
        LogTo toSend = new LogTo();
        String receivedErr;

        if(!newPassword.equals(newPasswordRepeat))
           return "3";

        if (!checkData.checkPassword(newPassword))
            return "2";

        //Pack and encode data
        toSend.login = userId;
        toSend.password = newPassword;

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO

//---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
        try
        {
            receivedErr = server.changePassword(toSend);
        }
        catch (Exception e)
        {
            return "1";
        }

        // testy
        //received = "0";


        //chcek if received if null !!!
        if(receivedErr == null)
            return "1";
//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        //end thread

        //can I return sth inside a thread or better outside??
        return receivedErr; // only to tests
    }

    /*
    * errorCode
    * 0 ok
    * 1 sth wrong with data base
    * */
    public List<AddAccountRequest> getListAddAccReq()
    {
        RequestListAddAccount received;

        //---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
        try
        {
            received = server.getRequestAddAccount(userId); // encoding userId
        }
        catch (Exception e)
        {
            return null;
        }

        if (received.error.equals("1"))
            return null;
//----------------------------------------------------------------------------------------------

//--------------------------do testowania odbierania danych-------------------------------------------
//        received = new RequestListAddAccount();
//        List<AddAccountRequest> list = new ArrayList<AddAccountRequest>();
//        AddAccountRequest sample = new AddAccountRequest();
//        sample.id_request = "1234";
//        sample.firstName = "Janusz";
//        sample.lastName = "Nędza";
//        sample.street = "Jana Pawła II 13c/14";
//        sample.zipCode = "37-450";
//        sample.city = "Stalowa Wola";
//        sample.pesel = "54092356981";
//        sample.idNumber = "AZK784512";
//        sample.email = "Janusz@gmail.com";
//        sample.phoneNumber = "759413682";
//
//        AddAccountRequest sample2 = new AddAccountRequest();
//        sample2.id_request = "1234";
//        sample2.firstName = "Andrzej";
//        sample2.lastName = "Wolak";
//        sample2.street = "Wislka II 13c/14";
//        sample2.zipCode = "37-450";
//        sample2.city = "Nowy Sącz";
//        sample2.pesel = "54092356981";
//        sample2.idNumber = "AZK784512";
//        sample2.email = "WolakA@gmail.com";
//        sample2.phoneNumber = "759413682";
//
//        list.add(sample);
//        list.add(sample2);
//
//        received.data = list;
//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        return  received.data;
    }

    /*
    * errorCode
    * -1 sth wrong with sending req
    * 0 ok
    * 1 sth wrong with data base
    * */
    public String sendAddAccDecision(String idReq, String decision)
    {
        AddAccReqDecision toSend = new AddAccReqDecision();
        String receivedErr;

        //Pack and encode data
        toSend.id_req = idReq;
        toSend.decision = decision;

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO
//---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
        try
        {
            receivedErr = server.answerAddAccountReq(userId, toSend);
        }
        catch (Exception e)
        {
            return "-1";
        }

//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        return receivedErr;
    }

    /*
    * errorCode
    * 0 ok
    * 1 sth wrong with data base
    * */
    public List<AddAccountRequest> getListChangePersonalDataReq()
    {
        RequestListAddAccount received;

        //---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
        try
        {
            received = server.getRequestChangePersonalData(userId); // encoding userId

        }
        catch (Exception e)
        {
            return null;
        }

        if (received.error.equals("1"))
            return null;
//----------------------------------------------------------------------------------------------

//--------------------------do testowania odbierania danych-------------------------------------------
//        received = new RequestListAddAccount();
//        List<AddAccountRequest> list = new ArrayList<AddAccountRequest>();
//        AddAccountRequest sample = new AddAccountRequest();
//        sample.id_request = "1234";
//        sample.firstName = "Janusz";
//        sample.lastName = "Nędza";
//        sample.street = "Jana Pawła II 13c/14";
//        sample.zipCode = "37-450";
//        sample.city = "Stalowa Wola";
//        sample.pesel = "54092356981";
//        sample.idNumber = "AZK784512";
//        sample.email = "Janusz@gmail.com";
//        sample.phoneNumber = "759413682";
//
//        AddAccountRequest sample2 = new AddAccountRequest();
//        sample2.id_request = "1234";
//        sample2.firstName = "Andrzej";
//        sample2.lastName = "Wolak";
//        sample2.street = "Wislka II 13c/14";
//        sample2.zipCode = "37-450";
//        sample2.city = "Nowy Sącz";
//        sample2.pesel = "54092356981";
//        sample2.idNumber = "AZK784512";
//        sample2.email = "WolakA@gmail.com";
//        sample2.phoneNumber = "759413682";
//
//        list.add(sample);
//        list.add(sample2);
//
//        received.data = list;
//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        return  received.data;
    }

    /*
    * errorCode:
    * -1 sth wrong with sending req
    * 0 ok
    * 1 sth wrong with data base
    * */
    public String sendChangeDataDecision(String idReq, String decision, PersonalData data)
    {
        AddAccReqDecision toSend = new AddAccReqDecision();
        String receivedErr;

        //Pack and encode data
        toSend.id_req = idReq;
        toSend.decision = decision;
        toSend.personalData = data;


        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO
//---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
        try
        {
            receivedErr = server.answerChangePersonalDataReq(userId, toSend);
        }
        catch (Exception e)
        {
            return "-1";
        }

//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        return receivedErr;
    }

    /*
    * erroCode:
    * 0 ok
    * 1 sth wrong with data base
    * */
    public List<LoanReq> getListLoanReq()
    {
        ListLoanReq received;

        //---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
        try
        {
            received = server.getRequestLoan(userId); // encoding userId
        }
        catch (Exception e)
        {
            return null;
        }

        if (received.error.equals("1"))
            return null;
//----------------------------------------------------------------------------------------------

//--------------------------do testowania odbierania danych-------------------------------------------
//        received = new ListLoanReq();
//        List<LoanReq> list = new ArrayList<LoanReq>();
//        LoanReq loan = new LoanReq();
//        PersonalData sample = new PersonalData();
//        loan.id_req = "1234";
//        loan.amount = "25 000";
//        loan.numberOfMonths = "12";
//        loan.salary = "3300";
//        loan.bankRate = "5%";
//        loan.instalment = "1345";
//        sample.firstName = "Janusz";
//        sample.lastName = "Nędza";
//        sample.street = "Jana Pawła II 13c/14";
//        sample.zipCode = "37-450";
//        sample.city = "Stalowa Wola";
//        sample.pesel = "54092356981";
//        sample.idNumber = "AZK784512";
//        sample.email = "Janusz@gmail.com";
//        sample.phoneNumber = "759413682";
//        loan.personalData = sample;
//
//        LoanReq loan1 = new LoanReq();
//        PersonalData sample2 = new PersonalData();
//
//        loan1.id_req = "1234";
//        loan1.amount = "25 000";
//        loan1.numberOfMonths = "12";
//        loan1.salary = "3300";
//        loan1.bankRate = "5%";
//        loan1.instalment = "1345";
//        sample2.firstName = "Andrzej";
//        sample2.lastName = "Wolak";
//        sample2.street = "Wislka II 13c/14";
//        sample2.zipCode = "37-450";
//        sample2.city = "Nowy Sącz";
//        sample2.pesel = "54092356981";
//        sample2.idNumber = "AZK784512";
//        sample2.email = "WolakA@gmail.com";
//        sample2.phoneNumber = "759413682";
//        loan1.personalData = sample2;
//
//        list.add(loan);
//        list.add(loan1);
//
//
//        received.loanList = list;
//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        return  received.loanList;
    }

    /*
    * errorCode:
    * -1 sth wrong with sending req
    * 0 ok
    * 1 sth wrong with data base
    * */
    public String sendLoanReqDecision(String idReq, String decision)
    {
        LoanDecision toSend = new LoanDecision();
        String receivedErr;

        //Pack and encode data
        toSend.id_req = idReq;
        toSend.decision = decision;

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO
//---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
        try
        {
            receivedErr = server.answerLoanReq(userId, toSend);
        }
        catch (Exception e)
        {
            return "-1";
        }
//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        return receivedErr;
    }

    /*
* errorCode:
* */
    public int unlockAcc()
    {
        int errorCode = -1;
        LogTo toSend = new LogTo();
        LogFrom received;

        //Pack and encode data
        // TO DO

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO
//---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
        try
        {

        }
        catch (Exception e)
        {

            System.out.println("Error: " + e);
            e.printStackTrace();
            return -1;
        }
        //chcek if received if null !!!

//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        // interpret data

        //end thread

        //can I return sth inside a thread or better outside??
        return errorCode;
    }

    /*
* errorCode:
* */
    public int deleteAcc()
    {
        int errorCode = -1;
        LogTo toSend = new LogTo();
        LogFrom received;

        //Pack and encode data
        // TO DO

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO
//---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
        try
        {

        }
        catch (Exception e)
        {

            System.out.println("Error: " + e);
            e.printStackTrace();
            return -1;
        }
        //chcek if received if null !!!

//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        // interpret data

        //end thread

        //can I return sth inside a thread or better outside??
        return errorCode;
    }
    //----------------------------------------------------------------------------------------------
    //**********************************************************************************************
    //----------------------------------------------------------------------------------------------

}
