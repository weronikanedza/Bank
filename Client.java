import java.rmi.Naming;

public class Client
{
    private final String serverName = "ListenServer";
    private String name;

    public static void main(String args[])
    {
        System.out.println("--------------------------");
        System.out.println("Client start! " + args[0]);
        new Client(args);
        System.out.println("Client stop! " + args[0]);
        System.out.println("--------------------------");
    }

    public Client(String args[])
    {
        this.name = args.length != 1 ? args[0] : "localhost";

        ServerFace login;
        try
        {
            login = (ServerFace) Naming.lookup("Login");


            switch(args[1])
            {
                case "0":
                    System.out.println("Logowanie: " + args[0]);
                    login.CheckLogin(name, args[1]);
                    break;
                case "1":
                    System.out.println("Wylogowanie " + args[0]);
                    login.CheckLogout(name);
                    break;

                case "2":
                    System.out.println("Transfer " + args[0]);
                    login.SendTransfer(args[0], args[2], 100);
                    break;

            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }


    }
}
