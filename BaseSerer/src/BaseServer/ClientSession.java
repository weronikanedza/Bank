package BaseServer;

import java.util.HashMap;
import java.util.Map;

public class ClientSession
{
    // This collection contain current user session.
    private static Map<String, ClientData> clientSessions;

    public ClientSession()
    {
        clientSessions = new HashMap<>();
    }


    public void addClient(String login, ClientData data)
    {
        synchronized (clientSessions)
        {
            clientSessions.put(login, data);
            show(); // for test
        }

    }

    public boolean removeClient(String login)
    {
        synchronized (clientSessions)
        {
            if(clientSessions.remove(login) != null)
            {
                show(); // for test
                return true;
            }

        }
        return false;
    }

    public synchronized void show()
    {
        System.out.println(clientSessions);
    }



}
