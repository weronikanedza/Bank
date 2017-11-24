package sample;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServerFace
{
    protected ServerImpl() throws RemoteException
    {
        super();
    }

    @Override
    public void SendTransfer(String fromClient, String toClient, int value) throws RemoteException
    {

    }

    @Override
    public void CheckLogin(String login, String password) throws RemoteException
    {

    }

    @Override
    public void CheckLogout(String login) throws RemoteException
    {

    }

    @Override
    public Object logIn(String login, Object data) throws RemoteException
    {
        return null;
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