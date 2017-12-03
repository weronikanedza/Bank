package ComputingServer;

import Base.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;

public class ComputingServerImpl
    extends UnicastRemoteObject
    implements BaseServerFace
{

    final private String URL="jdbc:mysql://localhost:3306/bankdb";
    private Connection connection=null;
    private Statement statement=null;
    private ResultSet rS=null;
    Checker check=null;
    Generator generator=null;

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

        check=new Checker(statement);
        generator=new Generator(statement);
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
    public String transfer(Transfer data) throws RemoteException
    {
//        double balance,transferAmount,senderBalance;
//        if(!balance().equals("")) {
//            balance = Double.parseDouble(balance());
//            transferAmount = Double.parseDouble(data.amount);
//            senderBalance = balance - transferAmount;
//
//            if (senderBalance < 0 || findAccount().equals("")) {
//                System.out.println("You don't have enough money/ there is no account with given number");
//                list.clear();
//                list.add("1");
//            }
//            else {
//                try {
//                    sql = "UPDATE account a  join customers c on a.pesel=c.pesel  set a.balance= '" + senderBalance + "' WHERE c.customer_nr='" + login + "'";
//                    statement.executeUpdate(sql); //update sender account
//                    sql = "UPDATE account SET balance=balance+'" + transferAmount + "' WHERE id_account='" + list.get(2) + "'";
//                    statement.executeUpdate(sql);
//                    list.clear();
//                    list.add("0"); //transfer accepted
//                } catch (SQLException e) {
//                    list.add("1");
//                    System.out.println(e.getMessage());
//                } catch (Exception e) {
//                    list.add("1");
//                    System.out.println("balance function exception");
//                    System.out.println(e.getMessage());
//                }
//            }
//        }else {
//            list.clear();
//            list.add("1");
//        }
//        return list;
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
    public String requestAddAccount(String login, PersonalData data) throws RemoteException
    {
        int id_req=0;
        try{
            if( check.checkIfCustomerExist(data.pesel) &&
                    check.checkCustomerInAddAccReq(data.pesel) ){ //+++age
                statement.execute("SELECT id_request FROM newaccountrequest");
                rS=statement.getResultSet();
                while (rS.next())
                    id_req = Integer.parseInt(rS.getString(1));

                id_req+=1;

                statement.executeUpdate("INSERT INTO newaccountrequest VALUES('"+id_req+ "','"+data.firstName+
                        "','"+data.lastName+"','"+data.street+"','"+data.city+"','"+data.zipCode+
                        "','"+data.idNumber+"','"+data.email+"','"+data.phoneNumber+"','"+data.pesel+"',1)");

            }else throw new Exception();

        }catch (Exception e){
            System.out.println(e.getMessage());
            return "1";
        }
        return "0";
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
    public String answerAddAccountReq(String login, AddAccReqDecision data) throws RemoteException
    {
        if (data.decision.equals("y")) {
            try {
                String logNr = generator.generateLogin();
                statement.executeUpdate("INSERT INTO users (login,password,status) VALUES ('" + logNr + "','" + generator.generatePassword() + "','C')");
                statement.executeUpdate("INSERT INTO customers (pesel,customer_nr,firstname,lastname,idNumber,street,email,zipcode,city,phonenumber)" +
                        " SELECT pesel,'" + logNr + "',firstname,lastname,idNumber,street,email,zipcode,city,phonenumber from newaccountrequest where id_request='" + data.id_req + "'");
                statement.executeUpdate("INSERT into account (id_account,balance,pesel) SELECT '" + generator.generateAccNr() + "',0.00,pesel " +
                        "from newaccountrequest where id_request='" + data.id_req + "'");
                statement.executeUpdate("Delete from newaccountrequest where id_request='" + data.id_req + "'");
            } catch (Exception e) {
                System.out.println("answer add acc req exception");
                System.out.println(e.getMessage());
                return "1";
            }

        }else {
            try{
                statement.executeUpdate("Delete from newaccountrequest where id_request='" + data.id_req + "'");
            } catch (SQLException e) {
                System.out.println("answer add acc req exception");
                e.printStackTrace();
                return "1";
            }

        }
        return "0";
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
    public RequestListAddAccount getRequestAddAccount(String login) throws RemoteException
    {
        RequestListAddAccount req=new RequestListAddAccount();
        req.data=new ArrayList<>();
        try {
            ResultSet rS=statement.executeQuery("SELECT * from newaccountrequest");
            while(rS.next()){
                AddAccountRequest addAcc=new AddAccountRequest(rS.getString("id_request"),rS.getString("firstname"),
                        rS.getString("lastname"),rS.getString("street"),rS.getString("zipCode"),
                        rS.getString("city"),rS.getString("pesel"),rS.getString("idNumber"),
                        rS.getString("email"),rS.getString("phoneNumber"));
                req.data.add(addAcc);
            }
            req.error="0";
        }catch(Exception e){
            System.out.println(e.getMessage());
            req.error="1";
        }

        return  req;
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
