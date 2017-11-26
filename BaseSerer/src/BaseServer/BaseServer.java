package BaseServer;

import Base.BaseServerFace;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BaseServer
	extends UnicastRemoteObject
{
	private static BaseServerFace computingSever;

	protected BaseServer() throws RemoteException
	{

	}

	public static void main(String args[])
	{
		System.out.println("Server start!");

		// Connetct with ComputingServer

		String url = "rmi://localhost/";
		try
		{
			Context context = new InitialContext();

			computingSever = (BaseServerFace) context.lookup(url + "ComputingServerFace");
		}
		catch (NamingException e)
		{
			System.out.println(e);
			e.printStackTrace();
		}

		// Connect with client
		try
		{
			BaseServerImpl server = new BaseServerImpl(computingSever);

			Context contextServer = new InitialContext();
			contextServer.rebind("rmi:BaseServerFace", server);

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

		System.out.println("Server is ready!");

	}
}
