package BaseServer;

public class ClientData
{
	private String name;
	private String password;
	private String account;
	private String other;

	public ClientData(String name, Object data)
	{
		this.name = name;
		this.other = (String)data;
	}

	public ClientData(String name, String password, String account)
	{
		this.name = name;
		this.password = password;
		this.account = account;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public void setOther(String other)
	{
		this.other = other;
	}

	public String getName()
	{
		return name;
	}

	public String getPassword()
	{
		return password;
	}

	public String getAccount()
	{
		return account;
	}

	public String getOther()
	{
		return other;
	}
}
