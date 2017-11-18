import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {
    final private String URL="jdbc:mysql://localhost:3306/bankdb";
    private Connection connection=null;
    private Statement statement=null;
    ResultSet rS =null;
    private List<String> list;

    public ConnectDB(List<String> list) throws SQLException {
        this.list=new ArrayList<>(list);
            try {
               connect();
            }catch (SQLException e){
               System.out.println("Cannot connect-constructor");
            }
    }
    /**
     *Creates connection with database
     * @throws SQLException
     */
    private void connect() throws SQLException {

        try {
            connection = DriverManager.getConnection(URL,"root","");
            statement=connection.createStatement();
            list=chooseOperation(statement);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }finally {
            if(statement!=null)
                statement.close();
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

    private List<String> chooseOperation(Statement statement) throws SQLException {
        SQLcommand sqlCommand=new SQLcommand(statement,list);
        String operation=list.get(0);

       switch (operation){
           case"logowanie":
                list=sqlCommand.logIn();
               break;
           case "dodawanie":
               list=sqlCommand.addCustomer();
           case "przelew":
               list=sqlCommand.transfer();
               break;
       }

       return list;
    }

    public List<String> getList() {
        return list;
    }
}
