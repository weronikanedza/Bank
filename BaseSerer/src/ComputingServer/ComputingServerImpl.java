package ComputingServer;

import Base.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * class creates connection with database and implements
 * interface with client requests
 */
public class ComputingServerImpl
    extends UnicastRemoteObject
    implements BaseServerFace
{

    final private String URL="jdbc:mysql://localhost:3306/bankdb?useUnicode=yes&characterEncoding=UTF-8";
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
            statement.execute("SET NAMES 'UTF8'");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
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
    public LogFrom logIn(String login, LogTo data) throws Exception
    {

        byte[] key = {-120,17,42,121,-12,1,6,34};

        SecretKey secretKey = new SecretKeySpec(key,"DES");
        DesEncrypter encrypter = new DesEncrypter(secretKey);

        data.login = encrypter.decrypt(data.login);
        data.password = encrypter.decrypt(data.password);


        System.out.println("LOGIN");
        LogFrom logFrom=new LogFrom();
        try {
            rS= statement.executeQuery("Select password,status,counter from users where login='"+data.login+"'" );
            rS.next();
            Integer counter= Integer.parseInt(rS.getString("counter"));
            System.out.println(counter);
            if(data.password.equals(rS.getString("password")) && counter<3 ){  //checks password capital/small letters
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
                statement.executeUpdate("UPDATE users SET counter=0 where login='" + data.login + "' ");

            }else if (!data.login.equals("1")){
                rS=statement.executeQuery("SELECT counter from users where login='"+data.login+"'");
                rS.next();

                if (counter<3) {
                    statement.executeUpdate("UPDATE users SET counter=counter+1 where login='" + data.login + "' ");
                }else
                    logFrom.error="2";

            }else {
                logFrom.error="1";
            }
        } catch(SQLException e) {
            System.out.println("resultset exception");
            System.out.println(e.getMessage());
            logFrom.error = "1";
        }

        return logFrom;
    }

    @Override
    public String restartPassword(String login) throws RemoteException
    {
        try {
            statement.executeUpdate("Update users set password='" + generator.generatePassword() + "' where login='"+login+"'");
        }catch (SQLException e){
            System.out.println("restartPassword sql exception");
            System.out.println(e.getMessage());
            return "1";
        }
        return "0";
    }

    @Override
    public String transfer(Transfer data) throws RemoteException {
        BigDecimal balance, transferAmount, senderBalance,receiverBalance;
        BigDecimal bigDecimal=new BigDecimal("0");
        int id_transfer=0;
        int temp=0;

        balance = new BigDecimal(check.checkBalance(data.accNoFrom));
        transferAmount = new BigDecimal(data.amount);
        senderBalance = balance.subtract(transferAmount);

        System.out.println(transferAmount);
        System.out.println(balance);
        System.out.println("Sender balance " + senderBalance);

        if (senderBalance.compareTo(bigDecimal)<0) {
            return "1";
        } else if (check.findAccount(data.accNoTo).equals("")) {
            return "2";
        } else {
            try {
                LocalDate date=LocalDate.now();
                statement.executeUpdate("UPDATE account a  join customers c on a.pesel=c.pesel  " +
                        "set a.balance= '" + senderBalance + "' WHERE c.customer_nr='" + data.login + "'"); //update sender account

                receiverBalance=balance.add(transferAmount);
                statement.executeUpdate("UPDATE account SET balance='" + receiverBalance
                        + "' WHERE id_account='" + data.accNoTo + "'");
                rS=statement.executeQuery("SELECT id_transfer from transfer");

                while(rS.next()) {
                    temp = Integer.parseInt(rS.getString("id_transfer"));
                    if(temp>id_transfer)
                        id_transfer=temp;
                }

                id_transfer+=1;
                statement.executeUpdate("INSERT into transfer values('"+id_transfer+"','"+data.accNoFrom+"','"+data.accNoTo+"','"+data.amount+"','"+data.title+"','" +
                        ""+date.toString()+"')");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return "3";
            }

            return "0";
        }
    }

    @Override
    public String changePassword(LogTo data) throws RemoteException
    {
        try {
            statement.executeUpdate("Update users set password='" + data.password + "' where login='"+data.login+"'");
        }catch (SQLException e){
            System.out.println("restartPassword sql exception");
            System.out.println(e.getMessage());
            return "1";
        }
        return "0";
    }


    @Override
    public String addFunds(String login,Funds data) throws RemoteException
    {
        try{
            if(check.checkBalanceLogin(data.login).equals("")){
                return "2";
            }else {
                BigDecimal balance=new BigDecimal(check.checkBalanceLogin(data.login));
                balance=balance.add(new BigDecimal(data.amount));
                statement.executeUpdate("update account a join customers c on a.pesel = c.pesel set a.balance ='"+balance+"' WHERE c.customer_nr='"+data.login+"'");
            }
        }catch (SQLException e){
            System.out.println("Error addFunds");
            return "1";
        }
        return "0";
    }

    @Override

    public String requestAddAccount(String login, PersonalData data) throws Exception
    {

        byte[] key = {-120,17,42,121,-12,1,6,34};
        SecretKey secretKey = new SecretKeySpec(key,"DES");
        DesEncrypter encrypter = new DesEncrypter(secretKey);

        data.firstName = encrypter.decrypt(data.firstName);
        data.lastName = encrypter.decrypt(data.lastName);
        data.street = encrypter.decrypt(data.street);
        data.zipCode = encrypter.decrypt(data.zipCode);
        data.city = encrypter.decrypt(data.city);
        data.pesel = encrypter.decrypt(data.pesel);
        data.idNumber = encrypter.decrypt(data.idNumber);
        data.email = encrypter.decrypt(data.email);
        data.phoneNumber = encrypter.decrypt(data.phoneNumber);

        int id_req=0;
        int temp=0;
        System.out.println(data);
        try{
            if( check.checkIfCustomerExist(data.pesel) &&
                    check.checkCustomerInAddAccReq(data.pesel) && check.checkAge(data.pesel) ){
                rS=statement.executeQuery("SELECT id_request FROM newaccountrequest");
                while (rS.next()) {
                    temp = Integer.parseInt(rS.getString(1));
                    if(temp>id_req)
                        id_req=temp;
                }

                id_req+=1;

                statement.executeUpdate("INSERT INTO newaccountrequest VALUES('"+id_req+ "','"+data.firstName+
                        "','"+data.lastName+"','"+data.street+"','"+data.city+"','"+data.zipCode+
                        "','"+data.idNumber+"','"+data.email+"','"+data.phoneNumber+"','"+data.pesel+"',1,1)");

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return "1";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "1";
        }

        return "0";
    }

    @Override
    public String requestChangePersonalData(String login, PersonalData data) throws RemoteException
    {
        System.out.println(data);
        int id_req=0;
        int temp=0;
        try{
            if(check.checkCustomerInAddAccReq(data.pesel) && check.checkAge(data.pesel) ){
                statement.execute("SELECT id_request FROM newaccountrequest");
                rS=statement.getResultSet();
                while (rS.next()) {
                    temp= Integer.parseInt(rS.getString(1));
                    if(temp>id_req)
                        id_req=temp;
                }

                id_req+=1;

                statement.executeUpdate("INSERT INTO newaccountrequest VALUES('"+id_req+ "','"+data.firstName+
                        "','"+data.lastName+"','"+data.street+"','"+data.city+"','"+data.zipCode+
                        "','"+data.idNumber+"','"+data.email+"','"+data.phoneNumber+"','"+data.pesel+"',1,0)");

            }else {
                return "2";
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return "1";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "1";
        }
        return "0";
    }

    @Override
    public ListLoanReq getRequestLoan(String login) throws RemoteException
    {
        ListLoanReq list=new ListLoanReq();
        list.loanList =new ArrayList<>();

        try{
            rS=statement.executeQuery("Select * from loan l natural join customers c where l.status=0");
            while (rS.next()){
                list.loanList.add(new LoanReq(rS.getString("customer_nr"),rS.getString("amount"),rS.getString("instalment"),
                        rS.getString("numberOfMonths"),rS.getString("bankRate"),rS.getString("salary"),rS.getString("id_loan")
                        ,new PersonalData(rS.getString("firstname"),rS.getString("lastname"),rS.getString("street"),rS.getString("zipCode"),
                        rS.getString("city"),rS.getString("pesel"),rS.getString("idNumber"),rS.getString("email"),rS.getString("phoneNumber"))));
            }
            list.error="0";
        }catch (SQLException e){
            System.out.println(e.getMessage());
            list.error="1";
        }
        return list;

    }

    @Override
    public String requestInvestment(Investment data) throws RemoteException
    {
        		int id=0;
        		int temp=0;
		try {
			if(Double.parseDouble(check.checkBalanceLogin(data.login))>=Double.parseDouble(data.amount)) {
				String rate[] = check.checkBankRate(data.time);
				String tab[] = generator.dateGenerate(data.time);
				String finalAmount = check.checkAmount(data.amount, data.time, rate[1]);
				BigDecimal balance=new BigDecimal(check.checkBalanceLogin(data.login));
				balance=balance.subtract(new BigDecimal(data.amount));
				rS = statement.executeQuery("Select id_investment FROM investment");
				while (rS.next()) {
                    temp = Integer.parseInt(rS.getString("id_investment"));
                    if(temp>id)
                        id=temp;
                }
				++id;

				statement.executeUpdate("INSERT INTO investment values('" + id + "','" + data.amount + "','" + tab[0] + "','" + tab[1] + "'," + rate[0] + ",0,'" + finalAmount + "','"+data.login+"')");
				statement.executeUpdate("UPDATE account NATURAL join customers set balance='"+balance+"'where customer_nr='"+data.login+"'");
			}else{
				return"2";
			}

		}catch (SQLException e){
			System.out.println(e.getMessage());
			return "1";
		}
		return "0";
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
            } catch (SQLException e) {
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
    public String answerChangePersonalDataReq(String login,AddAccReqDecision data) throws RemoteException
    {
        if(data.decision.equals("y")){

            try {
                statement.executeUpdate("UPDATE customers SET city = '"+data.personalData.city+"', email='"+data.personalData.email+"'," +
                        "firstname='"+data.personalData.firstName+"',idNumber='"+data.personalData.idNumber+"'," +
                        "lastname='"+data.personalData.lastName+"',phoneNumber='"+data.personalData.phoneNumber+"'," +
                        "street='"+data.personalData.street+"',zipcode='"+data.personalData.zipCode + "' where pesel='"+data.personalData.pesel+"'");

                statement.executeUpdate("Delete from newaccountrequest where id_request='"+data.id_req+"'");
            } catch (Exception e) {
                System.out.println("answer change personal data exception");
                System.out.println(e.getMessage());
                return "1";
            }
        }
        else{
            try{
                statement.executeUpdate("Delete from newaccountrequest where id_request='" + data.id_req + "'");
            } catch (SQLException e) {
                System.out.println("answer change personal data req exception");
                e.printStackTrace();
                return "1";
            }

        }
        return "0";
    }

    @Override
    public String answerLoanReq(String login, LoanDecision data) throws RemoteException
    {
        if(data.decision.equals("y")){
            try {
                rS=statement.executeQuery("SELECT * from loan where id_loan='"+data.id_req+"'");;
                rS.next();
                String tab[]=generator.dateGenerate(rS.getString("numberOfMonths"));
                String customer_nr=rS.getString("customer_nr");
                BigDecimal amount=new BigDecimal(rS.getString("amount"));
                BigDecimal balance=new BigDecimal(check.checkBalanceLogin(customer_nr));
                balance=balance.add( amount);
                System.out.println(balance);
                statement.executeUpdate("UPDATE account a natural join customers c set a.balance='"+balance+"' WHERE " +
                        "c.customer_nr='"+customer_nr+"'");

                statement.executeUpdate("UPDATE Loan set status=1, date='" + tab[0] + "',dateTo='"+tab[1]+"' where id_loan='" + data.id_req + "'");
            }catch (SQLException e){
                System.out.println(e.getMessage());
                return "1";
            }
        } else{
            try {
                statement.executeUpdate("Delete from Loan where id_loan='" + data.id_req + "'");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return "0";
    }



    @Override
    public String getBalance(String login) throws RemoteException
    {
        try {
            ResultSet rS = statement.executeQuery("Select balance from account natural join customers where customer_nr='"+login+"'");
            rS.next();
            System.out.println(rS.getString("balance"));
            return rS.getString("balance");
        }catch(SQLException e) {
            System.out.println(e.getMessage());
            return "-1";
        }
    }

    @Override
    public TransferData getTransferHistory(TransferHistory data) throws RemoteException
    {

//        TransferData tranferData=new TransferData();
//        tranferData.transferList=new ArrayList<>();
//        try{
//            rS=statement.executeQuery("select * from transfer t join account a on a.id_account=t.accFrom join customers c " +
//                    "on c.pesel=a.pesel where c.customer_nr='"+data.login+"' and t.date>=DATE_ADD(CURDATE(),INTERVAL '" +"-" +data.date+"' DAY)");
//
//            while (rS.next()){
//                tranferData.transferList.add(new Transfer(data.login,rS.getString("accFrom"),rS.getString("accTo"),
//                        rS.getString("amount"),rS.getString("title"),rS.getString("date")));
//
//
//            }
//            tranferData.error="0";
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//            tranferData.error="1";
//        }
//        return tranferData;
        return null;
    }

    @Override
    public PersonalData getPersonalData(String login) throws RemoteException
    {
        PersonalData data;
        try{
            ResultSet rS=statement.executeQuery("SELECT * from customers where customer_nr='"+login+"'");
            rS.next();
            data=new PersonalData(rS.getString("firstname"),rS.getString("lastname"),rS.getString("street"),rS.getString("zipcode"),
                    rS.getString("city"),rS.getString("pesel"),rS.getString("idNumber"),rS.getString("email"),
                    rS.getString("phoneNumber"));
        }catch (SQLException e){
            System.out.println("getPersonalData exception");
            System.out.println(e.getMessage());
            return null;
        }
        System.out.println(data);
        return data;
    }

    @Override
    public Loan getLoanHistory(String login) throws RemoteException
    {
//        Loan loan;
//        try {
//            rS=statement.executeQuery("SELECT * from loan where customer_nr='"+login+"' and status=1");
//            rS.next();
//            loan=new Loan(login, rS.getString("amount"),rS.getString("instalment"),rS.getString("numberOfMonths"),
//                    rS.getString("bankRate"),rS.getString("salary"),rS.getString("date"),rS.getString("dateTo"),
//                    "0");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            loan=new Loan();
//            loan.error="1";
//        }
//        return loan;
        return null;
    }

    @Override
    public ListInvestment getInvestmentHistory(String login) throws RemoteException
    {
//        ListInvestment listInvestment=new ListInvestment();
//        listInvestment.list= new ArrayList<>();
//        try {
//            rS=statement.executeQuery("Select rate from bankRate natural join investment where customer_nr='"+login+"'");
//            rS.next();
//            String rate=rS.getString("rate");
//
//            rS=statement.executeQuery("SELECT * from investment where customer_nr='"+login+"'");
//            while(rS.next())
//                listInvestment.list.add(new InvestmentHistory(rS.getString("amount"),rS.getString("dateFrom"),
//                        rS.getString("dateTo"),rate,rS.getString("status"),rS.getString("finalAmount")));
//            listInvestment.error="0";
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            listInvestment.error="1";
//        }
//        return listInvestment;
        return null;
    }

    @Override
    public RequestListAddAccount getRequestAddAccount(String login) throws RemoteException
    {
        RequestListAddAccount req=new RequestListAddAccount();
        req.data=new ArrayList<>();
        try {
            ResultSet rS=statement.executeQuery("SELECT * from newaccountrequest where reqstatus=1");
            while(rS.next()){
                AddAccountRequest addAcc=new AddAccountRequest(rS.getString("id_request"),rS.getString("firstname"),
                        rS.getString("lastname"),rS.getString("street"),rS.getString("zipCode"),
                        rS.getString("city"),rS.getString("pesel"),rS.getString("idNumber"),
                        rS.getString("email"),rS.getString("phoneNumber"));
                req.data.add(addAcc);
            }
            req.error="0";
        }catch(SQLException e){
            System.out.println(e.getMessage());
            req.error="1";
        }

        return  req;
    }

    @Override
    public  RequestListAddAccount getRequestChangePersonalData(String login) throws RemoteException
    {
        RequestListAddAccount req=new RequestListAddAccount();
        req.data=new ArrayList<>();
        try {
            ResultSet rS=statement.executeQuery("SELECT * from newaccountrequest where reqstatus='0'");
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
    public String requestLoan(Loan data) throws RemoteException
    {
        int id_loan=0;
        int temp=0;
        try {
            if(check.checkLoan(data.login)) {
                statement.execute("SELECT id_loan FROM loan");
                rS = statement.getResultSet();
                while (rS.next()) {
                    id_loan = Integer.parseInt(rS.getString(1));
                    if(temp>id_loan)
                        id_loan=temp;
                }
                ++id_loan ;
                statement.executeUpdate("INSERT into loan VALUES ('" + id_loan + "','" + data.amount + "'," +
                        "'5','" + data.instalment + "','" + data.numberOfMonths + "','" + data.login + "','" + data.salary + "','0','0','0')");
            }else{
                return "2";
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return "1";
        }
        return "0";
    }

    @Override
    public String unlockAcc(String login,String cust_nr) throws RemoteException
    {
//        try {
//            statement.executeUpdate("Update users set password='" + generator.generatePassword()+ "' where login='"+cust_nr+"'");
//            statement.executeUpdate("UPDATE USERS set counter=0 where login='"+cust_nr+"'");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return "1";
//        }
//        return "0";
        return null;
    }

    @Override
    public String deleteAcc(String login) throws RemoteException
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
