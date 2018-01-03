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
            server.LogOut(userId);
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
        int errorCode = 1;
        Funds toSend = new Funds();
        String receivedErr;

        //check data
        if (!checkData.checkIfOnlyNum(login) || !checkData.checkIfOnlyNum(amount) || amountAfterComma.length() != 2 || !checkData.checkIfOnlyNum(amountAfterComma))
            return "3";


        //Putting everything in to a list S

        toSend.login = login;
        toSend.amount = amount + "." + amountAfterComma;

        //encoding the list S of data
        //TO DO

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO

//---------------------------------poprawne wysylanie------------------------------------------
//        //sending and receiving data to/from main server, interpreting received data all in thread
//        try
//        {
//            received = server.addFunds(toSend);
//        }
//        catch (Exception e)
//        {
//            return errorCode;
//        }

         //testy
        receivedErr = "0";


        //chcek if received if null !!!
        if(receivedErr == null)
            return "1";
//----------------------------------------------------------------------------------------------

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

        //Putting everything in to a list S
        toSend.login = userId;
        toSend.password = newPassword;
        //encoding the list S of data
        //TO DO

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
            received = server.getRequestAddAccount(userId);

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

        //Putting everything in to a list S
        toSend.id_req = idReq;
        toSend.decision = decision;
        System.out.println("add acc decision send : " + decision);
        //encoding data
        //TO DO

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

        return receivedErr;
    }

    /*
* erroCode:
* */
    public List<AddAccountRequest> getListChangePersonalDataReq()
    {
        RequestListAddAccount received;

        //---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
//        try
//        {
//            received = server.getRequestChangePersonalData(userId);
//
//        }
//        catch (Exception e)
//        {
//            return null;
//        }
//
//        if (received.error.equals("1"))
//            return null;
//----------------------------------------------------------------------------------------------

//--------------------------do testowania odbierania danych-------------------------------------------
        received = new RequestListAddAccount();
        List<AddAccountRequest> list = new ArrayList<AddAccountRequest>();
        AddAccountRequest sample = new AddAccountRequest();
        sample.id_request = "1234";
        sample.firstName = "Janusz";
        sample.lastName = "Nędza";
        sample.street = "Jana Pawła II 13c/14";
        sample.zipCode = "37-450";
        sample.city = "Stalowa Wola";
        sample.pesel = "54092356981";
        sample.idNumber = "AZK784512";
        sample.email = "Janusz@gmail.com";
        sample.phoneNumber = "759413682";

        AddAccountRequest sample2 = new AddAccountRequest();
        sample2.id_request = "1234";
        sample2.firstName = "Andrzej";
        sample2.lastName = "Wolak";
        sample2.street = "Wislka II 13c/14";
        sample2.zipCode = "37-450";
        sample2.city = "Nowy Sącz";
        sample2.pesel = "54092356981";
        sample2.idNumber = "AZK784512";
        sample2.email = "WolakA@gmail.com";
        sample2.phoneNumber = "759413682";

        list.add(sample);
        list.add(sample2);

        received.data = list;
//----------------------------------------------------------------------------------------------
        return  received.data;
    }

    /*
* errorCode:
* */
    public String sendChangeDataDecision(String idReq, String decision)
    {
        AddAccReqDecision toSend = new AddAccReqDecision();
        String receivedErr;

        //Putting everything in to a list S
        toSend.id_req = idReq;
        toSend.decision = decision;

        System.out.println("change data decision send : " + decision);

        //encoding data
        //TO DO

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO
//---------------------------------poprawne wysylanie------------------------------------------
//        //sending and receiving data to/from main server, interpreting received data all in thread
//        try
//        {
//            receivedErr = server.answerChangePersonalDataReq(userId, toSend);
//        }
//        catch (Exception e)
//        {
//            return "-1";
//        }
        receivedErr = "0";
//----------------------------------------------------------------------------------------------

        return receivedErr;
    }

    /*
* erroCode:
* */
    public int getListLoanReq()
    {
        int errorCode = -1;
        LogTo toSend = new LogTo();
        LogFrom received;


        //encoding data to send
        //TO DO

        //Pack data to send


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
    public int loanReqDecison()
    {
        int errorCode = -1;
        LogTo toSend = new LogTo();
        LogFrom received;


        //encoding data to send
        //TO DO

        //Pack data to send


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
