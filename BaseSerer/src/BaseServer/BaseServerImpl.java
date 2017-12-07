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
		LogFrom logFrom= computingSever.logIn(login, data);
		return logFrom;
}

	@Override
	public Object restartPassword(String login, Object data) throws RemoteException
	{
		return null;
	}

	@Override
	public String transfer( Transfer data) throws RemoteException
	{
//		System.out.println("TRANSFER");
//		String transfer = computingSever.transfer(login, accFrom,accTo,data);
//		return transfer;
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
	public String requestAddAccount(String login, PersonalData data) throws RemoteException
	{
		System.out.println("Zakladanie konta(wniosek)");
		String err = computingSever.requestAddAccount(login, data);

		return err;
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
	public String answerAddAccountReq(String login, AddAccReqDecision data) throws RemoteException
	{
		System.out.println("ANSWERADDACCOUNTREQ");
		String err = computingSever.answerAddAccountReq(login, data);

		return err;
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
	public String getBalance(String login) throws RemoteException
	{
//		System.out.println("GETBALANCE");
//		String balance = computingSever.getBalance(login);
//		return balance;
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
	public RequestListAddAccount getRequestAddAccount(String login) throws RemoteException
	{
		System.out.println("Zakladanie konta(wniosek)");
		RequestListAddAccount list = computingSever.getRequestAddAccount(login);

		return list;
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
