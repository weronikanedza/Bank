import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {
    final private String URL="jdbc:sqlite:C:\\Users\\weronika\\Desktop\\workspace\\testDB2\\database.db";
    private Connection connection=null;
    private Statement statement=null;
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
            connection = DriverManager.getConnection(URL);
            statement=connection.createStatement();
            chooseOperation(statement);
        }catch (SQLException e){
            System.out.println("Cannot connect-function");
            //System.exit(0);  wyłączać ?
        }finally {
            if(statement!=null)
                statement.close();
        }
    }

    private void chooseOperation(Statement statement) throws SQLException {
        SQLcommand sql=new SQLcommand(statement);
        String operation=list.get(0);

       switch (operation){
           case "LOGOWANIE":
               LogOn log=new LogOn(list);
               log.compare(statement);
               break;
       }
    }
}
