import java.sql.SQLException;
import java.sql.Statement;

/**The class implements functions which gather data
 * from sqlite database
 */
public class SQLcommand {
    Statement statement;
    String sql;
    public SQLcommand(Statement statement) {
        this.statement = statement;
    }

    public void logOn(String login,String password) throws SQLException {
        sql="Select Login from Users where login=" +login;
        statement.execute(sql);
    }

}
