package sample;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerFace
        extends Remote
{
    void CheckLogin(String login, String password) throws RemoteException;
    void CheckLogout(String login) throws RemoteException;

    void SendTransfer(String fromClient, String toClient, int value) throws RemoteException;


    //TESTY

    String getDescription(String text) throws RemoteException;

}