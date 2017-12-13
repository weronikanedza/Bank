package Client;


import Base.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class User
{

    private CheckData checkData = new CheckData();
    String accNo;
    String userId;
    String balance;
    BaseServerFace server;

    /*
    * errocode:
    * 0 everything ok
    * -1 cannot connect to server
    */
    public int communicateWithServer()
    {
        String url = "rmi://localhost/";

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
        if(communicateWithServer()==-1)
            return errorCode;

        //check whether data is correct
        if (!checkData.checkIfOnlyNum(login) || !checkData.checkPassword(password))
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
        //sending and receiving data to/from main server, interpreting received data all in thread
        try
        {
            received = server.logIn(login, toSend);
            System.out.println(received.login);
        }
        catch (Exception e)
        {

            System.out.println("Error: " + e);
            e.printStackTrace();
            return -1;
        }
        //chcek if received if null !!!

        if(received == null)
            return errorCode;
//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        if(received.error.equals("0"))
        {
            if (received.status.equals("C"))// ok and client
            {
                balance = received.balance;
                accNo = received.accNo;
                errorCode = 0;
            }
            else if (received.status.equals("A"))// ok and admin
                errorCode = 1;

            userId = received.login;
        }
        else if(received.error.equals("1"))// sht wrong
            errorCode=-1;


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
        int errorCode = -4;
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
        toSend.pesel = pesel;
        toSend.city = city;
        toSend.email = email;
        toSend.firstName = name;
        toSend.idNumber = idNumber;
        toSend.lastName = lastName;
        toSend.phoneNumber = phoneNum;
        toSend.street = street;
        toSend.zipCode = zipCode;

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
            received = server.requestAddAccount(userId, toSend);
        }
        catch (Exception e)
        {
            return errorCode;
        }

        //chcek if received if null !!!
        if(received == null)
            return errorCode;
//----------------------------------------------------------------------------------------------

        if(received.equals("0"))
            errorCode = 0;
        else if(received.equals("1"))
            errorCode=-1;

        //end thread

        //can I return sth inside a thread or better outside??
        return errorCode; // only to tests
    }
}
