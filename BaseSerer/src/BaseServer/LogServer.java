package BaseServer;

import java.util.Date;

public class LogServer
{
    Object object = new Object();
    void addLog(String message)
    {
        synchronized (object)
        {
            System.out.println(new Date().toString() + " " + message);
        }
    }
}
