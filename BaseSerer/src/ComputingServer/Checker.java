package ComputingServer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * additional class with checks requiered data
 * in database ,ComputnigServerImpl uses this
 * class in conditionals
 */
public class Checker {
    private Statement statement=null;
    private ResultSet rS;

    public Checker(Statement statement) {
        this.statement = statement;
    }

    /**
     * check person in AddAccReq
     * @param pesel
     * @ returnn false if someone is enrolled on requwst
     */
    public boolean checkCustomerInAddAccReq (String pesel) {
        try {
            rS=statement.executeQuery("Select * from newaccountrequest where pesel='"+pesel+"'");
            rS.next();
            rS.getString("firstname");
            return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return true;
        }
    }
    /**
     * this function checks if client with given
     * pesel already exist
     * @param pesel
     * @throws Exception
     */
    public boolean checkIfCustomerExist (String pesel){
        try {
            rS=statement.executeQuery("Select * from customers where pesel='"+pesel+"'");
            rS.next();
            rS.getString("firstname");
            return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return true;
        }
    }

    /**
     * function gets balance of client with given number
     * @param accNr
     * @return balance
     */
    public String checkBalance(String accNr){
        try {
            rS=statement.executeQuery("Select * from account where id_account='"+accNr+"'");
            rS.next();
            return rS.getString("balance");
        }catch (Exception e){
            System.out.println("generateAccNr exception");
            System.out.println(e.getMessage());
            return "";
        }
    }

    /**
     * function checks if given account number exists
     * @param accTo
     * @return
     */
    public String findAccount(String accTo){
        try {
            ResultSet rS = statement.executeQuery("Select id_account from account where id_account='"+accTo+"'");
            if( rS.next()) //check rS if it doesn't contain any data then return empty string
                return rS.getString("id_account");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println("findAccount function exception");
            System.out.println(e.getMessage());
        }
        return "";
    }

    public boolean checkAge(String pesel){
        return false;
    }

}
