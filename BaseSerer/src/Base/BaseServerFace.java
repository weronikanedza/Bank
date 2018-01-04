package Base;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BaseServerFace
        extends Remote
{
    LogFrom logIn(String login, LogTo data) throws Exception; //checked // changed from RemoteException
    String restartPassword(String login) throws RemoteException;//checked + email
    String transfer(Transfer data) throws RemoteException; //checked
    String changePassword(LogTo data) throws RemoteException; // checked
    String addFunds(String login,Funds data) throws RemoteException; //checked
    String requestLoan(Loan data) throws RemoteException; //checked
    String requestAddAccount(String login, PersonalData data) throws Exception; //checked // changed from RemoteException
    String requestChangePersonalData(String login, PersonalData data) throws RemoteException;// checked
    String requestInvestment(Investment data) throws RemoteException; //checked

    String answerAddAccountReq(String login, AddAccReqDecision data) throws RemoteException; //checked //email
    String answerChangePersonalDataReq(String login,AddAccReqDecision data) throws RemoteException; //checked
    String answerLoanReq(String login,LoanDecision data) throws RemoteException; // checked

    String getBalance(String login) throws RemoteException; // checked
    TransferData getTransferHistory(TransferHistory data) throws RemoteException; //ok(w)
    PersonalData getPersonalData(String login) throws RemoteException; //checked
    Loan getLoanHistory(String login) throws RemoteException; //ok(w)
    ListInvestment getInvestmentHistory(String login) throws RemoteException; //ok(w)
    RequestListAddAccount getRequestAddAccount(String login) throws RemoteException; //checked
    RequestListAddAccount getRequestChangePersonalData(String login) throws RemoteException; //checked
    ListLoanReq getRequestLoan(String login) throws RemoteException; //checked
    String unlockAcc (String login,String cust_nr) throws RemoteException; //ok(w)
    String deleteAcc (String login) throws RemoteException;


    Object LogOut(String login) throws RemoteException;
    //TESTY

    String getDescription(String text) throws RemoteException;

}