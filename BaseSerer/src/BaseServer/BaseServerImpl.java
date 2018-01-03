package BaseServer;

import Base.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.atomic.AtomicBoolean;

public class BaseServerImpl
	extends UnicastRemoteObject
	implements BaseServerFace
{
	private static final long serialVersionUID = 1L;

	// Object logServer print and hold (not yet) information about route on server.
	// Something like information about client and server errors.
	private LogServer logServer = new LogServer();

	// This object hold every client who is now login on server.
	private ClientSession clientSession = new ClientSession();

	//  This object hold reference to computing servers. AtomicBoolean represent state each computing server.
	private BaseServerFace computingSever_1, computingSever_2;
	private AtomicBoolean serverState_1 = new AtomicBoolean(true);
	private AtomicBoolean serverState_2 = new AtomicBoolean(true);

	public BaseServerImpl(BaseServerFace serverFace_1, BaseServerFace serverFace_2) throws RemoteException
	{
		this.computingSever_1 = serverFace_1;
		this.computingSever_2 = serverFace_2;
	}

	@Override
	public LogFrom logIn(String login, LogTo data) throws RemoteException, InterruptedException
	{
		LogFrom logFrom;

		while(true)
		{
			if(serverState_1.get())
			{
				synchronized (computingSever_1)
				{
					serverState_1.set(false);
					logFrom = computingSever_1.logIn(login, data);
					serverState_1.set(true);
				}
				break;
			}
			else if(serverState_2.get())
			{
				synchronized (computingSever_2)
				{
					serverState_2.set(false);
					logFrom = computingSever_2.logIn(login, data);
					serverState_2.set(true);
				}
				break;
			}
		}

		if(logFrom.error.equals("0"))
			clientSession.addClient(login, new ClientData());

		logServer.AddMessageToLog("logIn", login, logFrom);
		return logFrom;
	}

	@Override
	public String restartPassword(String login) throws RemoteException
	{
//		System.out.println("RESTARTPASSWORD");
//		return computingSever.restartPassword(login);
		return null;
	}

	@Override
	public String transfer( Transfer data) throws RemoteException
	{
		String temporary;
		while(true)
		{
			if(serverState_1.get())
			{
				synchronized (computingSever_1)
				{
					serverState_1.set(false);
					temporary = computingSever_1.transfer(data);
					serverState_1.set(true);
				}

				break;
			}
			else if(serverState_2.get())
			{
				synchronized (computingSever_2)
				{
					serverState_2.set(false);
					temporary = computingSever_2.transfer(data);
					serverState_2.set(true);
				}

				break;
			}
		}
		logServer.AddMessageToLog("transfer", data.login, data);

		return temporary;
	}

	@Override
	public String changePassword(LogTo data) throws RemoteException
	{
		String temporary;
		while(true)
		{
			if(serverState_1.get())
			{
				synchronized (computingSever_1)
				{
					serverState_1.set(false);
					temporary = computingSever_1.changePassword(data);
					serverState_1.set(true);
				}

				break;
			}
			else if(serverState_2.get())
			{
				synchronized (computingSever_2)
				{
					serverState_2.set(false);
					temporary = computingSever_2.changePassword(data);
					serverState_2.set(true);
				}
				break;
			}
		}

		logServer.AddMessageToLog("changePassword", data.login, data);
		return temporary;
	}



	@Override
	public String addFunds(String login, Funds data) throws RemoteException
	{
//		System.out.println("addFunds");
//		return computingSever.addFunds(data);
		return null;
	}

	@Override
	public String requestAddAccount(String login, PersonalData data) throws RemoteException
	{
		String temporary;
		while(true)
		{
			if(serverState_1.get())
			{
				synchronized (computingSever_1)
				{
					serverState_1.set(false);
					temporary = computingSever_1.requestAddAccount(login, data);
					serverState_1.set(true);
				}

				break;
			}
			else if(serverState_2.get())
			{
				synchronized (computingSever_2)
				{
					serverState_2.set(false);
					temporary = computingSever_2.requestAddAccount(login, data);
					serverState_2.set(true);
				}
				break;
			}
		}

		logServer.AddMessageToLog("requestAddAccount", login, data);
		return temporary;
	}

	@Override
	public String requestChangePersonalData(String login, PersonalData data) throws RemoteException
	{
//		System.out.println("request chane persoanl data");
//		return  computingSever.requestChangePersonalData(login, data);
		return null;
	}

	@Override
	public ListLoanReq getRequestLoan(String login) throws RemoteException
	{
		return null;
	}

	@Override
	public String requestInvestment(Investment data) throws RemoteException
	{
		return null;
	}

	@Override
	public String answerAddAccountReq(String login, AddAccReqDecision data) throws RemoteException
	{
		String temporary;
		while(true)
		{
			if(serverState_1.get())
			{
				synchronized (computingSever_1)
				{
					serverState_1.set(false);
					temporary = computingSever_1.answerAddAccountReq(login, data);
					serverState_1.set(true);
				}

				break;
			}
			else if(serverState_2.get())
			{
				synchronized (computingSever_2)
				{
					serverState_2.set(false);
					temporary = computingSever_2.answerAddAccountReq(login, data);
					serverState_2.set(true);
				}
				break;
			}
		}

		logServer.AddMessageToLog("answerAddAccountReq", login, data);

		return temporary;
	}

	@Override
	public String answerChangePersonalDataReq(String login,AddAccReqDecision data) throws RemoteException
	{
		return null;
	}

	@Override
	public String answerLoanReq(String login,LoanDecision data) throws RemoteException
	{
		return null;
	}



	@Override
	public String getBalance(String login) throws RemoteException
	{
		String temporary;
		while(true)
		{
			if(serverState_1.get())
			{
				synchronized (computingSever_1)
				{
					serverState_1.set(false);
					temporary = computingSever_1.getBalance(login);
					serverState_1.set(true);
				}

				break;
			}
			else if(serverState_2.get())
			{
				synchronized (computingSever_2)
				{
					serverState_2.set(false);
					temporary = computingSever_2.getBalance(login);
					serverState_2.set(true);
				}
				break;
			}
		}

		logServer.AddMessageToLog("answerAddAccountReq", login, " ");

		return temporary;
	}

	@Override
	public TransferData getTransferHistory(TransferHistory data) throws RemoteException
	{
		return null;
	}

	@Override
	public PersonalData getPersonalData(String login) throws RemoteException
	{
		return null;
	}

	@Override
	public Loan getLoanHistory(String login) throws RemoteException
	{
		return null;
	}

	@Override
	public ListInvestment getInvestmentHistory(String login) throws RemoteException
	{
		return null;
	}

	@Override
	public RequestListAddAccount getRequestAddAccount(String login) throws RemoteException
	{
		RequestListAddAccount temporary;
		while(true)
		{
			if(serverState_1.get())
			{
				synchronized (computingSever_1)
				{
					serverState_1.set(false);
					temporary = computingSever_1.getRequestAddAccount(login);
					serverState_1.set(true);
				}

				break;
			}
			else if(serverState_2.get())
			{
				synchronized (computingSever_2)
				{
					serverState_2.set(false);
					temporary = computingSever_2.getRequestAddAccount(login);
					serverState_2.set(true);
				}
				break;
			}
		}

		logServer.AddMessageToLog("RequestListAddAccount", login, temporary);

		return temporary;
	}

	@Override
	public  RequestListAddAccount getRequestChangePersonalData(String login) throws RemoteException
	{
		return null;
	}

	@Override
	public String requestLoan(Loan data) throws RemoteException
	{
		return null;
	}

	@Override
	public String unlockAcc(String login,String cust_nr) throws RemoteException
	{
		return null;
	}

	@Override
    public String deleteAcc(String login) throws RemoteException
    {
		return null;
	}

	@Override
	public Object LogOut(String login) throws RemoteException
	{
		clientSession.removeClient(login);
		logServer.AddMessageToLog("LogOut " + login);
		return true; // CO MOZE POJSC NIE TAK ?! XDDDD
		//////////////////////////
		// DO ZMIANY
		//////////////////////////
	}

	@Override
	public String getDescription(String text) throws RemoteException
	{
		return null;
	}
}
