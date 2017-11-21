import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.HashMap;
import java.util.Map;


public class ListenServer
    extends UnicastRemoteObject
    implements ServerFace
{
    // This collection contain current user session.
    private static Map<String, ClientData> clientDataSet;

    // This collection contain users who are transferring now.
    // Tego dalej nie pisze bo to do ustalenia jak mam dane przesylac do Wery!!!!
    private String transferUserBlock[];

    public static void main(String args[])
    {
        System.out.println("SERVER START!");

        ListenServer server;
        try
        {
            server = new ListenServer();
            Naming.rebind("Login", server);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ListenServer() throws RemoteException
    {
        clientDataSet = new HashMap<>();
    }

    @Override
    public void CheckLogin(String login, String password)
            throws RemoteException
    {
        System.out.println("CheckLogin: " + login);

        if(clientDataSet.containsKey(login))
        {
            System.out.println("TAKI USER ISTANIEJE: " + login);
        }
        else
        {
            synchronized (clientDataSet)
            {
                System.out.println("Login: " + login);

                clientDataSet.put(login, new ClientData(login));
            }
        }
    }

    @Override
    public void CheckLogout(String login)
            throws RemoteException
    {
        System.out.println("CheckLogout: " + login);

        if(clientDataSet.containsKey(login))
        {
            synchronized (clientDataSet)
            {
                System.out.println("Logout: " + login);
                clientDataSet.remove(login);
            }
        }
        else
        {
            System.out.println("BRAK TAKIEGO USERA: " + login);
        }
    }


    @Override
    public void SendTransfer(String fromClient, String toClient, int value) throws RemoteException
    {
        // DO DOGADANIA
    }

    private class ClientData
    {
        private String login;
        private boolean online = false;

        public ClientData(String login)
        {
            this.login = login;
        }
    }


    // TO ZALEZY CO CHCE Wera DOSTAC
    private class Transmitter
        implements Runnable
    {
        @Override
        public void run()
        {

        }
    }

}


