package Base;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BaseServerFace
        extends Remote
{
    LogFrom logIn(String login, LogTo data) throws RemoteException; //ok
    Object restartPassword(String login, Object data) throws RemoteException;
    Object transfer(String login, String accFrom, String accTo, Object data) throws RemoteException;
    Object changePassword(String login, Object data) throws RemoteException;
    Object makeDeposit(String login, String accTo, Object data) throws RemoteException;

    String requestAddAccount(String login, PersonalData data) throws RemoteException; //ok
    Object requestChangePersonalData(String login, Object data) throws RemoteException;
    Object requestLoan(String login, Object data) throws RemoteException;
    Object requestInvestment(String login, Object data) throws RemoteException;

    String answerAddAccountReq(String login, AddAccReqDecision data) throws RemoteException; //ok --now
    Object answerChangePersonalDataReq(String login, Object data) throws RemoteException;
    Object answerLoanReq(String login, String answer, String accTo, Object data) throws RemoteException; // dogaduje sie z Dominikiem ale raczej tak zostanie
    Object answerInvestmentReq(String login, String answer, String accTo, Object data) throws RemoteException; // dogaduje sie z Dominikiem ale raczej tak zostanie

    Object getBalance(String login) throws RemoteException; // do Werki tylko login
    Object getTransferHistory(String login) throws RemoteException;
    Object getPersonalData(String login) throws RemoteException;
    Object getLoanHistory(String login) throws RemoteException;
    Object getInvestmentHistory(String login) throws RemoteException;
    RequestListAddAccount getRequestAddAccount(String login) throws RemoteException; //ok
    Object getRequestChangePersonalData(String login) throws RemoteException;
    Object getRequestLoan(String login) throws RemoteException;
    Object getRequestInvestment(String login) throws RemoteException;

    Object LogOut(String login) throws RemoteException;


    //TESTY

    String getDescription(String text) throws RemoteException;

}