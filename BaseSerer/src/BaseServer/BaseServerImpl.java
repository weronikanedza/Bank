package BaseServer;

import Base.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseServerImpl
	extends UnicastRemoteObject
	implements BaseServerFace
{

	private static final long serialVersionUID = 1L;

	private LogServer logServer = new LogServer();

	// Hold every client session
	ClientSession clientSession = new ClientSession();

	//  This object hold reference to computing servers.
	private BaseServerFace computingSever_1, computingSever_2;

	private Set<String> accountBlockMap = new HashSet<>();

	private AtomicInteger counterServer_1 = new AtomicInteger(0);
	private AtomicInteger counterServer_2 = new AtomicInteger(0);

	public BaseServerImpl(BaseServerFace serverFace_1, BaseServerFace serverFace_2) throws RemoteException
	{
		this.computingSever_1 = serverFace_1;
		this.computingSever_2 = serverFace_2;
	}

	@Override
	public LogFrom logIn(String login, LogTo data) throws Exception
	{
		LogFrom logFrom;

		if(clientSession.Find(login) == null)
		{
			if(counterServer_1.get() <= counterServer_2.get())
			{
				counterServer_1.incrementAndGet();
				logFrom = computingSever_1.logIn(login, data);
				counterServer_1.decrementAndGet();
			}
			else
			{
				counterServer_2.incrementAndGet();
				logFrom = computingSever_2.logIn(login, data);
				counterServer_2.decrementAndGet();
			}
			clientSession.Add(login, new Client());
			logServer.AddMessageToLog("logIn", login, logFrom);
			System.out.println("LogIN 1");
		}
		else
		{
			logFrom = new LogFrom();
			logFrom.error = "9";
			logServer.AddMessageToLog("logIn", login, logFrom);
			System.out.println("LogIN 2");
		}

		return logFrom;
	}

	@Override
	public String restartPassword(String login) throws RemoteException
	{
		String temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.restartPassword(login);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.restartPassword(login);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("restartPassword ", login, temporary);
		return temporary;
	}

	@Override
	public String transfer(Transfer data) throws RemoteException
	{
		String temporary = null;
		if(clientSession.Find(data.login) != null)
		{
			while(true)
			{
				if(!accountBlockMap.contains(data.accNoTo))
				{
					synchronized (accountBlockMap)
					{
						if(!accountBlockMap.add(data.accNoTo))
						{
							logServer.AddMessageToLog("transfer ", data.login, data);
							return temporary;
						}

					}

					if(!accountBlockMap.contains(data.accNoFrom))
					{
						synchronized (accountBlockMap)
						{
							if(!accountBlockMap.add(data.accNoFrom))
							{
								logServer.AddMessageToLog("transfer ", data.login, data);
								return temporary;
							}

						}

						if(counterServer_1.get() <= counterServer_2.get())
						{
							counterServer_1.incrementAndGet();
							temporary = computingSever_1.transfer(data);
							counterServer_1.decrementAndGet();

							synchronized (accountBlockMap)
							{
								accountBlockMap.remove(data.accNoTo);
								accountBlockMap.remove(data.accNoFrom);
							}

							break;
						}
						else
						{
							counterServer_2.incrementAndGet();
							temporary = computingSever_2.transfer(data);
							counterServer_2.decrementAndGet();

							synchronized (accountBlockMap)
							{
								accountBlockMap.remove(data.accNoTo);
								accountBlockMap.remove(data.accNoFrom);
							}
							break;
						}
					}


				}
			}
		}

		logServer.AddMessageToLog("transfer ", data.login, data);
		return temporary;
	}

	@Override
	public String changePassword(LogTo data) throws RemoteException
	{
		String temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.changePassword(data);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.changePassword(data);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("changePassword", data.login, data);
		return temporary;
	}

	@Override
	public String addFunds(String login, Funds data) throws RemoteException
	{
		String temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.addFunds(login, data);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.addFunds(login, data);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("addFunds", login, data);
		return temporary;
	}

	@Override
	public String requestLoan(Loan data) throws RemoteException
	{
		String temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.requestLoan(data);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.requestLoan(data);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("requestLoan", data.login, data);
		return temporary;
	}

	@Override
	public String requestAddAccount(String login, PersonalData data) throws Exception
	{
		String temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.requestAddAccount(login, data);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.requestAddAccount(login, data);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("requestAddAccount", login, data);
		return temporary;
	}

	@Override
	public String requestChangePersonalData(String login, PersonalData data) throws RemoteException
	{
		String temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.requestChangePersonalData(login, data);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.requestChangePersonalData(login, data);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("requestChangePersonalData", login, data);
		return temporary;
	}

	@Override
	public String requestInvestment(Investment data) throws RemoteException
	{
		String temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.requestInvestment(data);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.requestInvestment(data);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("requestInvestment", data.login, data);
		return temporary;
	}

	@Override
	public String answerAddAccountReq(String login, AddAccReqDecision data) throws RemoteException
	{
		String temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.answerAddAccountReq(login, data);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.answerAddAccountReq(login, data);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("answerAddAccountReq", login, data);
		return temporary;
	}

	@Override
	public String answerChangePersonalDataReq(String login, AddAccReqDecision data) throws RemoteException
	{
		String temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.answerChangePersonalDataReq(login, data);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.answerChangePersonalDataReq(login, data);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("answerChangePersonalDataReq", login, data);
		return temporary;
	}

	@Override
	public String answerLoanReq(String login, LoanDecision data) throws RemoteException
	{
		String temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.answerLoanReq(login, data);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.answerLoanReq(login, data);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("answerLoanReq", login, data);
		return temporary;
	}

	@Override
	public String getBalance(String login) throws RemoteException
	{
		String temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.getBalance(login);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.getBalance(login);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("getBalance", login, new Object());
		return temporary;
	}

	@Override
	public TransferData getTransferHistory(TransferHistory data) throws RemoteException
	{
		TransferData temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.getTransferHistory(data);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.getTransferHistory(data);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("getBalance", data.login, data);
		return temporary;
	}

	@Override
	public PersonalData getPersonalData(String login) throws RemoteException
	{
		PersonalData temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.getPersonalData(login);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.getPersonalData(login);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("getPersonalData", login, new Object());
		return temporary;
	}

	@Override
	public Loan getLoanHistory(String login) throws RemoteException
	{
		Loan temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.getLoanHistory(login);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.getLoanHistory(login);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("getLoanHistory", login, new Object());
		return temporary;
	}

	@Override
	public ListInvestment getInvestmentHistory(String login) throws RemoteException
	{
		ListInvestment temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.getInvestmentHistory(login);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.getInvestmentHistory(login);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("getInvestmentHistory", login, new Object());
		return temporary;
	}

	@Override
	public RequestListAddAccount getRequestAddAccount(String login) throws RemoteException
	{
		RequestListAddAccount temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.getRequestAddAccount(login);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.getRequestAddAccount(login);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("getRequestAddAccount", login, new Object());
		return temporary;
	}

	@Override
	public RequestListAddAccount getRequestChangePersonalData(String login) throws RemoteException
	{
		RequestListAddAccount temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.getRequestChangePersonalData(login);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.getRequestChangePersonalData(login);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("getRequestChangePersonalData", login, new Object());
		return temporary;
	}

	@Override
	public ListLoanReq getRequestLoan(String login) throws RemoteException
	{
		ListLoanReq temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.getRequestLoan(login);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.getRequestLoan(login);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("getRequestLoan", login, new Object());
		return temporary;
	}

	@Override
	public String unlockAcc(String login, String cust_nr) throws RemoteException
	{
		String temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.unlockAcc(login, cust_nr);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.unlockAcc(login, cust_nr);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("unlockAcc", login, cust_nr);
		return temporary;
	}

	@Override
	public String deleteAcc(String login, String cust_nr) throws RemoteException
	{
		String temporary;
		if(counterServer_1.get() <= counterServer_2.get())
		{
			counterServer_1.incrementAndGet();
			temporary = computingSever_1.deleteAcc(login, cust_nr);
			counterServer_1.decrementAndGet();
		}
		else
		{
			counterServer_2.incrementAndGet();
			temporary = computingSever_2.deleteAcc(login, cust_nr);
			counterServer_2.decrementAndGet();
		}

		logServer.AddMessageToLog("deleteAcc", login, cust_nr);
		return temporary;
	}

	@Override
	public Object LogOut(String login) throws RemoteException
	{
		if(clientSession.Find(login) != null)
		{
			clientSession.Remove(login);
			logServer.AddMessageToLog("LogOut", login, new Object());
			System.out.println("LogOut");
		}

		return true;
	}

	@Override
	public String getDescription(String text) throws RemoteException
	{
		return null;
	}

}
