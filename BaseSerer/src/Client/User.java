package Client;

import Base.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.security.NoSuchAlgorithmException;

public class User
{

    private CheckData checkData = new CheckData();
    public String accNo;
    public String userId;
    public String balance;
    public BaseServerFace server;

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
    * -3 account is banned
    * */ // czy dostaje null po polaczaeniu
    public String login(String login, String password) throws Exception
    {
        LogTo toSend = new LogTo();
        LogFrom received;

        // connect to server
        if(communicateWithServer()==-1)
            return "-1";

        //check whether data is correct
        if (!checkData.checkIfOnlyNum(login))
            return "-2";


        //Pack and encode data
        byte[] key = {-120,17,42,121,-12,1,6,34}
        SecretKey secretKey = new SecretKeySpec(key,"DES");
        DesEncrypter encrypter = new DesEncrypter(secretKey);

        String encrypted_login = encrypter.encrypt(login);
        String encrypted_password = encrypter.encrypt(password);

        toSend.login = encrypted_login;
        toSend.password = encrypted_password;

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
            return "-1";
        }
        //chcek if received if null !!!

        if(received == null)
            return "-1";
//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        if(received.error.equals("0"))
        {
            if (received.status.equals("C"))// ok and client
            {
                balance = received.balance;
                accNo = received.accNo;
                userId = received.login;
                return "0";
            }
            else if (received.status.equals("A"))// ok and admin
            {
                userId = received.login;
                return "1";
            }


        }
        else if(received.error.equals("1"))// sht wrong
            return "-1";
        else if(received.error.equals("2"))// banned
            return "-3";


        //end thread

        //can I return sth inside a thread or better outside??
        return "-1";
    }

    /*
    * errocode:
    * 1 request not accepted
    * 0 everything ok
    * -2 email != emailRepeated
    * -3 unsuitable data in fields
    * -4 cannot connect to server
    * */
    public String register(String name, String lastName, String pesel, String city, String street, String zipCode, String idNumber, String phoneNum, String email, String emailRepeated) throws Exception
    {
        PersonalData toSend = new PersonalData();
        String receivedErr;

        if(!email.equals(emailRepeated))
            return "-2";

        if (!checkData.checkPersonalData(name, lastName, pesel, city, street, zipCode, idNumber, phoneNum, email))
            return "-3";

        // connect to server
        if(communicateWithServer()==-1)
            return "-4";

        byte[] key = {-120,17,42,121,-12,1,6,34}
        SecretKey secretKey = new SecretKeySpec(key,"DES");
        DesEncrypter encrypter = new DesEncrypter(secretKey);

        String encrypted_pesel = encrypter.encrypt(pesel);
        String encrypted_city = encrypter.encrypt(city);
        String encrypted_email = encrypter.encrypt(email);
        String encrypted_firstName = encrypter.encrypt(name);
        String encrypted_idNumber = encrypter.encrypt(idNumber);
        String encrypted_lastName = encrypter.encrypt(lastName);
        String encrypted_phoneNumber = encrypter.encrypt(phoneNum);
        String encrypted_street = encrypter.encrypt(street);
        String encrypted_zipCode = encrypter.encrypt(zipCode);



        //Pack and encode data
        toSend.pesel = encrypted_pesel;
        toSend.city = encrypted_city;
        toSend.email = encrypted_email;
        toSend.firstName = encrypted_firstName;
        toSend.idNumber = encrypted_idNumber;
        toSend.lastName = encrypted_lastName;
        toSend.phoneNumber = encrypted_phoneNumber;
        toSend.street = encrypted_street;
        toSend.zipCode = encrypted_zipCode;

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO

//---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
        try
        {
            receivedErr = server.requestAddAccount(userId, toSend);
        }
        catch (Exception e)
        {
            return "-4";
        }

        //chcek if received if null !!!
        if(receivedErr == null)
            return "-4";
//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        //end thread

        //can I return sth inside a thread or better outside??
        return receivedErr; // only to tests
    }

    /*
    * erroCode:
    * -2 unsuitable data in fields
    * -1 cannot connect to server
    * 0 ok
    * 1 sht wrong with data base
    * 2 no such client
    * */
    public String resetPass(String clientNo, String name, String lastName)
    {
        String receivedErr;

        // connect to server
        if(communicateWithServer()==-1)
            return "-1";

        //check whether data is correct
        if(!checkData.checkIfOnlyNum(clientNo) || !checkData.checkIfOnlyChars(name) || !checkData.checkIfOnlyChars(lastName))
            return "-2";

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO
//---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
//        try
//        {
//           receivedErr = server.restartPassword(clientNo); // encoding clientNo
//        }
//        catch (Exception e)
//        {
//
//            System.out.println("Error: " + e);
//            e.printStackTrace();
//            return "-1";
//        }

        receivedErr = "0";

        //chcek if received if null !!!
        if(receivedErr == null)
            return "-1";
//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        //end thread

        //can I return sth inside a thread or better outside??
        return receivedErr;
    }
}
