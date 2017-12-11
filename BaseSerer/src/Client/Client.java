package Client;

import Base.BaseServerFace;
import Base.LogTo;
import Base.Transfer;

public class Client
{

    private CheckData checkData = new CheckData();
    String accNo;
    String userId;
    String balance;
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
    //********************************Client's Operations*******************************************
    //----------------------------------------------------------------------------------------------

    /*
* errocode:
* 0 everything ok
* 1 sth wrong with base server
* 2 unsuitable data in field
* 3 not equal password and repeated password
* */
    public int changePassword( String newPassword, String newPasswordRepeat)
    {
        int errorCode = 1;
        LogTo toSend = new LogTo();
        String received;

        if(!newPassword.equals(newPasswordRepeat))
        {
            errorCode = 3;
            return errorCode;
        }

        if (!checkData.checkPassword(newPassword))
        {
            errorCode = 2;
            return errorCode;
        }

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
            received = server.changePassword(toSend);
        }
        catch (Exception e)
        {
            return errorCode;
        }

        // testy
        //received = "0";


        //chcek if received if null !!!
        if(received == null)
            return errorCode;
//----------------------------------------------------------------------------------------------

        if(received.equals("0"))
            errorCode = 0;
        else if(received.equals("1"))
            errorCode = 1;
        //end thread

        //can I return sth inside a thread or better outside??
        return errorCode; // only to tests
    }
    /*
    * errocode:
    * 0 everything ok
    * -1 problem with data base
    * */
   public int getBalance()
   {
       int errorCode = -1;
       String received;

        try
        {
            received = server.getBalance(userId);
        }
        catch (Exception e)
        {
            return errorCode;
        }


       //test
       //received = "23.45";

       //chcek if received if null !!!
       if(received == null)
           return errorCode;

       if(received.equals("-1"))
           errorCode = -1;
       else
       {
           balance = received;
           errorCode = 0;
       }

       return errorCode;

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
    public int sendTransfer(String accNoTo, String amount, String amountAfterComma, String transferTitle)
    {
        int errorCode = 4;
        Transfer toSend = new Transfer();
        String received;

        if (!checkData.checkTransferData(accNoTo, amount, amountAfterComma, transferTitle))
        {
            errorCode = 5;
            return errorCode;
        }

        //Putting everything in to a list S
        toSend.login = userId;
        toSend.accNoFrom = accNo;
        toSend.accNoTo = accNoTo;
        toSend.amount = amount + "." + amountAfterComma; // czo ma byc . czy ,
        toSend.title = transferTitle;
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
            received = server.transfer(toSend);
        }
        catch (Exception e)
        {
            return errorCode;
        }

        // testy
       // received = "0";


        //chcek if received if null !!!
        if(received == null)
            return errorCode;
//----------------------------------------------------------------------------------------------

        if(received.equals("0"))
            errorCode = 0;
        else if(received.equals("1"))
            errorCode = 1;
        else if(received.equals("2"))
            errorCode = 2;
        else if(received.equals("3"))
            errorCode = 3;
        //end thread

        //can I return sth inside a thread or better outside??
        return errorCode; // only to tests
    }

    //----------------------------------------------------------------------------------------------
    //**********************************************************************************************
    //----------------------------------------------------------------------------------------------

}
