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
    public String getDescription(String text) throws RemoteException
    {
        return "TESTYYYYYY";
    }

    @Override
    public void CheckLogin(String login, String password) throws RemoteException
    {
        System.out.println("asdas");
    }

    @Override
    public void CheckLogout(String login) throws RemoteException
    {
        System.out.println("asdas");
    }

    @Override
    public void SendTransfer(String fromClient, String toClient, int value) throws RemoteException
    {
        System.out.println("asdas");
    }
}