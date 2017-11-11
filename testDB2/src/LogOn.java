import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
/**
 *handle log on operation
 */
public class LogOn extends Operation {
    private String login;
    private String password;
    private List<String> outList;
    private String sql;

    public LogOn(List<String> list) {
        super(list);
        outList=new ArrayList<>();
        login=list.get(1);
        password=list.get(2);
        System.out.println("Constructor LogOn login:"+login+" haslo:"+password);
        outList=new ArrayList<>(list);
    }

    @Override
    public String toString() {
        return "LogOn{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +"}";
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    /**
     *
     * @return list which will be sent to client
     * @param1 operation type
     * @param2 login/client number
     * @param3 status ex.0 client/1 admin
     * @param4 error
     */
    public List<String> compare(Statement statement) throws SQLException {

        outList.remove(2); //remove password from outputlist
        sql="Select Status from Users u where" +
                     " u.Login= '"+this.login +"' and u.Password='"+this.password+"'";

        try {
           ResultSet rS= statement.executeQuery(sql);
            outList.add("1"); //
            outList.add(rS.getString("Status"));

        }catch(SQLException e){
            System.out.println(e.getMessage());
            outList.add("0"); //set error element on 0
        }
        System.out.println("Output list");
        return outList;
    }
}
