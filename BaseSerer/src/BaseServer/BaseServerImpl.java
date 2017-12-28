package BaseServer;

import Base.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

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

	//  This object hold reference to computing servers. Counters represent how much client is connecting to server,
	private BaseServerFace computingSever_1;
	private int countComputingSever_1 = 0;

	private BaseServerFace computingSever_2;
	private int countComputingSever_2= 0;

	public BaseServerImpl(BaseServerFace serverFace_1, BaseServerFace serverFace_2) throws RemoteException
	{
		this.computingSever_1 = serverFace_1;
		this.computingSever_2 = serverFace_2;
	}

	@Override
	public synchronized LogFrom logIn(String login, LogTo data) throws RemoteException
	{
		synchronized (computingSever_1)
		{
			LogFrom logFrom = computingSever_1.logIn(login, data);

			if(logFrom.error.equals("0"))
			{
				// Add error code if client have session now!!!!

				logServer.addMessageToLog("Add client " + login + " to session.");

				ClientData client = new ClientData();
				clientSession.addClient(login, client);

				if(countComputingSever_2 == countComputingSever_1)
				{
					client.setComputingSever(computingSever_2);
					System.out.println("2: " + countComputingSever_2++);
				}
				else if(countComputingSever_2 < countComputingSever_1)
				{
					client.setComputingSever(computingSever_2);
					System.out.println("2: " + countComputingSever_2++);
				}
				else
				{
					client.setComputingSever(computingSever_1);
					System.out.println("1: " + countComputingSever_1++);
				}
			}

			return logFrom;
		}

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
		System.out.println("TRANSFER");
		return computingSever_1.transfer(data);
	}

	@Override
	public String changePassword(LogTo data) throws RemoteException
	{
		System.out.println("changepassword");
		return computingSever_1.changePassword(data);

	}



	@Override
	public String addFunds(String login,Funds data) throws RemoteException
	{
//		System.out.println("addFunds");
//		return computingSever.addFunds(data);
		return null;
	}

	@Override
	public String requestAddAccount(String login, PersonalData data) throws RemoteException
	{
		System.out.println("requestaddaccount");
		return  computingSever_1.requestAddAccount(login, data);

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
		System.out.println("ANSWERADDACCOUNTREQ");
		return computingSever_1.answerAddAccountReq(login, data);

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
		System.out.println("GETBALANCE");
		return computingSever_1.getBalance(login);
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
		System.out.println("Zakladanie konta(wniosek)");
		return computingSever_1.getRequestAddAccount(login);
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
