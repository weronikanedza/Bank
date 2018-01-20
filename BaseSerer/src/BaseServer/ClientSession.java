package BaseServer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientSession
{
    // This collection contain current user session.
    Map<String, Client> clients;

    ClientSession()
    {
        clients = new ConcurrentHashMap<>();
    }

    void Add(String key, Client value)
    {
        clients.put(key, value);
    }

    Client Find(String key)
    {
        return clients.get(key);
    }

    void Remove(String key)
    {
        clients.remove(key);
    }







}
