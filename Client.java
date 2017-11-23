package sample;

import javax.naming.Context;
import javax.naming.InitialContext;


public class Client
{
    private final String serverName = "ListenServer";
    private String name;

    public static void main(String args[])
    {
        System.out.println("--------------------------");
        System.out.println("Client start");
        new Client("1");
        System.out.println("Client stop! ");
        System.out.println("--------------------------");
    }

    public Client(String state)
    {
        // String url = "rmi://217.182.77.242/";
        String url = "rmi://localhost/";
        String name = "maslo";

        try
        {
            Context context = new InitialContext();
            ServerFace login = (ServerFace) context.lookup(url + "ServerFace");

            switch (state)
            {
                case "0":
                    System.out.println("Logowanie: ");
                    login.CheckLogin(name, state);
                    break;
                case "1":
                    System.out.println("Wylogowanie " + state);
                    login.CheckLogout(name);
                    break;

                case "2":
                    System.out.println("Transfer " + state);
                    login.SendTransfer(state, state, 100);
                    break;

            }
        } catch (Exception e)
        {
            System.err.println(e);

        }

    }
}
