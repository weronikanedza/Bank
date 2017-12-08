package Base;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BaseServerFace
        extends Remote
{
<<<<<<< HEAD
    LogFrom logIn(String login, LogTo data) throws RemoteException; //checked
    String restartPassword(String login) throws RemoteException;//ok (w)
    String transfer(Transfer data) throws RemoteException; //test
    String changePassword(LogTo data) throws RemoteException; // ok(w)
=======
    LogFrom logIn(String login, LogTo data) throws RemoteException; //ok
    Object restartPassword(String login, Object data) throws RemoteException;
    String transfer(Transfer data) throws RemoteException; //OK --now
    String changePassword(LogTo data) throws RemoteException;
>>>>>>> 3aeb2514ed691d653eb9e58801ada8d5dfb8f3d0
    Object makeDeposit(String login, String accTo, Object data) throws RemoteException;
    String addFunds(Object data) throws RemoteException;

    String requestAddAccount(String login, PersonalData data) throws RemoteException; //checked
    String requestChangePersonalData(String login, PersonalData data) throws RemoteException;//ok(w)
    Object requestLoan(String login, Object data) throws RemoteException;
    Object requestInvestment(String login, Object data) throws RemoteException;

    String answerAddAccountReq(String login, AddAccReqDecision data) throws RemoteException; //checked
    Object answerChangePersonalDataReq(String login, Object data) throws RemoteException;
    Object answerLoanReq(String login, String answer, String accTo, Object data) throws RemoteException; // dogaduje sie z Dominikiem ale raczej tak zostanie
    Object answerInvestmentReq(String login, String answer, String accTo, Object data) throws RemoteException; // dogaduje sie z Dominikiem ale raczej tak zostanie

    String getBalance(String login) throws RemoteException; // test
    Object getTransferHistory(String login) throws RemoteException;
    Object getPersonalData(String login) throws RemoteException;
    Object getLoanHistory(String login) throws RemoteException;
    Object getInvestmentHistory(String login) throws RemoteException;
    RequestListAddAccount getRequestAddAccount(String login) throws RemoteException; //checked
    Object getRequestChangePersonalData(String login) throws RemoteException;
    Object getRequestLoan(String login) throws RemoteException;
    Object getRequestInvestment(String login) throws RemoteException;

    Object LogOut(String login) throws RemoteException;
    //TESTY

    String getDescription(String text) throws RemoteException;

}