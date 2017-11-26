package Client;

import Base.BaseServerFace;
import Base.LogTo;
import Base.LogFrom;
import sample.AddAccReqDecision;
import sample.AddAccountRequest;
import sample.PersonalData;
import sample.RequestListAddAccount;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.ArrayList;
import java.util.List;
public class Client
{

    private CheckData checkData = new CheckData();
    String accNo;
    String userId;
    String balance;
    BaseServerFace server;

    public int communicateWithServer()
    {
        // String url = "rmi://217.182.77.242/";
        String url = "rmi://localhost/";
        String name = "maslo";

        try
        {
            Context context = new InitialContext();

            server = (BaseServerFace) context.lookup(url + "BaseServerFace");


        } catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
            //return 0;   // test
            return -1;
        }

        return 0;
    }
    /*
    * erroCode:
    * 1 everything ok admin log in
    * 0 everything ok client log in
    * -1 cannot connect to server
    * -2 unsuitable data in fields
    * */
    public int login(String login, String password)
    {   
        int errorCode = -1;
        LogTo toSend = new LogTo();
        LogFrom received;

        // connect to server
//        if(communicateWithServer()==-1)
//            return errorCode;

        //check whether data is correct
        if (!checkData.checkLoginData(login, password))
        {
            errorCode = -2;
            return errorCode;
        }

        //encoding data to send
        //TO DO

        //Pack data to send
        toSend.login = login;
        toSend.password = password;

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO
//---------------------------------poprawne wysylanie------------------------------------------
//         //sending and receiving data to/from main server, interpreting received data all in thread
//        try
//        {
//            received = server.logIn(login, toSend);
//            System.out.println(received.login);
//        }
//        catch (Exception e)
//        {
//
//            System.out.println("Error: " + e);
//            e.printStackTrace();
//            return -1;
//        }
//         //chcek if received if null !!!
//        if(received == null)
//            return errorCode;
//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

//--------------------------do testowania odbierania danych-------------------------------------------
        received = new LogFrom();
        received.error = "0";
        received.status = "a";
        received.login = "1234";
        received.balance = "1140.56";
        received.accNo  = "15975364820";
////----------------------------------------------------------------------------------------------


        if(received.error.equals("0"))
        {
            if (received.status.equals("c"))// ok and client
            {
                balance = received.balance;
                accNo = received.accNo;
                errorCode = 0;
            }
            else if (received.status.equals("a"))// ok and admin
                errorCode = 1;

            userId = received.login;
        }
        else if(received.error.equals("1"))// sht wrong
            errorCode=-2;

        //end thread

        //can I return sth inside a thread or better outside??
        return errorCode;
    }
    /*
    * errocode:
    * 0 everything ok
    * -1 cannot connect to server
    * -2 email != emailRepeated
    * -3 unsuitable data in fields
    * */
    public int register(String name, String lastName, String pesel, String city, String street, String zipCode, String idNumber, String phoneNum, String email, String emailRepeated)
    {
        int errorCode = -1;
        PersonalData toSend = new PersonalData();
        String received;

        if(!email.equals(emailRepeated))
        {
            errorCode = -2;
            return errorCode;
        }

        if (!checkData.checkPersonalData(name, lastName, pesel, city, street, zipCode, idNumber, phoneNum, email))
        {
            errorCode = -3;
            return errorCode;
        }

        // connect to server
        if(communicateWithServer()==-1)
            return errorCode;

        //Putting everything in to a list S

        //encoding the list S of data
        //TO DO

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO

//---------------------------------poprawne wysylanie------------------------------------------
//         //sending and receiving data to/from main server, interpreting received data all in thread
//        try
//        {
//            received = server.requestAddAccount(userId, toSend);
//        }
//        catch (Exception e)
//        {
//            return -1;
//        }

//         //chcek if received if null !!!
//        if(received == null)
//            return errorCode;
//----------------------------------------------------------------------------------------------

//--------------------------do testowania odbierania danych-------------------------------------------
        received = "0";
//----------------------------------------------------------------------------------------------

        if(received.equals("0"))
            errorCode = 0;
        else if(received.equals("1"))
            errorCode=-2;

        //end thread

        //can I return sth inside a thread or better outside??
        return errorCode; // only to tests
    }
    /*
    *
    * */
    public List<AddAccountRequest> getListAddAccReq()
    {
        RequestListAddAccount received;

//        //---------------------------------poprawne wysylanie------------------------------------------
//         //sending and receiving data to/from main server, interpreting received data all in thread
//        try
//        {
//            received = server.getRequestAddAccount(userId);
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

    public int sendAddAccDecision(String idReq, String decision)
    {
        int errorCode = -1;
        AddAccReqDecision toSend = new AddAccReqDecision();
        String received = "0";

        //Putting everything in to a list S
        toSend.id_req = idReq;
        toSend.decision = decision;

        //encoding the list S of data
        //TO DO

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO
//---------------------------------poprawne wysylanie------------------------------------------
//         //sending and receiving data to/from main server, interpreting received data all in thread
//        try
//        {
//            received = server.answerAddAccountReq(userId, toSend);
//        }
//        catch (Exception e)
//        {
//            return errorCode;
//        }
//----------------------------------------------------------------------------------------------
        if (received.equals("1"))
            errorCode = -2;
        else if (received.equals("0"))
            errorCode = 0;

        return errorCode;
    }
}
