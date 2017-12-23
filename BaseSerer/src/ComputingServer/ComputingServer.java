package ComputingServer;

import Base.LogFrom;
import Base.LogTo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class ComputingServer
{
    public static void main(String args[]) throws RemoteException, SQLException {
        System.out.println("Server start!");
        try
        {
            // FIRST SERVER
            ComputingServerImpl server_1 = new ComputingServerImpl();
            Context contextServer_1 = new InitialContext();
            contextServer_1.rebind("rmi:ComputingServerFace1", server_1);
            System.out.println("First server is ready!");

            // SECOND SERVER
            ComputingServerImpl server_2 = new ComputingServerImpl();
            Context contextServer_2 = new InitialContext();
            contextServer_2.rebind("rmi:ComputingServerFace2", server_2);
            System.out.println("Second server is ready!");
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
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
