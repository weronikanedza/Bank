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
        clientSessions.put(login, data);
    }
}
