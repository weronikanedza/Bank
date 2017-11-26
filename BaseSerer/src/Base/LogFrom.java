package Base;

import java.io.Serializable;

public class LogFrom
implements Serializable
{

    public String status;
    public String error;
    public String accNo;
    public String login;
    public String balance;

    public LogFrom()
    {
    }

    public String toString() {
        return "LogFrom [status=" + this.status + ", error=" + this.error + ", accNo=" + this.accNo + ", login=" + this.login + ", balance=" + this.balance + "]";
    }
}

