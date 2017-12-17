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
	private static BaseServerFace computingSever_1, computingSever_2;

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
			Context context_1 = new InitialContext();
			computingSever_1 = (BaseServerFace) context_1.lookup(url + "ComputingServerFace1");

			Context context_2 = new InitialContext();
			computingSever_2 = (BaseServerFace) context_2.lookup(url + "ComputingServerFace2");
		}
		catch (NamingException e)
		{
			System.out.println(e);
			e.printStackTrace();
		}

		// Connect with client
		try
		{
			BaseServerImpl server = new BaseServerImpl(computingSever_1, computingSever_2);

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
