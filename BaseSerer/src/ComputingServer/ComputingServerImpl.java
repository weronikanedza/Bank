package ComputingServer;

import Base.BaseServerFace;
import Base.LogFrom;
import Base.LogTo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class ComputingServerImpl
    extends UnicastRemoteObject
    implements BaseServerFace
{

    final private String URL="jdbc:mysql://localhost:3306/bazadb";
    private Connection connection=null;
    private Statement statement=null;
    private ResultSet rS=null;

    public ComputingServerImpl() throws RemoteException, SQLException {
        super();
        try {
            connection = DriverManager.getConnection(URL,"root","");
            statement=connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }


    @Override
    public LogFrom logIn(String login, LogTo data) throws RemoteException
    {
        System.out.println("LOGIN");
        LogFrom logFrom=new LogFrom();
        try {
            rS= statement.executeQuery("Select password,status from users where login='"+data.login+"' and password='"+data.password+"'" );
            rS.next();
            if(data.password.equals(rS.getString("password"))){ //checks password capital/small letters
                logFrom.error="0";
                logFrom.status=rS.getString("status");
                logFrom.login=data.login;
                System.out.println(logFrom.login);

                if(logFrom.status.equals("C") ) { //add balance if client is a user
                    rS=statement.executeQuery("Select balance,id_account from account natural join customers where customer_nr='"+data.login+"'");
                    rS.next();
                    logFrom.balance= rS.getString("balance");
                    logFrom.accNo= rS.getString("id_account");
                }

            }else throw new Exception();
        } catch(SQLException e){
            System.out.println("resultset exception");
            System.out.println(e.getMessage());
            logFrom.error="1";
        } catch (Exception e){
            System.out.println("There is no that person in database");
            logFrom.error="1";
        }
        return logFrom;
    }

    @Override
    public Object restartPassword(String login, Object data) throws RemoteException
    {
        return null;
    }

    @Override
    public Object transfer(String login, String accFrom, String accTo, Object data) throws RemoteException
    {
        return null;
    }

    @Override
    public Object changePassword(String login, Object data) throws RemoteException
    {
        return null;
    }

    @Override
    public Object makeDeposit(String login, String accTo, Object data) throws RemoteException
    {
        return null;
    }

    @Override
    public Object requestAddAccount(String login, Object data) throws RemoteException
    {
        return null;
    }

    @Override
    public Object requestChangePersonalData(String login, Object data) throws RemoteException
    {
        return null;
    }

    @Override
    public Object requestLoan(String login, Object data) throws RemoteException
    {
        return null;
    }

    @Override
    public Object requestInvestment(String login, Object data) throws RemoteException
    {
        return null;
    }

    @Override
    public Object answerAddAccountReq(String login, Object data) throws RemoteException
    {
        return null;
    }

    @Override
    public Object answerChangePersonalDataReq(String login, Object data) throws RemoteException
    {
        return null;
    }

    @Override
    public Object answerLoanReq(String login, String answer, String accTo, Object data) throws RemoteException
    {
        return null;
    }

    @Override
    public Object answerInvestmentReq(String login, String answer, String accTo, Object data) throws RemoteException
    {
        return null;
    }

    @Override
    public Object getBalance(String login) throws RemoteException
    {
        return null;
    }

    @Override
    public Object getTransferHistory(String login) throws RemoteException
    {
        return null;
    }

    @Override
    public Object getPersonalData(String login) throws RemoteException
    {
        return null;
    }

    @Override
    public Object getLoanHistory(String login) throws RemoteException
    {
        return null;
    }

    @Override
    public Object getInvestmentHistory(String login) throws RemoteException
    {
        return null;
    }

    @Override
    public Object getRequestAddAccount(String login) throws RemoteException
    {
        return null;
    }

    @Override
    public Object getRequestChangePersonalData(String login) throws RemoteException
    {
        return null;
    }

    @Override
    public Object getRequestLoan(String login) throws RemoteException
    {
        return null;
    }

    @Override
    public Object getRequestInvestment(String login) throws RemoteException
    {
        return null;
    }

    @Override
    public Object LogOut(String login) throws RemoteException
    {
        return null;
    }

    @Override
    public String getDescription(String text) throws RemoteException
    {
        return null;
    }
}
