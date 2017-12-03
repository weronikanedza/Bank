package Base;

import java.io.Serializable;

public class LogTo
        implements Serializable
{
    public String login;
    public String password;

    public LogTo() { }

    public String toString()
    {
        return "LogTo [login=" + this.login + ", password=" + this.password + "]";
    }
}