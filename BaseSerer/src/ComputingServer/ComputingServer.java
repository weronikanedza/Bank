package ComputingServer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class ComputingServer
{
    public static void main(String args[])
    {
        System.out.println("Server start!");
        try
        {
            ComputingServerImpl server = new ComputingServerImpl();

            Context contextServer = new InitialContext();
            contextServer.rebind("rmi:ComputingServerFace", server);
            System.out.println("Server is ready!");
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        catch (NamingException e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
