package BaseServer;

import Base.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

public class BaseServerImpl
	extends UnicastRemoteObject
	implements BaseServerFace
{
	private static final long serialVersionUID = 1L;

	// This collection contain current user session.
	private static Map<String, ClientData> clientDataMap;

	BaseServerFace computingSever;

	public BaseServerImpl(BaseServerFace serverFace) throws RemoteException
	{
		this.computingSever = serverFace;
	}

	@Override
	public synchronized LogFrom logIn(String login, LogTo data) throws RemoteException
	{
		System.out.println("LOGIN");
		LogFrom logFrom = computingSever.logIn(login, data);

		return logFrom;
	}

	@Override
	public Object restartPassword(String login, Object data) throws RemoteException
	{
		return null;
	}

	@Override
	public Object transfer(String login, String accFrom, String accTo, Object data) throws RemoteException
	{
		return null;
	}

	@Override
	public Object changePassword(String login, Object data) throws RemoteException
	{
		return null;
	}

	@Override
	public Object makeDeposit(String login, String accTo, Object data) throws RemoteException
	{
		return null;
	}

	@Override
	public Object requestAddAccount(String login, Object data) throws RemoteException
	{
		return null;
	}

	@Override
	public Object requestChangePersonalData(String login, Object data) throws RemoteException
	{
		return null;
	}

	@Override
	public Object requestLoan(String login, Object data) throws RemoteException
	{
		return null;
	}

	@Override
	public Object requestInvestment(String login, Object data) throws RemoteException
	{
		return null;
	}

	@Override
	public Object answerAddAccountReq(String login, Object data) throws RemoteException
	{
		return null;
	}

	@Override
	public Object answerChangePersonalDataReq(String login, Object data) throws RemoteException
	{
		return null;
	}

	@Override
	public Object answerLoanReq(String login, String answer, String accTo, Object data) throws RemoteException
	{
		return null;
	}

	@Override
	public Object answerInvestmentReq(String login, String answer, String accTo, Object data) throws RemoteException
	{
		return null;
	}

	@Override
	public Object getBalance(String login) throws RemoteException
	{
		return null;
	}

	@Override
	public Object getTransferHistory(String login) throws RemoteException
	{
		return null;
	}

	@Override
	public Object getPersonalData(String login) throws RemoteException
	{
		return null;
	}

	@Override
	public Object getLoanHistory(String login) throws RemoteException
	{
		return null;
	}

	@Override
	public Object getInvestmentHistory(String login) throws RemoteException
	{
		return null;
	}

	@Override
	public Object getRequestAddAccount(String login) throws RemoteException
	{
		return null;
	}

	@Override
	public Object getRequestChangePersonalData(String login) throws RemoteException
	{
		return null;
	}

	@Override
	public Object getRequestLoan(String login) throws RemoteException
	{
		return null;
	}

	@Override
	public Object getRequestInvestment(String login) throws RemoteException
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
