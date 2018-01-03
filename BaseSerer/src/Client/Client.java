package Client;

import Base.*;

public class Client
{

    private CheckData checkData = new CheckData();
    String accNo;
    String userId;
    String balance;
    BaseServerFace server;
    PersonalData personalData;

    public void logOut(){

        try
        {
            server.LogOut(userId); // encoding userId
        }
        catch (Exception e)
        {
            System.out.println("err Log out");
        }

    }

    //----------------------------------------------------------------------------------------------
    //********************************Client's Operations*******************************************
    //----------------------------------------------------------------------------------------------

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
    * errocode:
    * 0 everything ok
    * -1 problem with data base
    * */
   public String getBalance()
   {
       String received;

        try
        {
            received = server.getBalance(userId); //encoding userId
        }
        catch (Exception e)
        {
            return "-1";
        }


       //test
       //received = "23.45";

       //chcek if received if null !!!
       if(received == null)
           return "-1";

       //decoding data
       //TO DO

       if(!received.equals("-1"))
       {
           balance = received;
           return "0";
       }

       return "-1";

   }

    /*
    * errocode:
    * 0 everything ok
    * 1 no resources
    * 2 there is no such account
    * 3 sth wrong with data base
    * 4 sth wrong with base server
    * 5 unsuitable data in fields
    * */
    public String sendTransfer(String accNoTo, String amount, String amountAfterComma, String transferTitle)
    {
        Transfer toSend = new Transfer();
        String receivedErr;

        if (!checkData.checkTransferData(accNoTo, amount, amountAfterComma, transferTitle))
            return "5";


        //Pack and encode data
        toSend.login = userId;
        toSend.accNoFrom = accNo;
        toSend.accNoTo = accNoTo;
        toSend.amount = amount + "." + amountAfterComma;
        toSend.title = transferTitle;

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO

//---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
        try
        {
            receivedErr = server.transfer(toSend);
        }
        catch (Exception e)
        {
            return "4";
        }

        // testy
       // received = "0";


        //chcek if received if null !!!
        if(receivedErr == null)
            return "4";
//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        //end thread

        //can I return sth inside a thread or better outside??
        return receivedErr; // only to tests
    }

    /*
* erroCode:
* PersonalData object   ok
* null   sth wrong with server
* */
    public void getPersonalData()
    {
        PersonalData received;

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO
//---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
//        try
//        {
//            received = server.getPersonalData(userId); //encoding userId
//        }
//        catch (Exception e)
//        {
//
//            System.out.println("Error: " + e);
//            e.printStackTrace();
//            return null;
//        }

        //testy
        PersonalData sample = new PersonalData();
        sample.firstName = "Janusz";
        sample.lastName = "Nędza";
        sample.street = "Jana Pawła II 13c/14";
        sample.zipCode = "37-450";
        sample.city = "Stalowa Wola";
        sample.pesel = "54092356981";
        sample.idNumber = "AZK784512";
        sample.email = "Janusz@gmail.com";
        sample.phoneNumber = "759413682";

        //chcek if received if null !!!
//        if(received == null)
//            return null;

//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        //end thread

        //can I return sth inside a thread or better outside??
        personalData = sample; // zmienic na received przy laczeniu
    }

    /*
* erroCode:
* 1 sth wrong with data base
* 0 ok
* -1 sth wrong with base server
* -2 email != emailRepeated
* -3 unsuitable data in fields
* */
    public String changePersonalData(String name, String lastName, String pesel, String city, String street, String zipCode, String idNumber, String phoneNum, String email, String emailRepeated)
    {

        PersonalData toSend = new PersonalData();
        String received;

        if(!email.equals(emailRepeated))
            return "-2";

        if (!checkData.checkPersonalData(name, lastName, pesel, city, street, zipCode, idNumber, phoneNum, email))
            return "-3";

        //Pack and encode data
        toSend.pesel = pesel;
        toSend.city = city;
        toSend.email = email;
        toSend.firstName = name;
        toSend.idNumber = idNumber;
        toSend.lastName = lastName;
        toSend.phoneNumber = phoneNum;
        toSend.street = street;
        toSend.zipCode = zipCode;

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO
//---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
//        try
//        {
//            received = server.requestChangePersonalData(userId, toSend);
//        }
//        catch (Exception e)
//        {
//
//            System.out.println("Error: " + e);
//            e.printStackTrace();
//            return "-1";
//        }

        received = "0";
        //chcek if received if null !!!
        if(received == null)
            return "-1";

        //decoding data
        //TO DO

        // interpret data

        //end thread

        //can I return sth inside a thread or better outside??
        return received;
    }

    /*
* erroCode:
* */
    public int getInvestmentHistory()
    {
        int errorCode = -1;
        LogTo toSend = new LogTo();
        LogFrom received;

        //Pack and encode data
        //TO DO

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
* erroCode:
* */
    public int getLoanHistory()
    {
        int errorCode = -1;
        LogTo toSend = new LogTo();
        LogFrom received;


        //Pack and encode data
        //TO DO

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
* erroCode:
* */
    public int getTranserHistory()
    {
        int errorCode = -1;
        LogTo toSend = new LogTo();
        LogFrom received;

        //Pack and encode data
        //TO DO

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
* erroCode:
* -2 wrong data
* -1 sth worn with server
* 0 ok
* 1 sth wrong with database
* */
    public String sendReqLoan(String amount, String months, String workPlace, String salary, String instolment)
    {
        Loan toSend = new Loan();
        String received;

        if(!checkData.checkCompanyName(workPlace) || !checkData.checkIfOnlyNum(salary))
            return "-2";

        //Pack and encode data
        toSend.amount = amount;
        toSend.numberOfMonths = months;
        toSend.salary = salary;
        toSend.instalment = instolment;
        toSend.login = userId;

        //checking whether new thread can be created
        //TO DO

        //new thread creating
        //TO DO
//---------------------------------poprawne wysylanie------------------------------------------
        //sending and receiving data to/from main server, interpreting received data all in thread
//        try
//        {
//            server.requestLoan(toSend);
//        }
//        catch (Exception e)
//        {
//
//            System.out.println("Error: " + e);
//            e.printStackTrace();
//            return "-1";
//        }

        received = "0";
        //check if received if null !!!
        if(received == null)
            return "-1";
//----------------------------------------------------------------------------------------------
        //decoding data
        //TO DO

        //end thread

        //can I return sth inside a thread or better outside??
        return received;
    }

    /*
* erroCode:
* */
    public int sendReqInvestment()
    {
        int errorCode = -1;
        LogTo toSend = new LogTo();
        LogFrom received;

        //Pack and encode data
        //TO DO

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
