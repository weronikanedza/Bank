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
        }

    }

    public ClientData findClient(String login)
    {
        synchronized (clientSessions)
        {
            return clientSessions.get(login);
        }
    }

}
