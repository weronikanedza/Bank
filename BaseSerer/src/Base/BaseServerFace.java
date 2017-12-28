package Base;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BaseServerFace
        extends Remote
{
    LogFrom logIn(String login, LogTo data) throws RemoteException; //checked
    String restartPassword(String login) throws RemoteException;//ok (w) email
    String transfer(Transfer data) throws RemoteException; //checked
    String changePassword(LogTo data) throws RemoteException; // ok(w)
    String addFunds(String login,Funds data) throws RemoteException; //ok (w)
    String requestLoan(Loan data) throws RemoteException; //ok(w)
    String requestAddAccount(String login, PersonalData data) throws RemoteException; //checked
    String requestChangePersonalData(String login, PersonalData data) throws RemoteException;//ok(w)
    String requestInvestment(Investment data) throws RemoteException; //ok(w)

    String answerAddAccountReq(String login, AddAccReqDecision data) throws RemoteException; //checked //email
    String answerChangePersonalDataReq(String login,AddAccReqDecision data) throws RemoteException; //ok(w)
    String answerLoanReq(String login,LoanDecision data) throws RemoteException; // ok(w)

    String getBalance(String login) throws RemoteException; // checked
    TransferData getTransferHistory(TransferHistory data) throws RemoteException; //ok(w)
    PersonalData getPersonalData(String login) throws RemoteException; //ok(w)
    Loan getLoanHistory(String login) throws RemoteException; //ok(w)
    ListInvestment getInvestmentHistory(String login) throws RemoteException; //ok(w)
    RequestListAddAccount getRequestAddAccount(String login) throws RemoteException; //checked
    RequestListAddAccount getRequestChangePersonalData(String login) throws RemoteException; // ok(w)
    ListLoanReq getRequestLoan(String login) throws RemoteException; //ok(w)
    String unlockAcc (String login,String cust_nr) throws RemoteException; //ok(w)


    Object LogOut(String login) throws RemoteException;
    //TESTY

    String getDescription(String text) throws RemoteException;

}